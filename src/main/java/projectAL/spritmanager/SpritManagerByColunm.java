package projectAL.spritmanager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import gameframework.core.DrawableImage;
import gameframework.core.SpriteManager;

/**
 * This implementation of {@link SpriteManager} assumes that sprite types are in
 * colunms whereas increments of a type are in rows
 * 
 */

public class SpritManagerByColunm implements SpriteManager {

	private final DrawableImage image;
	private Map<String, Integer> types;

  //as the sprites aren't squares we need the 2 dimensions
	private final int spriteSizeX;
	private final int spriteSizeY;

	private int spriteNumber = 0;
	private final int maxSpriteNumber;
	private int currentColunm;
	private final int renderingSize;

	public SpritManagerByColunm(String filename,
                              Canvas canvas,
                              int renderingSize,
                              int numberOfRows,
                              int numberOfColumns) {

		this.renderingSize = renderingSize;
		image = new DrawableImage(filename, canvas);
		this.maxSpriteNumber = numberOfRows;

		this.spriteSizeX = image.getImage().getHeight(null) / numberOfRows;
		this.spriteSizeY = image.getImage().getWidth(null) / numberOfColumns;

	}

	@Override
	public void setTypes(String... types) {
		int i = 0;
		this.types = new HashMap<String, Integer>(types.length);
		for (String type : types) {
			this.types.put(type, i);
			i++;
		}
	}

	@Override
	public void draw(Graphics g, Point position) {

		// Destination image coordinates
		int dx1 = (int) position.getX();
		int dy1 = (int) position.getY();
		int dx2 = dx1 + renderingSize;
		int dy2 = dy1 + renderingSize;

		// Source image coordinates
		int sx1 = currentColunm * spriteSizeX;
		int sy1 = spriteNumber * spriteSizeY;
		int sx2 = sx1 + spriteSizeX;
		int sy2 = sy1 + spriteSizeY;

    // BufferedImage image2;
    // if(flip){
    //   AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
    //   tx.translate(0, -image.getImage().getHeight(null));
    //   AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    //   image2 = op.filter(toBufferedImage(image.getImage()), null);
    // }else{
    //   image2 = toBufferedImage(image.getImage());
    // }

    g.drawImage(image.getImage(), dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
                null);
	}

	@Override
	public void setType(String type) {
		if (!types.containsKey(type)) {
			throw new IllegalArgumentException(type
					+ " is not a valid type for this sprite manager.");
		}
		this.currentColunm = types.get(type);
	}

	@Override
	public void increment() {
		spriteNumber = (spriteNumber + 1) % maxSpriteNumber;
	}

	@Override
	public void reset() {
		spriteNumber = 0;
	}

	@Override
	public void setIncrement(int increment) {
		this.spriteNumber = increment;
	}
  
}

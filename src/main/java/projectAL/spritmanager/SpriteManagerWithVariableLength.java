package projectAL.spritmanager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import gameframework.core.DrawableImage;
import gameframework.core.SpriteManager;

import projectAL.utility.SpriteType;

/**
 * This implementation of {@link SpriteManager} assumes that sprite types are in
 * rows whereas increments of a type are in columns
 *
 */

public class SpriteManagerWithVariableLength implements SpriteManager {

	private final DrawableImage image;
	private Map<String, int[]> types;
	private final int spriteSize;
	private int spriteNumber = 0;
	private int SpriteLength;
	private int currentRow;
	private final int renderingSize;

	@Override
	public void setTypes(String... types) {
    System.out.println("not implemented plese use the method with SpriteType");
	}

	public SpriteManagerWithVariableLength(String filename, Canvas canvas, int renderingSize, int maxSpriteNumber) {
		this.renderingSize = renderingSize;
		image = new DrawableImage(filename, canvas);
    this.SpriteLength = maxSpriteNumber;
		this.spriteSize = image.getImage().getWidth(null) / maxSpriteNumber;
	}

	public void setTypes(SpriteType... types ) {
		int i = 0;
		this.types = new HashMap<String, int[]>(types.length);
		for (SpriteType type : types) {
      int[] tmp = {type.getLength(),i};
			this.types.put(type.getType(), tmp);
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
		int sx1 = spriteNumber * spriteSize;
		int sy1 = currentRow * spriteSize;
		int sx2 = sx1 + spriteSize;
		int sy2 = sy1 + spriteSize;
		g.drawImage(image.getImage(), dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
	}

	@Override
	public void setType(String type) {
		if (!types.containsKey(type)) {
			throw new IllegalArgumentException(type + " is not a valid type for this sprite manager.");
		}
    int[] t = types.get(type);
		this.currentRow = t[1];
    this.SpriteLength = t[0];
	}

	@Override
	public void increment() {
		spriteNumber = (spriteNumber + 1) % SpriteLength;
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

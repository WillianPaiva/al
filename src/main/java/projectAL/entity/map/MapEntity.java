package projectAL.entity.map;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import gameframework.core.Drawable;
import gameframework.core.DrawableImage;
import gameframework.core.GameEntity;

public abstract class MapEntity implements Drawable, GameEntity {
  protected  DrawableImage image = null;
  public int RENDERING_SIZE = 32;
  protected  Point draw;
  protected  int tile;



  @Override
  public void draw(Graphics g) {
		g.drawImage(
        image.getImage(),
        (int)this.draw.getX(),
        (int)this.draw.getY(),
        RENDERING_SIZE,
        RENDERING_SIZE,
                null);
  }

  public MapEntity (Canvas defultCanvas, Point draw, int tile){
    this.draw = draw;
    this.image = new DrawableImage("sprite/generated/"+tile+".gif", defultCanvas);
  }



}

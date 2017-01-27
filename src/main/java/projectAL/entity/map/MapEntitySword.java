package projectAL.entity.map;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.core.Overlappable;

public class MapEntitySword extends MapEntity implements Overlappable {


  @Override
  public Point getPosition() {
    return this.draw;

  }


  public MapEntitySword(Canvas defultCanvas, Point draw, int tile) {
    super(defultCanvas, draw, tile);
  }

  @Override
  public Rectangle getBoundingBox() {
    return ( new Rectangle((int) this.draw.getX(), (int)this.draw.getY(), RENDERING_SIZE, RENDERING_SIZE));
  }

}

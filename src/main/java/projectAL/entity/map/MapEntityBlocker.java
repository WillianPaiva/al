package projectAL.entity.map;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.moves_rules.MoveBlocker;

public class MapEntityBlocker extends MapEntity implements MoveBlocker {

  public MapEntityBlocker(Canvas defultCanvas, Point draw, int tile) {
    super(defultCanvas, draw, tile);

  }

  @Override
  public Rectangle getBoundingBox() {
    return ( new Rectangle((int) this.draw.getX(), (int)this.draw.getY(), RENDERING_SIZE, RENDERING_SIZE));
  }




}

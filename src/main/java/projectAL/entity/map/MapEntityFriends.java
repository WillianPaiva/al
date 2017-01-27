package projectAL.entity.map;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.core.DrawableImage;
import gameframework.core.Overlappable;

public class MapEntityFriends extends MapEntity implements Overlappable {


  @Override
  public Point getPosition() {
    return this.draw;
  }


  public MapEntityFriends(Canvas defultCanvas, Point draw) {
    super(defultCanvas,draw,0);
    this.image = new DrawableImage("sprite/Human/HumanFriend.png", defultCanvas);
    this.RENDERING_SIZE = 64;
  }

  @Override
  public Rectangle getBoundingBox() {
    return ( new Rectangle((int) this.draw.getX(), (int)this.draw.getY(), RENDERING_SIZE, RENDERING_SIZE));
  }

}

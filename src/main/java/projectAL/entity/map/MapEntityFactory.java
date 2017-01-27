package projectAL.entity.map;

import java.awt.Canvas;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;


public class MapEntityFactory {
  private int SPRITE_SIZE;
	private Canvas canvas;

	public MapEntityFactory(Canvas canvas, int SPRITE_SIZE){
    this.canvas = canvas;
    this.SPRITE_SIZE = SPRITE_SIZE;
	}

  public MapEntity getOverlapable(Point p, int tile){
    return new MapEntityOverlapable(canvas, p,tile);
  }

  public MapEntity getBlocker(Point p, int tile){
    return new MapEntityBlocker(canvas, p,tile);
  }

  public MapEntity getSword(int tile){
    return new MapEntitySword(canvas, getPoint(),tile);
  }

  public MapEntity getShield(int tile){
    return new MapEntityShield(canvas, getPoint(),tile);
  }

  public MapEntity getPotion(int tile){
    return new MapEntityPotion(canvas, getPoint(),tile);
  }

  public MapEntity getFriends(){
    return new MapEntityFriends(canvas, getPoint());
  }

  private Point getPoint(){
    return new Point(getRandom(3, 25) * SPRITE_SIZE, getRandom(3, 28) * SPRITE_SIZE);
  }

  private int getRandom(int min , int max){
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

}

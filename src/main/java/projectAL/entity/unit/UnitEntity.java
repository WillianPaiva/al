package projectAL.entity.unit;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.core.Drawable;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.Overlappable;

import projectAL.spritmanager.SpriteManagerWithVariableLength;

import soldier.ages.AgeMiddleFactory;
import soldier.core.AgeAbstractFactory;
import soldier.core.Unit;
import soldier.core.UnitGroup;

public abstract class UnitEntity extends GameMovable implements Drawable,
                                                     GameEntity, Overlappable {

  //we have to different sprites one for the character walking
  protected SpriteManagerWithVariableLength spriteManager;

  //create a factory for this unit
  protected AgeAbstractFactory UnitFactory = new AgeMiddleFactory();

  //the unit represented by this entitiy
  protected Unit unit;

  public static final int RENDERING_SIZE = 64;

  protected boolean attacking = false;
  private String equip;
  private Canvas canvas;


  public UnitEntity(Canvas defautCanvas, String name, String equip){
    initSpritManagers(defautCanvas, equip);
    this.unit = new UnitGroup(name);
    this.unit.addUnit(UnitFactory.infantryUnit(name));
    this.equip = equip;
    this.canvas = defautCanvas;
  }

  public abstract void initSpritManagers(Canvas defautCanvas, String equip);

  @Override
  public Rectangle getBoundingBox() {
    return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
  }

  @Override
  public void draw(Graphics g) {
    String spriteType = "WalkingUp";
    Point tmp = getSpeedVector().getDirection();

    if (tmp.getX() == 1) {
      spriteType = "WalkingRight";
    } else if (tmp.getX() == -1) {
      spriteType = "WalkingLeft";
    } else if (tmp.getY() == 1) {
      spriteType = "WalkingDown";
    } else if (tmp.getY() == -1) {
      spriteType = "WalkingUp";
    } else {
        spriteManager.reset();
    }

      spriteManager.setType(spriteType);
      spriteManager.draw(g, getPosition());
  }


  @Override
  public void oneStepMoveAddedBehavior() {
    spriteManager.increment();
  }

  public void parry(float attack){
    this.unit.parry(attack);
  }

  public void attack(UnitEntity target){
    target.parry(this.unit.strike());
  }

  public void addMember(String name){
    this.unit.addUnit(UnitFactory.infantryUnit(name));
  }

  public void addSword(){
    this.unit.addEquipment(UnitFactory.attackWeapon());
    if(this.equip.equals("Shield")){
      initSpritManagers(canvas,"Full");
      this.equip = "Full";
    }else if(this.equip.equals("Nothing")){
      initSpritManagers(canvas,"Dagger");
      this.equip = "Dagger";
    }
  }

  public void addShield(){
    this.unit.addEquipment(UnitFactory.defenseWeapon());
    if(this.equip.equals("Dagger")){
      initSpritManagers(canvas,"Full");
      this.equip = "Full";
    }else if(this.equip.equals("Nothing")){
      initSpritManagers(canvas,"Shield");
      this.equip = "Shield";
    }
  }

  public void usePotion(){
    this.unit.heal();
  }

  public boolean isAlive(){
    return this.unit.alive();
  }
  public int healthpoints(){
    return Math.round(this.unit.getHealthPoints());
  }

}

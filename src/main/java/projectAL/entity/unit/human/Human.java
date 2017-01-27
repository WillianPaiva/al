package projectAL.entity.unit.human;

import java.awt.Canvas;


import projectAL.entity.unit.UnitEntity;
import projectAL.spritmanager.SpriteManagerWithVariableLength;
import projectAL.utility.SpriteType;



public class Human extends UnitEntity {

     /**
      *
      */
  public Human(Canvas defaultCanvas, String name,String equip ){
    super(defaultCanvas, name, equip );
     }

     @Override
     public void initSpritManagers(Canvas defaultCanvas, String equip) {
          //create a split manager for the walking 
       this.spriteManager = new SpriteManagerWithVariableLength(
              "sprite/Human/Human"+equip+".png",
               defaultCanvas, RENDERING_SIZE, 13
                                                                   );

          this.spriteManager.setTypes(
              new SpriteType("SpellUp",7),
              new SpriteType("SpellLeft",7),
              new SpriteType("SpellDown",7),
              new SpriteType("SpellRight",7),
              new SpriteType("TrustUp",8),
              new SpriteType("TrustLeft",8),
              new SpriteType("TrustDown",8),
              new SpriteType("TrustRight",8),
              new SpriteType("WalkingUp",9),
              new SpriteType("WalkingLeft",9),
              new SpriteType("WalkingDown",9),
              new SpriteType("WalkingRight",9),
              new SpriteType("AttackingUp",6),
              new SpriteType("AttackingLeft",6),
              new SpriteType("AttackingDown",6),
              new SpriteType("AttackingRight",6),
              new SpriteType("ShootingUp",13),
              new SpriteType("ShootingLeft",13),
              new SpriteType("ShootingDown",13),
              new SpriteType("ShootingRight",13),
              new SpriteType("Dying",6)
              );

     }


}

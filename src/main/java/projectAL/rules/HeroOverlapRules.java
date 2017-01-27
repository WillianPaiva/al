package projectAL.rules;

import java.awt.Point;

import gameframework.core.GameUniverse;
import gameframework.core.ObservableValue;
import gameframework.moves_rules.OverlapRulesApplierDefaultImpl;

import projectAL.entity.map.MapEntity;
import projectAL.entity.map.MapEntityFriends;
import projectAL.entity.map.MapEntityPotion;
import projectAL.entity.map.MapEntityShield;
import projectAL.entity.map.MapEntitySword;
import projectAL.entity.unit.human.Human;
import projectAL.entity.unit.orc.Orc;

public class HeroOverlapRules extends OverlapRulesApplierDefaultImpl{

	protected GameUniverse universe;
  protected Point startPosition;
  protected int orcsKilled = 0;
  protected int orcs = 0;
  protected ObservableValue<Integer> lives;
  protected ObservableValue<Integer> score;
  protected ObservableValue<Boolean> endOfGame;

	public HeroOverlapRules(
      Point start,
      ObservableValue<Integer> score,
      ObservableValue<Integer> lives,
      ObservableValue<Boolean> endOfGame
                          )
  {
    this.startPosition = start;
    this.lives = lives;
    this.score = score;
    this.endOfGame = endOfGame;
    this.lives.setValue(100);
	}

	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

  public void setNbOrcs(int o){
    this.orcs = o; 
  }

	public void overlapRule(Human h, Orc o) {
    h.attack(o);
    o.attack(h);
    if(!o.isAlive()){
      this.universe.removeGameEntity(o);
      score.setValue(score.getValue()+3);
      this.orcsKilled++;
      if(this.orcsKilled >= this.orcs){
        endOfGame.setValue(true);
      }
    }
    if(!h.isAlive()){
      endOfGame.setValue(true);
    }
    this.lives.setValue(h.healthpoints());
	}

	public void overlapRule(Human h, MapEntityPotion p) {
    h.usePotion();
    this.universe.removeGameEntity(p);
    this.lives.setValue(h.healthpoints());
	}

	public void overlapRule(Human h,  MapEntitySword s) {
    h.addSword();
    this.universe.removeGameEntity(s);
    score.setValue(score.getValue()+2);
	}

	public void overlapRule(Human h,  MapEntityShield s) {
    h.addShield();
    this.universe.removeGameEntity(s);
    score.setValue(score.getValue()+1);
	}

	public void overlapRule(Human h,  MapEntityFriends f) {
    h.addMember("friend");
    this.universe.removeGameEntity(f);
    score.setValue(score.getValue()+5);
	}

}

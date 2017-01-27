package projectAL.levels;

import java.awt.Canvas;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import gameframework.core.CanvasDefaultImpl;
import gameframework.core.Game;
import gameframework.core.GameLevelDefaultImpl;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.GameUniverseDefaultImpl;
import gameframework.core.GameUniverseViewPortDefaultImpl;
import gameframework.moves_rules.MoveBlockerChecker;
import gameframework.moves_rules.MoveBlockerCheckerDefaultImpl;
import gameframework.moves_rules.MoveStrategyRandom;
import gameframework.moves_rules.OverlapProcessor;
import gameframework.moves_rules.OverlapProcessorDefaultImpl;

import projectAL.entity.map.MapEntityFactory;
import projectAL.entity.unit.UnitEntity;
import projectAL.entity.unit.human.Human;
import projectAL.entity.unit.orc.Orc;
import projectAL.movestrategy.MoveStrategyKeyboardWithStop;
import projectAL.rules.HeroOverlapRules;

public class GameLvlArena extends GameLevelDefaultImpl {
  public static final int SPRITE_SIZE = 32;
  public static final int NUMBER_OF_ORCS = 20;
  public static final int NUMBER_OF_SHIELDS = 5;
  public static final int NUMBER_OF_SWORDS = 5;
  public static final int NUMBER_OF_POTIONS = 5;
  public static final int NUMBER_OF_FRIENDS = 5;

  private Canvas canvas;
  private MapEntityFactory mapFactory;
  public GameLvlArena(Game g) {
    super(g);
    canvas = g.getCanvas();
    mapFactory = new MapEntityFactory(canvas, SPRITE_SIZE);
  }

  //,---------------------------------------------------------------------------
  //| all the tiles cam be found on the sprite/generated folder each tile has a
  //| number refernte to the name of the file. her we produce the map by using 2
  //| 2d lists the ides is one list for the overlapable tiles and one list for
  //| the move blocker tiles. if a tile is number 0 on the overlapable a valuer
  //| for the tile will be taken at the moveBlocker list.
  //`---------------------------------------------------------------------------



  ///////////////////////////////
  // list of overlapable tiles //
  ///////////////////////////////
  static int[][] overlapbleMap = {
    { 0, 0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0 },
    { 0, 359, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 356, 360, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 357, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 354, 358, 0 },
    { 0, 361, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 362, 0 },
    { 0, 0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0 }
  };

  ///////////////////////////////
  // list of moveBlocker tiles //
  ///////////////////////////////

  static int[][] blockersMap = {
    { 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    4476 },
    { 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476, 4476 }
  };



  @Override
  protected void init() {
    OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();
    MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();


    HeroOverlapRules overlapRules = new HeroOverlapRules(
        new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE),
        score[0],
        life[0],
        endOfGame
                                                         );
    overlapRules.setNbOrcs(NUMBER_OF_ORCS);

    overlapProcessor.setOverlapRules(overlapRules);

    universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
    overlapRules.setUniverse(universe);

    gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
    ((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);


    // Filling up the universe with basic non movable entities and inclusion in the universe
    for (int i = 0; i < 31; ++i) {
      for (int j = 0; j < 28; ++j) {
        if (overlapbleMap[i][j] == 0) {
          if(blockersMap[i][j] != 0){
            universe.addGameEntity(mapFactory.getBlocker(new Point(j * SPRITE_SIZE , i * SPRITE_SIZE),blockersMap[i][j]));
          }
        }else{
            universe.addGameEntity(mapFactory.getOverlapable(new Point(j * SPRITE_SIZE , i * SPRITE_SIZE),overlapbleMap[i][j]));
        }
      }
    }


    //some Swords
    for(int i = 0; i < NUMBER_OF_SWORDS; i++){
      universe.addGameEntity(mapFactory.getSword(582));
    }
    //some Shields
    for(int i = 0; i < NUMBER_OF_SHIELDS; i++){

      universe.addGameEntity(mapFactory.getShield(3325));
    }
    //some potions
    for(int i = 0; i < NUMBER_OF_POTIONS; i++){

      universe.addGameEntity(mapFactory.getPotion(139));
    }

    //some friends to rescue
    for(int i = 0; i < NUMBER_OF_FRIENDS; i++){

      universe.addGameEntity(mapFactory.getFriends());
    }

    // hero definition and inclusion in the universe
    UnitEntity player = new Human(canvas,"hero","Nothing");
    GameMovableDriverDefaultImpl gameDriver = new GameMovableDriverDefaultImpl();
    MoveStrategyKeyboardWithStop keyStr = new MoveStrategyKeyboardWithStop();
    gameDriver.setStrategy(keyStr);
    gameDriver.setmoveBlockerChecker(moveBlockerChecker);
    canvas.addKeyListener(keyStr);
    player.setDriver(gameDriver);
    player.setPosition(new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE));
    universe.addGameEntity(player);

    //populate the orcs
    UnitEntity orcs;
    for(int i = 0; i < NUMBER_OF_ORCS; i++){
      GameMovableDriverDefaultImpl orcDriver = new GameMovableDriverDefaultImpl();
      MoveStrategyRandom ranStr = new MoveStrategyRandom();
      orcDriver.setStrategy(ranStr);
      orcDriver.setmoveBlockerChecker(moveBlockerChecker);
      orcs = new Orc(canvas,"test","Full");
      orcs.setDriver(orcDriver);
      orcs.setPosition(new Point(getRandom(3, 25) * SPRITE_SIZE, getRandom(3, 28) * SPRITE_SIZE));
      universe.addGameEntity(orcs);
    }

  }
  private int getRandom(int min , int max){
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }


  }

package projectAL.app;

import java.util.ArrayList;

import gameframework.core.GameDefaultImpl;
import gameframework.core.GameLevel;

import projectAL.levels.GameLvlArena;

/**
 * Hello world!
 *
 */
public class App 
{

	public static void main(String[] args) {
    GameDefaultImpl g = new GameDefaultImpl();
    ArrayList<GameLevel> levels = new ArrayList<>();

    levels.add(new GameLvlArena(g)); // only one level is available

    g.setLevels(levels);
    g.start();
  }
}

package projectAL.movestrategy;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gameframework.moves_rules.MoveStrategy;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;

/**
 * {@link MoveStrategy} which listens to the keyboard and answers new
 * {@link SpeedVector speed vectors} based on what the user typed.
 */

public class MoveStrategyKeyboardWithStop extends KeyAdapter implements MoveStrategy {
	protected SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(0, 0));
  int speed = 8;

	public SpeedVector getSpeedVector() {
		return speedVector;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		switch (keycode) {
      case KeyEvent.VK_RIGHT:
        speedVector.setDirection(new Point(1, 0));
        speedVector.setSpeed(speed);
        break;
      case KeyEvent.VK_LEFT:
        speedVector.setDirection(new Point(-1, 0));
        speedVector.setSpeed(speed);
        break;
      case KeyEvent.VK_UP:
        speedVector.setDirection(new Point(0, -1));
        speedVector.setSpeed(speed);
        break;
      case KeyEvent.VK_DOWN:
        speedVector.setDirection(new Point(0, 1));
        speedVector.setSpeed(speed);
        break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		int keycode = event.getKeyCode();
		switch (keycode) {
      case KeyEvent.VK_RIGHT:
        speedVector.setDirection(new Point(1, 0));
        speedVector.setSpeed(0);
        break;
      case KeyEvent.VK_LEFT:
        speedVector.setDirection(new Point(-1, 0));
        speedVector.setSpeed(0);
        break;
      case KeyEvent.VK_UP:
        speedVector.setDirection(new Point(0, -1));
        speedVector.setSpeed(0);
        break;
      case KeyEvent.VK_DOWN:
        speedVector.setDirection(new Point(0, 1));
        speedVector.setSpeed(0);
        break;
		}
	}

}

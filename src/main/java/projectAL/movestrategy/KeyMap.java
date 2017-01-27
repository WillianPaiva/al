package projectAL.movestrategy;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class KeyMap extends KeyAdapter {
  private boolean attacking = false;

	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		switch (keycode) {
      case KeyEvent.VK_A:
        this.attacking = true;
        break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		int keycode = event.getKeyCode();
		switch (keycode) {
      case KeyEvent.VK_A:
        this.attacking = false;
        break;
		}
	}

	/**
	 * @return the attacking
	 */
	public boolean isAttacking() {
		return attacking;
	}

}

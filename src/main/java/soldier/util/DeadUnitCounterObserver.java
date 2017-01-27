/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit Bordeaux.
 */
package soldier.util;

import soldier.core.Unit;
import soldier.observer_util.Observer;

public class DeadUnitCounterObserver implements Observer<Unit> {
	private int deadUnit = 0;

	@Override
	public void update(Unit unit) {
		if (!unit.alive()) {
			++deadUnit;
			// System.out.println(unit.getName() + " is the " + deadUnit +
			// "th death, please stop war !!!");
			unit.removeObserver(this);
		}
	}

	public int getNumberOfDeadUnits() {
		return deadUnit;
	}
}
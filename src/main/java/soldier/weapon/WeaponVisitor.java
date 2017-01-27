/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit Bordeaux.
 */
package soldier.weapon;

import soldier.core.WeaponAttack;
import soldier.core.WeaponDefense;

public interface WeaponVisitor {
	void visit(WeaponAttack s);

	void visit(WeaponDefense s);
}

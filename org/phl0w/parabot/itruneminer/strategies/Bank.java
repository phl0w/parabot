package org.phl0w.parabot.itruneminer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itruneminer.iTRuneMiner;
import org.phl0w.parabot.itruneminer.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Bank implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.isFull();
    }

    @Override
    public void execute() {
        iTRuneMiner.status = "Banking";
        if (Utilities.isBankOpen()) {
            iTRuneMiner.mined += Inventory.getCount(iTRuneMiner.RUNE_ORE_ID + 1);
            Menu.sendAction(646, 2481, 0, 23412);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.isEmpty();
                }
            }, 2000);
        } else {
            final SceneObject[] depositBoxes = SceneObjects.getNearest(9398);
            if (depositBoxes.length > 0) {
                final SceneObject depositBox = depositBoxes[0];
                if (depositBox != null) {
                    depositBox.interact(0);
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Utilities.isBankOpen();
                        }
                    }, 2000);
                }
            }
        }
    }
}

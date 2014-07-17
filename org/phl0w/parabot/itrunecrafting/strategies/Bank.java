package org.phl0w.parabot.itrunecrafting.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itrunecrafting.iTRunecrafting;
import org.phl0w.parabot.itrunecrafting.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Bank implements Strategy {
    @Override
    public boolean activate() {
        return Utilities.isLoggedIn() && Inventory.getCount(iTRunecrafting.RUNE_ESSENCE + 1) == 0;
    }

    @Override
    public void execute() {
        if (!Utilities.isBankOpen()) {
            final SceneObject[] banks = SceneObjects.getNearest(2213);
            if (banks.length > 0) {
                final SceneObject bank = banks[0];
                if (bank != null) {
                    bank.interact(0);
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Utilities.isBankOpen();
                        }
                    }, 2000);
                }
            }
        } else {
            Utilities.withdrawItem(iTRunecrafting.RUNE_ESSENCE, 0);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getCount(iTRunecrafting.RUNE_ESSENCE + 1) > 0;
                }
            }, 2000);
        }
    }
}

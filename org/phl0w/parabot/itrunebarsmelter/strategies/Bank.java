package org.phl0w.parabot.itrunebarsmelter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.italtar.utilities.Utilities;
import org.phl0w.parabot.itrunebarsmelter.iTRuneBarSmelter;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Bank implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(iTRuneBarSmelter.COAL_ORE_ID + 1) < 4 || Inventory.getCount(iTRuneBarSmelter.RUNE_ORE_ID + 1) == 0;
    }

    @Override
    public void execute() {
        iTRuneBarSmelter.status = "Banking";
        if (Utilities.isBankOpen()) {
            iTRuneBarSmelter.smelted += Inventory.getCount(iTRuneBarSmelter.RUNE_BAR_ID + 1);
            Menu.sendAction(646, 2481, 0, 23412);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.isEmpty();
                }
            }, 2000);
            Menu.sendAction(78, iTRuneBarSmelter.RUNE_ORE_ID, 0, 5382);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getCount(iTRuneBarSmelter.RUNE_ORE_ID + 1) > 0;
                }
            }, 1200);
            Menu.sendAction(53, iTRuneBarSmelter.COAL_ORE_ID, 1, 5382);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getCount(iTRuneBarSmelter.COAL_ORE_ID + 1) > 4 && Inventory.getCount(iTRuneBarSmelter.RUNE_ORE_ID + 1) > 0;
                }
            }, 1200);
        } else {
            final SceneObject[] bankBooths = SceneObjects.getNearest(2213);
            if (bankBooths.length > 0) {
                final SceneObject bankBooth = bankBooths[0];
                if (bankBooth != null) {
                    bankBooth.interact(0);
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

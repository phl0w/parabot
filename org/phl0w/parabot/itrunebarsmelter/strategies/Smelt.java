package org.phl0w.parabot.itrunebarsmelter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itrunebarsmelter.iTRuneBarSmelter;
import org.phl0w.parabot.itrunebarsmelter.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Smelt implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(iTRuneBarSmelter.RUNE_ORE_ID + 1) > 0 && Inventory.getCount(iTRuneBarSmelter.COAL_ORE_ID + 1) > 4;
    }

    @Override
    public void execute() {
        iTRuneBarSmelter.status = "Smelting";
        if (Utilities.isSmeltScreenOpen()) {
            Menu.sendAction(315, 43302912, 232, 7448);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getCount(iTRuneBarSmelter.RUNE_ORE_ID + 1) == 0 && Inventory.getCount(iTRuneBarSmelter.COAL_ORE_ID + 1) < 4;
                }
            }, 15000);
        } else {
            final SceneObject[] smelteries = SceneObjects.getNearest(2643);
            if (smelteries.length > 0) {
                final SceneObject smeltery = smelteries[0];
                if (smeltery != null) {
                    smeltery.interact(0);
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Utilities.isSmeltScreenOpen();
                        }
                    }, 2500);
                }
            }
        }
    }
}

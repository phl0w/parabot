package org.phl0w.parabot.itrunecrafting.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itrunecrafting.iTRunecrafting;
import org.phl0w.parabot.itrunecrafting.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Craft implements Strategy {
    @Override
    public boolean activate() {
        return Utilities.isLoggedIn() && Inventory.getCount(iTRunecrafting.RUNE_ESSENCE + 1) > 0;
    }

    @Override
    public void execute() {
        final SceneObject[] altars = SceneObjects.getNearest(iTRunecrafting.selectedAltar.getObjectId());
        if (altars.length > 0) {
            final SceneObject altar = altars[0];
            if (altar != null) {
                altar.interact(0);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.getCount(iTRunecrafting.RUNE_ESSENCE + 1) == 0;
                    }
                }, 2000);
                final int newCount = Utilities.getStackSize();
                iTRunecrafting.crafted += newCount - iTRunecrafting.prevCount;
                iTRunecrafting.prevCount = newCount;
            }
        }
    }
}

package org.phl0w.parabot.italtar.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.italtar.iTAltar;
import org.phl0w.parabot.italtar.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Bank implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(iTAltar.selectedBone.getItemId() + 1) == 0 || Utilities.isBankOpen();
    }

    @Override
    public void execute() {
        if (Utilities.isBankOpen()) {
            if (Inventory.getCount(iTAltar.selectedBone.getItemId() + 1) == 0) {
                Utilities.withdrawItem(iTAltar.selectedBone.getItemId(), 0);
                Time.sleep(1000, 2000);
            }
            // Close bank
            Menu.sendAction(200, 5325, 9, 5384);
            Time.sleep(800, 1600);
        } else {
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
        }
    }
}

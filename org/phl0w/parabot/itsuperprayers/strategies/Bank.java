package org.phl0w.parabot.itsuperprayers.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itsuperprayers.iTSuperPrayers;
import org.phl0w.parabot.itsuperprayers.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Bank implements Strategy {

    public boolean activate() {
        return Utilities.isLoggedIn() && Inventory.getCount(6811) <= 4 || Inventory.getCount(2435) <= 4 || Utilities.isBankOpen();
    }

    public void execute() {
        iTSuperPrayers.status = "Banking";
        if (!Utilities.isLoggedIn()) {
            return;
        }
        if (!Utilities.isBankOpen()) {
            SceneObject banker = SceneObjects.getClosest(2213);
            if (banker == null || banker.distanceTo() > 20) {
                return;
            }
            Menu.sendAction(502, banker.getHash(), banker.getLocalRegionX(), banker.getLocalRegionY());
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Utilities.isBankOpen();
                }
            }, 2000);
        } else {
            if (Inventory.getCount(6811) <= 4 || Inventory.getCount(2435) <= 4) {
                iTSuperPrayers.potionsMade += Inventory.getCount(14597);
                Menu.sendAction(646, 2481, 0, 23412);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.getCount() == 0;
                    }
                }, 750);
                Menu.sendAction(867, 2434, 0, 5382);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.getCount(2435) > 0 || Inventory.getCount(6811) > 0;
                    }
                }, 750);
                Menu.sendAction(867, 6810, 1, 5382);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.getCount(2435) > 0 && Inventory.getCount(6811) > 0;
                    }
                }, 750);
            }
            Menu.sendAction(200, 5325, 9, 5384);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !Utilities.isBankOpen() && Inventory.getCount(2435) > 0 && Inventory.getCount(6811) > 0;
                }
            }, 2000);
            Time.sleep(350, 550);
        }
    }
}

package org.phl0w.parabot.itherblore.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itherblore.iTHerblore;
import org.phl0w.parabot.itherblore.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Bank implements Strategy {

    public boolean activate() {
        return Utilities.isLoggedIn() && Inventory.getCount(iTHerblore.item1 + 1) <= 4 || Inventory.getCount(iTHerblore.item2 + 1) <= 4 || Utilities.isBankOpen();
    }

    public void execute() {
        iTHerblore.status = "Banking";
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
            if (Inventory.getCount(iTHerblore.item1 + 1) <= 4 || Inventory.getCount(iTHerblore.item2 + 1) <= 4) {
                iTHerblore.potionsMade += Inventory.getCount(iTHerblore.finishedPotionId + 1);
                Menu.sendAction(646, 2481, 0, 23412);
                if (iTHerblore.hasRelogged) {
                    Menu.sendAction(431, iTHerblore.item1, 0, 5382);
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Utilities.isWithdrawAmountScreenOpen();
                        }
                    }, 2000);
                    Keyboard.getInstance().sendKeys("14");
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Inventory.getCount(iTHerblore.item1 + 1) > 0 || Inventory.getCount(iTHerblore.item2 + 1) > 0;
                        }
                    }, 750);
                    Menu.sendAction(867, iTHerblore.item2, 1, 5382);
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Inventory.getCount(iTHerblore.item1 + 1) > 0 && Inventory.getCount(iTHerblore.item2 + 1) > 0;
                        }
                    }, 750);
                    iTHerblore.hasRelogged = false;
                } else {
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Inventory.getCount() == 0;
                        }
                    }, 750);
                    Menu.sendAction(867, iTHerblore.item1, 0, 5382);
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Inventory.getCount(iTHerblore.item1 + 1) > 0 || Inventory.getCount(iTHerblore.item2 + 1) > 0;
                        }
                    }, 750);
                    Menu.sendAction(867, iTHerblore.item2, 1, 5382);
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Inventory.getCount(iTHerblore.item1 + 1) > 0 && Inventory.getCount(iTHerblore.item2 + 1) > 0;
                        }
                    }, 750);
                }
            }
            Menu.sendAction(200, 5325, 9, 5384);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !Utilities.isBankOpen() && Inventory.getCount(iTHerblore.item1 + 1) > 0 && Inventory.getCount(iTHerblore.item2 + 1) > 0;
                }
            }, 2000);
            Time.sleep(350, 550);
        }
    }
}

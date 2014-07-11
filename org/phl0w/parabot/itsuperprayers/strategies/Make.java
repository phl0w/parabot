package org.phl0w.parabot.itsuperprayers.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itsuperprayers.iTSuperPrayers;
import org.phl0w.parabot.itsuperprayers.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;

public class Make implements Strategy {

    public boolean activate() {
        return Utilities.isLoggedIn() && Inventory.getCount(6811) > 4 && Inventory.getCount(2435) > 4 && !Utilities.isBankOpen();
    }

    public void execute() {
        iTSuperPrayers.status = "Making pots";
        if (Inventory.getCount() == 28) {
            for (int i = 0; i < 14; i++) {
                Menu.sendAction(447, 6810, 27, 3214);
                Time.sleep(10);
                Menu.sendAction(870, 2434, 13, 3214);
                Time.sleep(100);
            }
        } else if (Inventory.getCount() == 20) {
            for (int i = 0; i < 10; i++) {
                Menu.sendAction(447, 6810, 9, 3214);
                Time.sleep(10);
                Menu.sendAction(870, 2434, 19, 3214);
                Time.sleep(100);
            }
        } else {
            for (int i = 0; i < 14; i++) {
                Menu.sendAction(447, 6810, i + 14, 3214);
                Time.sleep(10);
                Menu.sendAction(870, 2434, i, 3214);
                Time.sleep(100);
            }
        }
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.getCount(6811) <= 4;
            }
        }, 1600);
    }
}

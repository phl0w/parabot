package org.phl0w.parabot.itpohchopper.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itpohchopper.iTPOHChopper;
import org.phl0w.parabot.itpohchopper.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

public class Banker implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.isFull();
    }

    @Override
    public void execute() {
        iTPOHChopper.status = "Banking";
        final Npc[] nearestServants = Npcs.getNearest(4241);
        if (nearestServants.length > 0) {
            final Npc nearestServant = nearestServants[0];
            if (nearestServant != null) {
                nearestServant.interact(0);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Utilities.isOpenBankScreenOpen() || Utilities.isGuestOpenBankScreenOpen();
                    }
                }, 2000);
            }
        }
        if (Utilities.isOpenBankScreenOpen()) {
            Menu.sendAction(315, 893, 262, 2482);
        } else {
            Menu.sendAction(315, 21397504, 241, 2471);
        }
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Utilities.isBankOpen();
            }
        }, 2000);
        iTPOHChopper.logsChopped += Inventory.getCount(iTPOHChopper.selectedTree.getItemId() + 1);
        Menu.sendAction(646, 2481, 0, 23412);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.isEmpty();
            }
        }, 2000);
    }
}

package org.phl0w.parabot.itmahoganybenches.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itmahoganybenches.iTMahoganyBenches;
import org.phl0w.parabot.itmahoganybenches.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

public class Banker implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(8782 + 1) < 12;
    }

    @Override
    public void execute() {
        iTMahoganyBenches.status = "Banking";
        final Npc[] nearestServants = Npcs.getNearest(4241);
        if (nearestServants.length > 0) {
            final Npc nearestServant = nearestServants[0];
            if (nearestServant != null) {
                nearestServant.interact(0);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Utilities.isOpenBankScreenOpen();
                    }
                }, 2000);
            }
        }
        Menu.sendAction(315, 893, 262, 2482);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Utilities.isBankOpen();
            }
        }, 2000);

        Menu.sendAction(53, 8782, 0, 5382);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.getCount(8778) > 11;
            }
        }, 2000);
    }
}

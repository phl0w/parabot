package org.phl0w.parabot.itpohplanker.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itpohplanker.iTPOHPlanker;
import org.phl0w.parabot.itpohplanker.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

public class Banker implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(iTPOHPlanker.selectedLog.getLogId() + 1) == 0;
    }

    @Override
    public void execute() {
        iTPOHPlanker.status = "Banking";
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

        iTPOHPlanker.logsPlanked += Inventory.getCount(iTPOHPlanker.selectedLog.getPlankId() + 1);
        Menu.sendAction(432, iTPOHPlanker.selectedLog.getPlankId(), 2, 5064);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.getCount(iTPOHPlanker.selectedLog.getPlankId()) == 0;
            }
        }, 2000);

        Menu.sendAction(53, iTPOHPlanker.selectedLog.getLogId(), 0, 5382);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.getCount(iTPOHPlanker.selectedLog.getLogId()) > 0;
            }
        }, 2000);
    }
}

package org.phl0w.parabot.itpohplanker.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itpohplanker.iTPOHPlanker;
import org.phl0w.parabot.itpohplanker.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Plank implements Strategy {

    public boolean activate() {
        return Inventory.getCount(iTPOHPlanker.selectedLog.getLogId() + 1) > 0;
    }

    @Override
    public void execute() {
        iTPOHPlanker.status = "Planking";
        final SceneObject workbench = SceneObjects.getClosest(iTPOHPlanker.WORKBENCHES);
        if (workbench != null) {
            Menu.sendAction(502, workbench.getHash(), workbench.getLocalRegionX(), workbench.getLocalRegionY());
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Utilities.isPlankScreenOpen();
                }
            }, 2000);
            Menu.sendAction(315, 1298308531, 12, 2461);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getCount(iTPOHPlanker.selectedLog.getLogId()) == 0;
                }
            }, 2000);
            Time.sleep(1000);
        }
    }
}

package org.phl0w.parabot.itmahoganybenches.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itmahoganybenches.iTMahoganyBenches;
import org.phl0w.parabot.itmahoganybenches.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class HandleObject implements Strategy {

    @Override
    public boolean activate() {
        return !Utilities.isMakeScreenOpen() && Inventory.getCount(8782 + 1) >= 12;
    }

    @Override
    public void execute() {
        iTMahoganyBenches.status = "HandleObj";
        final SceneObject bench = SceneObjects.getClosest(13305);
        if (bench != null) {
            Menu.sendAction(1062, bench.getHash(), bench.getLocalRegionX(), bench.getLocalRegionY());
            Time.sleep(800, 1200);
        } else {
            Menu.sendAction(1062, 1326650151, 39, 38);
            //[index: 1, action1: 1326653478, action2: 38, action3: 64, id: 1062]
            Menu.sendAction(1062, 1326653478, 38, 64);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Utilities.isMakeScreenOpen();
                }
            }, 2000);
        }
    }
}

package org.phl0w.parabot.itoaklarders.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itoaklarders.iTOakLarders;
import org.phl0w.parabot.itoaklarders.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class HandleObject implements Strategy {

    @Override
    public boolean activate() {
        return !Utilities.isMakeScreenOpen() && Inventory.getCount(8778 + 1) >= 8;
    }

    @Override
    public void execute() {
        iTOakLarders.status = "HandleObj";
        final SceneObject larder = SceneObjects.getClosest(13566);
        if (larder != null) {
            Menu.sendAction(1062, larder.getHash(), larder.getLocalRegionX(), larder.getLocalRegionY());
            Time.sleep(800, 1200);
        } else {
            Menu.sendAction(1062, 1326109736, 40, 40);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Utilities.isMakeScreenOpen();
                }
            }, 2000);
        }
    }
}

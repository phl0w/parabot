package org.phl0w.parabot.itruneminer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itruneminer.iTRuneMiner;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Mine implements Strategy {
    @Override
    public boolean activate() {
        return Players.getMyPlayer().getAnimation() == -1 || Players.getMyPlayer().getAnimation() == 1353 && !Inventory.isFull();
    }

    @Override
    public void execute() {
        iTRuneMiner.status = "Mining";
        final SceneObject[] rocks = SceneObjects.getNearest(2106, 2107);
        if (rocks.length > 0) {
            final SceneObject rock = rocks[0];
            if (rock != null) {
                rock.interact(0);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Players.getMyPlayer().getAnimation() != -1;
                    }
                }, 2000);
            }
        }
    }
}

package org.phl0w.parabot.itmahoganybenches.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itmahoganybenches.iTMahoganyBenches;
import org.phl0w.parabot.itmahoganybenches.utilities.Utilities;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;

public class Make implements Strategy {
    @Override
    public boolean activate() {
        return Utilities.isMakeScreenOpen() && Players.getMyPlayer().getAnimation() == -1;
    }

    @Override
    public void execute() {
        iTMahoganyBenches.status = "Make";
        Menu.sendAction(632, 8113, 2, 26702);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return !Utilities.isMakeScreenOpen();
            }
        }, 2000);
        iTMahoganyBenches.made++;
    }
}

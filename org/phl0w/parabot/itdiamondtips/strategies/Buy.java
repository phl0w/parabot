package org.phl0w.parabot.itdiamondtips.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itdiamondtips.iTDiamondTips;
import org.phl0w.parabot.util.Utilities;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;

public class Buy implements Strategy {
    @Override
    public boolean activate() {
        return Loader.getClient().getOpenInterfaceId() == 3824 && Utilities.isLoggedIn() && !Inventory.isFull() && Inventory.getCount(iTDiamondTips.UNCUT_DIAMOND + 1) == 0;
    }

    @Override
    public void execute() {
        Menu.sendAction(53, iTDiamondTips.UNCUT_DIAMOND, 6, 3900);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.getCount(iTDiamondTips.UNCUT_DIAMOND + 1) > 0;
            }
        }, 3000);
        Menu.sendAction(200, 1617, 6, 3902);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Loader.getClient().getOpenInterfaceId() == -1;
            }
        }, 2000);
    }
}

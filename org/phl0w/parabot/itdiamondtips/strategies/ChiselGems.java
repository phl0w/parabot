package org.phl0w.parabot.itdiamondtips.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itdiamondtips.iTDiamondTips;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;

public class ChiselGems implements Strategy {

    @Override
    public boolean activate() {
        return Inventory.getCount(iTDiamondTips.UNCUT_DIAMOND + 1) > 0 && Players.getMyPlayer().getAnimation() == -1 && Loader.getClient().getOpenInterfaceId() == -1;
    }

    @Override
    public void execute() {
        Menu.sendAction(447, iTDiamondTips.CHISEL, getFirstItemSlot(iTDiamondTips.CHISEL), 3214);
        Time.sleep(300, 500);
        Menu.sendAction(870, iTDiamondTips.UNCUT_DIAMOND, getFirstItemSlot(iTDiamondTips.UNCUT_DIAMOND), 3214);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Loader.getClient().getBackDialogId() == 2492;
            }
        }, 2000);
        Menu.sendAction(315, 1617, 15, 2498);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.getCount(iTDiamondTips.UNCUT_DIAMOND + 1) == 0;
            }
        }, 50000);
    }

    private int getFirstItemSlot(final int id) {
        for (int i = 0; i < Inventory.getItems().length; i++) {
            if (Inventory.getItems()[i].getId() - 1 == id) {
                return i;
            }
        }
        return 0;
    }
}

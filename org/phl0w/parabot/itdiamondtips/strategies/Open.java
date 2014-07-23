package org.phl0w.parabot.itdiamondtips.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itdiamondtips.iTDiamondTips;
import org.phl0w.parabot.util.Utilities;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

public class Open implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(iTDiamondTips.CUT_DIAMOND + 1) == 0 && Inventory.getCount(iTDiamondTips.UNCUT_DIAMOND + 1) == 0 &&
                Utilities.isLoggedIn() && Loader.getClient().getOpenInterfaceId() != 3824;
    }

    @Override
    public void execute() {
        final Npc[] shopkeepers = Npcs.getNearest(545);
        if (shopkeepers.length > 0) {
            final Npc shopkeeper = shopkeepers[0];
            if (shopkeeper != null) {
                shopkeeper.interact(0);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Loader.getClient().getOpenInterfaceId() == 3824;
                    }
                }, 2000);
            }
        }
    }
}

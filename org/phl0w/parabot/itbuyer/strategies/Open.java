package org.phl0w.parabot.itbuyer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itbuyer.iTBuyer;
import org.phl0w.parabot.itbuyer.utilities.Utilities;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

public class Open implements Strategy {
    @Override
    public boolean activate() {
        return Utilities.isLoggedIn() && !Utilities.isShopScreenOpen() && Utilities.getNearestRandom() == null;
    }

    @Override
    public void execute() {
        final Npc[] shopkeepers = Npcs.getNearest(iTBuyer.npcId);
        if (shopkeepers.length > 0) {
            final Npc shopkeeper = shopkeepers[0];
            if (shopkeeper != null) {
                shopkeeper.interact(0);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Utilities.isShopScreenOpen();
                    }
                }, 3000);
            }
        }
    }
}

package org.phl0w.parabot.itbuyer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itbuyer.iTBuyer;
import org.phl0w.parabot.itbuyer.utilities.Utilities;
import org.rev317.min.api.methods.Menu;

public class Buy implements Strategy {
    @Override
    public boolean activate() {
        return Utilities.isShopScreenOpen();
    }

    @Override
    public void execute() {
        iTBuyer.status = "Buying";
        Menu.sendAction(53, iTBuyer.itemId, iTBuyer.itemSlot, 3900);
        iTBuyer.status = "Sleeping";
        iTBuyer.sleepTimer = System.currentTimeMillis() + iTBuyer.sleepDelay;
        Time.sleep(iTBuyer.sleepDelay);
    }
}

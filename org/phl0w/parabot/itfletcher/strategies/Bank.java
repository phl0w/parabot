package org.phl0w.parabot.itfletcher.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.api.Banking;
import org.phl0w.parabot.itfletcher.iTFletcher;
import org.phl0w.parabot.itfletcher.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;

public class Bank implements Strategy {
    @Override
    public boolean activate() {
        return Utilities.isLoggedIn() && Inventory.getCount(iTFletcher.item.getLogId() + 1) == 0;
    }

    @Override
    public void execute() {
        iTFletcher.status = "Banking";
        if (!Banking.isOpen()) {
            Banking.open();
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Banking.isOpen();
                }
            }, 2000);
        } else {
            iTFletcher.fletched += Inventory.getCount(iTFletcher.item.getProductId() + 1);
            Banking.depositItem(iTFletcher.item.getProductId(), 1, Banking.Option.ALL_BUT_ONE);
            Time.sleep(300, 500);
            Banking.withdrawItem(iTFletcher.item.getLogId(), 0, Banking.Option.ALL);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getCount(iTFletcher.item.getLogId() + 1) > 0;
                }
            }, 2000);
            Banking.close();
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !Banking.isOpen();
                }
            }, 2000);
        }
    }
}

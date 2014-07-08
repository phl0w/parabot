package org.phl0w.parabot.italtar.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.italtar.iTAltar;
import org.phl0w.parabot.italtar.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;

public class UseOnAltar implements Strategy {

    @Override
    public boolean activate() {
        return Inventory.getCount(iTAltar.selectedBone.getItemId() + 1) > 0;
    }

    @Override
    public void execute() {
        Menu.sendAction(447, 536, 0, 3214);

        Menu.sendAction(62, 1248040364, 44, 43);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Utilities.isOfferScreenOpen();
            }
        }, 15000);
        Menu.sendAction(315, 21446656, 45, 2498);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.getCount(iTAltar.selectedBone.getItemId() + 1) == 0;
            }
        }, 50000);
    }
}

package org.phl0w.parabot.itfletcher.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.api.Banking;
import org.phl0w.parabot.itfletcher.iTFletcher;
import org.phl0w.parabot.itfletcher.utilities.FletchData;
import org.phl0w.parabot.itfletcher.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;

import java.awt.event.KeyEvent;

public class Fletch implements Strategy {
    @Override
    public boolean activate() {
        return Utilities.isLoggedIn() && Inventory.getCount(iTFletcher.item.getLogId() + 1) > 0 && Players.getMyPlayer().getAnimation() == -1 &&
                !Banking.isOpen();
    }

    @Override
    public void execute() {
        iTFletcher.status = "Fletching";
        if (!Utilities.isFletchBackDialogueOpen()) {
            Menu.sendAction(447, 946, 0, 3214);
            Time.sleep(300, 500);
            Menu.sendAction(870, iTFletcher.item.getLogId(), Utilities.getFirstItemSlot(iTFletcher.item.getLogId()), 3214);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Utilities.isFletchBackDialogueOpen();
                }
            }, 2000);
        } else {
            final FletchData fd = iTFletcher.item;
            Menu.sendAction(fd.getActionId(), fd.getAction1(), fd.getAction2(), fd.getAction3());
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !Utilities.isFletchBackDialogueOpen() && Utilities.getInputState() == 1;
                }
            }, 2000);
            Utilities.setInputString("27");
            Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getAnimation() != -1;
                }
            }, 2000);
        }
    }
}

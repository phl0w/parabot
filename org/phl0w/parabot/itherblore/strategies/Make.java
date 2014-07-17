package org.phl0w.parabot.itherblore.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itherblore.iTHerblore;
import org.phl0w.parabot.itherblore.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Skill;

public class Make implements Strategy {

    public boolean activate() {
        return Utilities.isLoggedIn() && Inventory.getCount(iTHerblore.item1 + 1) > 4 && Inventory.getCount(iTHerblore.item2 + 1) > 4 && !Utilities.isBankOpen();
    }

    public void execute() {
        iTHerblore.status = "Making pots";
        if (!iTHerblore.fetchedStats) {
            iTHerblore.startLevel = Utilities.getLevel(Skill.HERBLORE);
            iTHerblore.startXp = Skill.HERBLORE.getExperience();
            iTHerblore.fetchedStats = true;
        }
        if (Inventory.getCount() == 28) {
            for (int i = 0; i < 14; i++) {
                Menu.sendAction(447, iTHerblore.item1, 27, 3214);
                Time.sleep(10);
                Menu.sendAction(870, iTHerblore.item2, 13, 3214);
                Time.sleep(100);
            }
        } else if (Inventory.getCount() == 20 && (Inventory.getCount(iTHerblore.item1 + 1) != 6 || Inventory.getCount(iTHerblore.item2) != 6)) {
            for (int i = 0; i < 10; i++) {
                Menu.sendAction(447, iTHerblore.item1, 9, 3214);
                Time.sleep(10);
                Menu.sendAction(870, iTHerblore.item2, 19, 3214);
                Time.sleep(100);
            }
        } else {
            for (int i = 0; i < 14; i++) {
                Menu.sendAction(447, iTHerblore.item1, i + 14, 3214);
                Time.sleep(10);
                Menu.sendAction(870, iTHerblore.item2, i, 3214);
                Time.sleep(100);
            }
        }
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.getCount(iTHerblore.item1) <= 4;
            }
        }, 1600);
    }
}

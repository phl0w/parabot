package org.phl0w.parabot.itimplinghunter.utilities;

import org.phl0w.parabot.itimplinghunter.iTImplingHunter;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.Item;

import java.text.DecimalFormat;

public class Utilities {

    public static int getLevel(final Skill skill) {
        if (skill.getRealLevel() > 99) {
            return 99;
        }
        return skill.getRealLevel();
    }

    public static String formatRuntime(final long runtime) {
        final DecimalFormat nf = new DecimalFormat("00");
        long millis = System.currentTimeMillis() - runtime;
        final long hours = millis / (1000 * 60 * 60);
        millis -= hours * (1000 * 60 * 60);
        final long minutes = millis / (1000 * 60);
        millis -= minutes * (1000 * 60);
        final long seconds = millis / 1000;
        return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
    }

    public static int getMoney() {
        if (Inventory.getItems(996).length == 1) {
            final Item money = Inventory.getItems(996)[0];
            if (money != null) {
                return money.getStackSize();
            }
        }
        return -1;
    }

    public static int getPerHour(final int variable) {
        return (int) (variable * 3600000D / (System.currentTimeMillis() - iTImplingHunter.startTime));
    }
}

package org.phl0w.parabot.itruneminer.utilities;

import org.phl0w.parabot.itruneminer.iTRuneMiner;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;

import java.text.DecimalFormat;

public class Utilities {

    public static boolean isBankOpen() {
        return Loader.getClient().getOpenInterfaceId() == 23350;
    }

    public static boolean isLoggedIn() {
        return SceneObjects.getNearest().length > 0;
    }

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

    public static int getPerHour(final int variable) {
        return (int) (variable * 3600000D / (System.currentTimeMillis() - iTRuneMiner.startTime));
    }
}

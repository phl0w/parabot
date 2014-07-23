package org.phl0w.parabot.util;

import org.phl0w.parabot.itdiamondtips.iTDiamondTips;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Skill;

import java.lang.reflect.Field;
import java.text.DecimalFormat;

public class Utilities {

    public static boolean isLoggedIn() {
        try {
            final Class<?> c = Loader.getClient().getClass();
            final Field f = c.getDeclaredField("aj");
            return f.getBoolean(Loader.getClient());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
}

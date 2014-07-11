package org.phl0w.parabot.itbuyer.utilities;

import org.phl0w.parabot.itbuyer.iTBuyer;
import org.rev317.min.Loader;

import java.text.DecimalFormat;

public class Utilities {

    public static boolean isShopScreenOpen() {
        return Loader.getClient().getOpenInterfaceId() == 3824;
    }

    public static int getPerHour(final int variable) {
        return (int) (variable * 3600000D / (System.currentTimeMillis() - iTBuyer.startTime));
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

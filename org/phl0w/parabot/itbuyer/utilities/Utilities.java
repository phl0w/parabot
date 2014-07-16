package org.phl0w.parabot.itbuyer.utilities;

import org.phl0w.parabot.itbuyer.iTBuyer;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Npc;

import java.text.DecimalFormat;

public class Utilities {

    public static boolean isLoggedIn() {
        return SceneObjects.getNearest().length > 0;
    }

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

    public static int getStackSize() {
        final Item[] items = Inventory.getItems(iTBuyer.itemId + 1);
        if (items.length > 0) {
            final Item item = items[0];
            if (item != null) {
                return item.getStackSize();
            }
        }
        return 0;
    }

    public static Npc getNearestRandom() {
        int[] randoms = {410, 1091, 3117, 3022, 3351, 409};
        final Npc[] nearest = Npcs.getNearest(randoms);
        if (nearest.length > 0) {
            for (Npc n : nearest) {
                if (n != null) {
                    if (n.getLocation().distanceTo() < 3) {
                        return n;
                    }
                }
            }
        }
        return null;
    }
}

package org.phl0w.parabot.itfletcher.utilities;

import org.phl0w.parabot.itfletcher.iTFletcher;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;

import java.lang.reflect.Field;
import java.text.DecimalFormat;

public class Utilities {

    public static boolean isLoggedIn2() {
        return SceneObjects.getNearest().length > 0;
    }

    public static boolean isFletchBackDialogueOpen() {
        return Loader.getClient().getBackDialogId() == 8899 || Loader.getClient().getBackDialogId() == 8880 ||
                Loader.getClient().getBackDialogId() == 8866;
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
        return (int) (variable * 3600000D / (System.currentTimeMillis() - iTFletcher.startTime));
    }

    public static String[] getList() {
        final FletchData[] data = FletchData.values();
        final String[] names = new String[data.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = data[i].name();
        }
        return names;
    }

    public static FletchData getItem(String name) {
        for (final FletchData fd : FletchData.values()) {
            if (fd.name().equalsIgnoreCase(name)) {
                return fd;
            }
        }
        return FletchData.MAGIC_LONGBOW;
    }

    public static int getFirstItemSlot(final int id) {
        for (int i = 0; i < Inventory.getItems().length; i++) {
            if (Inventory.getItems()[i].getId() - 1 == id) {
                return i;
            }
        }
        return 0;
    }

    public static int getInputState() {
        try {
            final Class<?> c = Loader.getClient().getClass();
            final Field f = c.getDeclaredField("aq");
            f.setAccessible(true); // it's a private field
            return f.getInt(Loader.getClient());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

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

    public static void setInputString(final String str) {
        try {
            final Class<?> c = Loader.getClient().getClass();
            final Field f = c.getDeclaredField("eY");
            f.setAccessible(true);
            f.set(Loader.getClient(), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

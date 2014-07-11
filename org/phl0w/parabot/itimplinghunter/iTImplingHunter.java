package org.phl0w.parabot.itimplinghunter;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itimplinghunter.strategies.CatchImpling;
import org.phl0w.parabot.itimplinghunter.utilities.Impling;
import org.phl0w.parabot.itimplinghunter.utilities.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Skill;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


@ScriptManifest(author = "phl0w",
        category = Category.OTHER,
        description = "Hunts zombie/dragon/kingly implings",
        name = "iTImplingHunter",
        servers = {"PkHonor"},
        version = 1.0)

public class iTImplingHunter extends Script implements Paintable {

    /**
     * User variables
     */
    public static int startLevel = 0;
    public static int startMoney = 0;
    public static int prevInvCount = 0;
    public static int lootedItems = 0;
    public static int startXp = 0;
    public static long startTime;
    public static String selected = "zombie";
    public static Impling selectedImpling = Impling.KINGLY;

    private final ArrayList<Strategy> strategies = new ArrayList<>();

    @Override
    public boolean onExecute() {
        selected = JOptionPane.showInputDialog("Enter the name of the impling you want to hunt:");
        selectedImpling = getSelectedImpling();
        startLevel = Utilities.getLevel(Skill.CONSTRUCTION);
        prevInvCount = Inventory.getCount();
        startMoney = Utilities.getMoney();
        startTime = System.currentTimeMillis();
        startXp = Skill.CONSTRUCTION.getExperience();
        strategies.add(new CatchImpling());
        provide(strategies);
        return true;
    }

    private Impling getSelectedImpling() {
        for (Impling i : Impling.values()) {
            if (i.name().equalsIgnoreCase(selected)) {
                return i;
            }
        }
        return Impling.ZOMBIE;
    }


    @Override
    public void paint(Graphics g) {
        org.phl0w.parabot.itimplinghunter.utilities.Paint.onRepaint(g);
    }
}

package org.phl0w.parabot.itrunecrafting;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itrunecrafting.strategies.Antis;
import org.phl0w.parabot.itrunecrafting.strategies.Bank;
import org.phl0w.parabot.itrunecrafting.strategies.Craft;
import org.phl0w.parabot.itrunecrafting.strategies.Relog;
import org.phl0w.parabot.itrunecrafting.utilities.Altar;
import org.phl0w.parabot.itrunecrafting.utilities.Utilities;
import org.rev317.min.api.methods.Skill;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "phl0w", name = "iTRunecrafting", version = 1.0, category = Category.RUNECRAFTING, description = "Crafts runes dawg", servers = {"PKHonor"})
public class iTRunecrafting extends Script implements Paintable {

    private final ArrayList<Strategy> strategies = new ArrayList<>();
    public static int startXp, startLevel;
    public static long startTime;
    public static Altar selectedAltar = Altar.WATER;
    public static final int RUNE_ESSENCE = 1436;
    public static String password;
    public static String status = "Startup";
    public static int prevCount;
    public static int crafted = 0;

    @Override
    public boolean onExecute() {
        password = JOptionPane.showInputDialog("Please enter your password for relogging purposes:");
        final String selected = JOptionPane.showInputDialog("Enter the name of the rune you want to craft:");
        selectedAltar = Utilities.getAltar(selected);
        startLevel = Utilities.getLevel(Skill.RUNECRAFTING);
        startXp = Skill.RUNECRAFTING.getExperience();
        prevCount = Utilities.getStackSize();
        startTime = System.currentTimeMillis();
        strategies.add(new Relog());
        strategies.add(new Antis());
        strategies.add(new Bank());
        strategies.add(new Craft());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itrunecrafting.utilities.Paint.onRepaint(graphics);
    }

}

package org.phl0w.parabot.itherblore;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itherblore.strategies.Antis;
import org.phl0w.parabot.itherblore.strategies.Bank;
import org.phl0w.parabot.itherblore.strategies.Make;
import org.phl0w.parabot.itherblore.strategies.Relog;
import org.phl0w.parabot.itherblore.utilities.Utilities;
import org.rev317.min.api.methods.Skill;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "phl0w", category = Category.HERBLORE, description = "Makes unfinished and finished potions - have ingredients in slot 1 and 2 of your bank - preferably run in ::private", name = "iTHerblore", servers = {"PKHonor"}, version = 1.0D)
public class iTHerblore extends Script implements Paintable {

    private ArrayList<Strategy> strategies = new ArrayList<>();
    public static String password = "";
    public static int startLevel;
    public static int startXp;
    public static long startTime;
    public static int potionsMade = 0;
    public static String status = "Startup";
    public static boolean hasRelogged = true;
    public static boolean fetchedStats = false;
    public static int item1 = -1;
    public static int item2 = -1;
    public static int finishedPotionId = -1;

    @Override
    public boolean onExecute() {
        if (Utilities.isLoggedIn()) {
            startLevel = Utilities.getLevel(Skill.HERBLORE);
            startXp = Skill.HERBLORE.getExperience();
            fetchedStats = true;
        }
        startTime = System.currentTimeMillis();
        password = JOptionPane.showInputDialog("Please enter your password for relogging purposes:");
        item1 = Integer.parseInt(JOptionPane.showInputDialog("Please enter the id of item #1:"));
        item2 = Integer.parseInt(JOptionPane.showInputDialog("Please enter the id of item #2:"));
        finishedPotionId = Integer.parseInt(JOptionPane.showInputDialog("Please enter the id of finished potion:\n" +
                "This is only required if you want to keep track of potions made."));
        strategies.add(new Relog());
        strategies.add(new Antis());
        strategies.add(new Make());
        strategies.add(new Bank());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itherblore.utilities.Paint.onRepaint(graphics);
    }
}

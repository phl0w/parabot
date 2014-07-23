package org.phl0w.parabot.itruneminer;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itruneminer.strategies.Bank;
import org.phl0w.parabot.itruneminer.strategies.Mine;
import org.phl0w.parabot.itruneminer.utilities.Utilities;
import org.phl0w.parabot.util.Antis;
import org.phl0w.parabot.util.Relog;
import org.rev317.min.api.methods.Skill;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(name = "iTRuneMiner", version = 1.0, author = "phl0w", description = "Mines rune in the heroes guild basement", servers = "PKHonor", category = Category.MINING)
public class iTRuneMiner extends Script implements Paintable {

    private final ArrayList<Strategy> strategies = new ArrayList<>();
    public static int startXp, startLevel, mined;
    public static long startTime;
    public static final int RUNE_ORE_ID = 451;
    public static String status = "Starting up";

    @Override
    public boolean onExecute() {
        final String username = JOptionPane.showInputDialog("Please enter your username for relogging purposes:");
        final String password = JOptionPane.showInputDialog("Please enter your password for relogging purposes:");
        startXp = Skill.MINING.getExperience();
        startLevel = Utilities.getLevel(Skill.MINING);
        startTime = System.currentTimeMillis();
        strategies.add(new Relog(username, password));
        strategies.add(new Antis());
        strategies.add(new Bank());
        strategies.add(new Mine());
        provide(strategies);
        return true;
    }


    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itruneminer.utilities.Paint.onRepaint(graphics);
    }
}

package org.phl0w.parabot.itruneminer;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itruneminer.strategies.Antis;
import org.phl0w.parabot.itruneminer.strategies.Bank;
import org.phl0w.parabot.itruneminer.strategies.Mine;
import org.phl0w.parabot.itruneminer.strategies.Relog;
import org.phl0w.parabot.itruneminer.utilities.Utilities;
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
    public static String password;

    @Override
    public boolean onExecute() {
        password = JOptionPane.showInputDialog("Please enter your password for relogging purposes:");
        startXp = Skill.MINING.getExperience();
        startLevel = Utilities.getLevel(Skill.MINING);
        startTime = System.currentTimeMillis();
        strategies.add(new Relog());
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

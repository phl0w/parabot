package org.phl0w.parabot.itsuperprayers;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itsuperprayers.strategies.Antis;
import org.phl0w.parabot.itsuperprayers.strategies.Bank;
import org.phl0w.parabot.itsuperprayers.strategies.Make;
import org.phl0w.parabot.itsuperprayers.strategies.Relog;
import org.phl0w.parabot.itsuperprayers.utilities.Utilities;
import org.rev317.min.api.methods.Skill;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "phl0w", category = Category.HERBLORE, description = "Makes super prayers - have ingredients in slot 1 and 2 of your bank - preferably run in ::private", name = "iTSuperPrayers", servers = {"PKHonor"}, version = 1.0D)
public class iTSuperPrayers extends Script implements Paintable {

    private ArrayList<Strategy> strategies = new ArrayList<>();
    public static String password = "";
    public static int startLevel;
    public static int startXp;
    public static long startTime;
    public static int potionsMade = 0;
    public static String status = "Startup";

    @Override
    public boolean onExecute() {
        startLevel = Utilities.getLevel(Skill.HERBLORE);
        startXp = Skill.HERBLORE.getExperience();
        startTime = System.currentTimeMillis();
        password = JOptionPane.showInputDialog("Please enter your password for relogging purposes:");
        strategies.add(new Relog());
        strategies.add(new Antis());
        strategies.add(new Make());
        strategies.add(new Bank());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itsuperprayers.utilities.Paint.onRepaint(graphics);
    }
}

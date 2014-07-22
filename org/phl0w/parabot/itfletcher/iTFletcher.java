package org.phl0w.parabot.itfletcher;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itfletcher.strategies.Bank;
import org.phl0w.parabot.itfletcher.strategies.Fletch;
import org.phl0w.parabot.itfletcher.utilities.FletchData;
import org.phl0w.parabot.itfletcher.utilities.Utilities;
import org.phl0w.parabot.util.Antis;
import org.phl0w.parabot.util.Relog;
import org.rev317.min.api.methods.Skill;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(name = "iTFletcher", version = 1.0, description = "Does all the fletching work for you!", author = "Flow", servers = {"PkHonor"}, category = Category.FLETCHING)
public class iTFletcher extends Script implements Paintable {

    public static int startXp, startLevel, fletched;
    public static long startTime;
    public static String status = "Startup";
    public static FletchData item = FletchData.MAGIC_LONGBOW;
    private final ArrayList<Strategy> strategies = new ArrayList<>();

    public boolean onExecute() {
        final String password = JOptionPane.showInputDialog("Please enter your password for relogging purposes:");
        final String i = (String) JOptionPane.showInputDialog(null, "Select a item to carve", "Select item", JOptionPane.QUESTION_MESSAGE, null, Utilities.getList(), Utilities.getList()[0]);
        item = Utilities.getItem(i);
        startXp = Skill.FLETCHING.getExperience();
        startLevel = Utilities.getLevel(Skill.FLETCHING);
        startTime = System.currentTimeMillis();
        strategies.add(new Relog(password));
        strategies.add(new Antis());
        strategies.add(new Bank());
        strategies.add(new Fletch());
        provide(strategies);
        return true;
    }


    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itfletcher.utilities.Paint.onRepaint(graphics);
    }
}

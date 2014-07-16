package org.phl0w.parabot.itpohplanker;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itpohplanker.strategies.Antis;
import org.phl0w.parabot.itpohplanker.strategies.Banker;
import org.phl0w.parabot.itpohplanker.strategies.Plank;
import org.phl0w.parabot.itpohplanker.strategies.Relog;
import org.phl0w.parabot.itpohplanker.utilities.Log;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "phl0w", category = Category.OTHER, name = "iTPOHPlanker", version = 1.0, description = "Turns logs into planks in your POH!", servers = "PKHonor")
public class iTPOHPlanker extends Script implements Paintable {


    public static int logsPlanked;
    public static long startTime;
    public static String status = "Starting up";
    public static String password;
    public static Log selectedLog = Log.OAK;
    public static final int[] WORKBENCHES = {13706};

    private final ArrayList<Strategy> strategies = new ArrayList<>();

    @Override
    public boolean onExecute() {
        password = JOptionPane.showInputDialog("Please enter your password for relogging purposes:");
        startTime = System.currentTimeMillis();
        strategies.add(new Relog());
        strategies.add(new Antis());
        strategies.add(new Plank());
        strategies.add(new Banker());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itpohplanker.utilities.Paint.onRepaint(graphics);
    }
}

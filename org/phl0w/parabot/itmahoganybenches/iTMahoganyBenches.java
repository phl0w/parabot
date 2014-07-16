package org.phl0w.parabot.itmahoganybenches;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itmahoganybenches.strategies.Antis;
import org.phl0w.parabot.itmahoganybenches.strategies.Banker;
import org.phl0w.parabot.itmahoganybenches.strategies.HandleObject;
import org.phl0w.parabot.itmahoganybenches.strategies.Make;
import org.phl0w.parabot.itmahoganybenches.utilities.Utilities;
import org.rev317.min.api.methods.Skill;

import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "phl0w", version = 1.0, name = "iTMahoganyBenches", servers = {"PKHonor"}, description = "Makes mahogany benches in ur POH!", category = Category.OTHER)
public class iTMahoganyBenches extends Script implements Paintable {

    public static int startXp, startLevel, made;
    public static long startTime;
    public static String status = "Starting up";

    private ArrayList<Strategy> strategies = new ArrayList<>();

    @Override
    public boolean onExecute() {
        startXp = Skill.HUNTER.getExperience();
        startLevel = Utilities.getLevel(Skill.HUNTER);
        startTime = System.currentTimeMillis();
        strategies.add(new Antis());
        strategies.add(new Banker());
        strategies.add(new Make());
        strategies.add(new HandleObject());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itmahoganybenches.utilities.Paint.onRepaint(graphics);
    }
}

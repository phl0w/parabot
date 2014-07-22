package org.phl0w.parabot.itrunebarsmelter;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itrunebarsmelter.strategies.Antis;
import org.phl0w.parabot.itrunebarsmelter.strategies.Bank;
import org.phl0w.parabot.itrunebarsmelter.strategies.Smelt;
import org.phl0w.parabot.itrunebarsmelter.utilities.Utilities;
import org.rev317.min.api.methods.Skill;

import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(name = "iTRuneBarSmelter", version = 1.0, category = Category.SMITHING, description = "Smelts rune bars @ crafting guild", author = "phl0w", servers = {"PKHonor"})
public class iTRuneBarSmelter extends Script implements Paintable {

    public static int startXp, startLevel, smelted;
    public static long startTime;
    private final ArrayList<Strategy> strategies = new ArrayList<>();
    public static String status = "Starting up";
    public static int RUNE_ORE_ID = 451;
    public static int COAL_ORE_ID = 453;
    public static int RUNE_BAR_ID = 2363;

    @Override
    public boolean onExecute() {
        startTime = System.currentTimeMillis();
        startXp = Skill.SMITHING.getExperience();
        startLevel = Utilities.getLevel(Skill.SMITHING);
        strategies.add(new Antis());
        strategies.add(new Smelt());
        strategies.add(new Bank());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itrunebarsmelter.utilities.Paint.onRepaint(graphics);
    }
}

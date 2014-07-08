package org.phl0w.parabot.italtar;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.italtar.strategies.Bank;
import org.phl0w.parabot.italtar.strategies.UseOnAltar;
import org.phl0w.parabot.italtar.utilities.Bone;
import org.phl0w.parabot.italtar.utilities.Utilities;
import org.rev317.min.api.methods.Skill;

import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "phl0w", name = "iTAltar", category = Category.PRAYER, version = 1.0, description = "Uses bones on altar in Edgeville.", servers = {"PKHonor"})
public class iTAltar extends Script implements Paintable {

    public static int startLevel = 0;
    public static int startXp = 0;
    public static long startTime = 0;

    public static Bone selectedBone = Bone.DRAGON_BONE;
    private ArrayList<Strategy> strategies = new ArrayList<>();

    @Override
    public boolean onExecute() {
        startLevel = Utilities.getLevel(Skill.PRAYER);
        startXp = Skill.PRAYER.getExperience();
        startTime = System.currentTimeMillis();
        strategies.add(new Bank());
        strategies.add(new UseOnAltar());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.italtar.utilities.Paint.onRepaint(graphics);
    }
}

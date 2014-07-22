package org.phl0w.parabot.itpohchopper;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itpohchopper.strategies.Antis;
import org.phl0w.parabot.itpohchopper.strategies.Banker;
import org.phl0w.parabot.itpohchopper.strategies.Chopper;
import org.phl0w.parabot.itpohchopper.utilities.Tree;
import org.phl0w.parabot.itpohchopper.utilities.Utilities;
import org.rev317.min.api.methods.Skill;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "phl0w", category = Category.WOODCUTTING, name = "iTPOHChopper", version = 1.0, description = "Chops any tree in your POH! Have a servant and trees planted.", servers = "PKHonor")
public class iTPOHChopper extends Script implements Paintable {


    public static int startXp;
    public static int startLevel;
    public static int logsChopped;
    public static long startTime;
    public static String status = "Starting up";
    public static Tree selectedTree = Tree.OAK;
    public static String[] options = {"Normal", "Oak", "Willow", "Maple", "Yew", "Magic"};

    private final ArrayList<Strategy> strategies = new ArrayList<>();

    @Override
    public boolean onExecute() {
        String tree = (String) JOptionPane.showInputDialog(null, "Select a tree to chop", "Select tree", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        selectedTree = Utilities.getTree(tree);
        startTime = System.currentTimeMillis();
        startXp = Skill.WOODCUTTING.getExperience();
        startLevel = Utilities.getLevel(Skill.WOODCUTTING);
        strategies.add(new Antis());
        strategies.add(new Chopper());
        strategies.add(new Banker());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itpohchopper.utilities.Paint.onRepaint(graphics);
    }
}

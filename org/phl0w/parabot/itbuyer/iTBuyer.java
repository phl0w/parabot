package org.phl0w.parabot.itbuyer;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itbuyer.strategies.Antis;
import org.phl0w.parabot.itbuyer.strategies.Buy;
import org.phl0w.parabot.itbuyer.strategies.Open;
import org.phl0w.parabot.itbuyer.strategies.Relog;
import org.phl0w.parabot.itbuyer.utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "phl0w", name = "iTBuyer", category = Category.OTHER, version = 1.0, description = "Buys stuff", servers = {"PKHonor"})
public class iTBuyer extends Script implements Paintable {

    public static String password;
    public static long sleepTimer = 0;
    public static String status = "Startup";
    public static int startItemCount = 0;
    public static long startTime = 0;
    public static int itemId = 2435;  //16081   = bonemeal                        || 2435 = prayer
    public static int itemSlot = 2;         //16          = bonemeal || 2 = prayer
    public static int sleepDelay = 5000; //5 seconds per 10 items
    public static int npcId = 581; //supplies store (prayer) = 581 || herblore store (bonemeal) = 561
    public static int rCount = 0;
    private ArrayList<Strategy> strategies = new ArrayList<>();

    @Override
    public boolean onExecute() {
        password = JOptionPane.showInputDialog("Please enter your password for relogging purposes:");
        itemId = Integer.parseInt(JOptionPane.showInputDialog("Please enter the item ID:\nBonemeal:16081, Prayer pot(4):2435"));
        itemSlot = Integer.parseInt(JOptionPane.showInputDialog("Please enter the item slot in shop:\nBonemeal: 16, Prayer pot(4):2"));
        sleepDelay = Integer.parseInt(JOptionPane.showInputDialog("Please enter the sleep delay in milliseconds:"));
        npcId = Integer.parseInt(JOptionPane.showInputDialog("Please enter the npc ID:\nSupplies store: 581, Herlore store: 561"));
        startItemCount = Utilities.getStackSize();
        startTime = System.currentTimeMillis();
        strategies.add(new Antis());
        strategies.add(new Relog());
        strategies.add(new Open());
        strategies.add(new Buy());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itbuyer.utilities.Paint.onRepaint(graphics);
    }
}

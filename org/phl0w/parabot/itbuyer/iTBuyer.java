package org.phl0w.parabot.itbuyer;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itbuyer.strategies.Antis;
import org.phl0w.parabot.itbuyer.strategies.Buy;
import org.rev317.min.api.methods.Inventory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "phl0w", name = "iTBuyer", category = Category.OTHER, version = 1.0, description = "Buys stuff", servers = {"PKHonor"})
public class iTBuyer extends Script implements Paintable {

    public static long sleepTimer = 0;
    public static String status = "Startup";
    public static int startItemCount = 0;
    public static long startTime = 0;
    public static int itemId = 16081;  //16081   = bonemeal                        || 2435 = prayer
    public static int itemSlot = 16;         //16          = bonemeal || 2 = prayer
    public static int sleepDelay = 5000; // 5000 = bonemeal, 50000 = prayer pot
    private ArrayList<Strategy> strategies = new ArrayList<>();

    @Override
    public boolean onExecute() {
        itemId = Integer.parseInt(JOptionPane.showInputDialog("Please enter the item ID:\nBonemeal:16081, Prayer pot(4):2435"));
        itemSlot = Integer.parseInt(JOptionPane.showInputDialog("Please enter the item slot in shop:\nBonemeal: 16, Prayer pot(4):2"));
        sleepDelay = Integer.parseInt(JOptionPane.showInputDialog("Please enter the sleep delay in milliseconds:"));
        startItemCount = Inventory.getCount(itemId + 1);
        startTime = System.currentTimeMillis();
        strategies.add(new Buy());
        strategies.add(new Antis());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itbuyer.utilities.Paint.onRepaint(graphics);
    }
}

package org.phl0w.parabot.itdiamondtips;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itdiamondtips.strategies.Buy;
import org.phl0w.parabot.itdiamondtips.strategies.ChiselGems;
import org.phl0w.parabot.itdiamondtips.strategies.CutBoltTips;
import org.phl0w.parabot.itdiamondtips.strategies.Open;
import org.phl0w.parabot.util.Antis;
import org.phl0w.parabot.util.Relog;
import org.phl0w.parabot.util.Utilities;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(name = "iTDiamondTips", version = 1.0, description = "Chisels uncut diamonds, then cuts them into bolt tops. Start with chisel in slot 1 of your inventory, and money preferably in last. Make sure to be standing next to the crafting shop.", category = Category.CRAFTING, author = "phl0w", servers = {"PkHonor"})
public class iTDiamondTips extends Script implements Paintable {

    public static int startXp, startLevel;
    public static long startTime;
    public static int chiseled;
    private final ArrayList<Strategy> strategies = new ArrayList<>();

    public static final int CUT_DIAMOND = 1601;
    public static final int UNCUT_DIAMOND = 1617;
    public static final int CHISEL = 1755;
    public static final int DIAMOND_BOLT_TIPS = 9192;
    public static int startBoltTips = 0;

    @Override
    public boolean onExecute() {
        startBoltTips = getAmount(DIAMOND_BOLT_TIPS);
        final String username = JOptionPane.showInputDialog("Please enter your username for relogging purposes:");
        final String password = JOptionPane.showInputDialog("Please enter your password for relogging purposes:");
        startXp = Skill.CRAFTING.getExperience();
        startLevel = Utilities.getLevel(Skill.CRAFTING);
        startTime = System.currentTimeMillis();
        strategies.add(new Relog(username, password));
        strategies.add(new Antis());
        strategies.add(new Buy());
        strategies.add(new ChiselGems());
        strategies.add(new CutBoltTips());
        strategies.add(new Open());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        org.phl0w.parabot.itdiamondtips.utilities.Paint.onRepaint(graphics);
    }

    public static int getAmount(final int id) {
        final Item[] items = Inventory.getItems(id + 1);
        if (items.length > 0) {
            if (items[0] != null) {
                return items[0].getStackSize();
            }
        }
        return 0;
    }
}

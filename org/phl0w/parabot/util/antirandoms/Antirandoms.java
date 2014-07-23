package org.phl0w.parabot.util.antirandoms;

import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.util.antirandoms.impl.BobsIsland;
import org.phl0w.parabot.util.antirandoms.impl.Genie;
import org.phl0w.parabot.util.antirandoms.impl.MysteriousOldMan;
import org.phl0w.parabot.util.antirandoms.impl.SandwichLady;

import java.awt.*;

public class Antirandoms implements Strategy {
    private final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
    private final IAntirandom[] antis = {new SandwichLady(), new MysteriousOldMan(), new Genie(), new BobsIsland()};

    @Override
    public boolean activate() {
        return true;
    }

    @Override
    public void execute() {
        for (final IAntirandom anti : antis) {
            if (anti.activate()) {
                if (runnable != null)
                    runnable.run();
                System.out.println("Performing " + anti.getClass().getSimpleName());
                anti.execute();
            }
        }
    }
}

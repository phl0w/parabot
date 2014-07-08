package org.phl0w.parabot.itimplinghunter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itimplinghunter.iTImplingHunter;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;

public class CatchImpling implements Strategy {
    @Override
    public boolean activate() {
        return !Inventory.isFull() && Players.getMyPlayer().getAnimation() == -1;
    }

    @Override
    public void execute() {
        final Npc[] nearestImplings = Npcs.getNearest(iTImplingHunter.selectedImpling.getNpcId());
        if (nearestImplings.length > 0) {
            final Npc nearestImpling = nearestImplings[0];
            if (nearestImpling != null) {
                nearestImpling.interact(0);
                Time.sleep(1000, 2000);
            }
        }
    }
}

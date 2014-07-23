package org.phl0w.parabot.util.antirandoms.impl;

import org.parabot.environment.api.utils.Filter;
import org.phl0w.parabot.util.antirandoms.IAntirandom;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;

public class Genie implements IAntirandom {

    private static final int[] GENIE_NPC = {3022, 3351, 409};
    private static final Filter<Npc> FILTER = new Filter<Npc>() {
        @Override
        public boolean accept(final Npc n) {
            return inArray(n.getDef().getId()) && n.getInteractingCharacter().equals(Players.getMyPlayer());
        }
    };

    @Override
    public boolean activate() {
        final Npc[] genies = Npcs.getNearest(FILTER);
        return genies.length > 0 && genies[0] != null;
    }

    @Override
    public void execute() {
        final Npc genie = Npcs.getNearest(FILTER)[0];
        if (genie != null) {
            System.exit(0);
        }
    }

    private static boolean inArray(final int i) {
        for (int t : GENIE_NPC) {
            if (i == t) {
                return true;
            }
        }
        return false;
    }
}

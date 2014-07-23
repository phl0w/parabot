package org.phl0w.parabot.util.antirandoms.impl;

import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.phl0w.parabot.util.antirandoms.IAntirandom;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;

public class SandwichLady implements IAntirandom {

    private static final int SANDWICH_LADY_NPC = 3117;
    private static final Filter<Npc> FILTER = new Filter<Npc>() {
        @Override
        public boolean accept(Npc npc) {
            return npc.getDef().getId() == SANDWICH_LADY_NPC && npc.getInteractingCharacter().equals(Players.getMyPlayer());
        }
    };

    @Override
    public boolean activate() {
        final Npc[] sandwichLadies = Npcs.getNearest(FILTER);
        return sandwichLadies.length > 0 && sandwichLadies[0] != null;
    }

    @Override
    public void execute() {
        final Npc sandwichLady = Npcs.getNearest(FILTER)[0];
        if (sandwichLady != null) {
            sandwichLady.interact(0);
            Time.sleep(300, 500);
        }
    }
}

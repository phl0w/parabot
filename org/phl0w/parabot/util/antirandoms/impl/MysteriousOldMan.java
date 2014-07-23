package org.phl0w.parabot.util.antirandoms.impl;

import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.phl0w.parabot.util.antirandoms.IAntirandom;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;

public class MysteriousOldMan implements IAntirandom {

    private static final int MYSTERIOUS_MAN_NPC = 410;
    private static final Filter<Npc> FILTER = new Filter<Npc>() {
        @Override
        public boolean accept(Npc n) {
            return n.getDef().getId() == MYSTERIOUS_MAN_NPC && n.getInteractingCharacter().equals(Players.getMyPlayer());
        }
    };

    @Override
    public boolean activate() {
        final Npc[] mysteriousMen = Npcs.getNearest(FILTER);
        return mysteriousMen.length > 0 && mysteriousMen[0] != null;
    }

    @Override
    public void execute() {
        final Npc mysteriousMan = Npcs.getNearest(FILTER)[0];
        if (mysteriousMan != null) {
            mysteriousMan.interact(0);
            Time.sleep(300, 500);
        }
    }
}

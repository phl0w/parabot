package org.phl0w.parabot.util.antirandoms.impl;

import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.phl0w.parabot.util.Area;
import org.phl0w.parabot.util.antirandoms.IAntirandom;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

public class BobsIsland implements IAntirandom {

    private static final Area BOBS_ISLAND = new Area(new Tile(2511, 4765),
            new Tile(2511, 4790),
            new Tile(2542, 4790),
            new Tile(2542, 4765));
    private static final int BOB_NPC = 1091;
    private static final int PORTAL_OBJ = 8987;
    private static final Filter<Npc> FILTER = new Filter<Npc>() {
        @Override
        public boolean accept(final Npc n) {
            return n.getDef().getId() == BOB_NPC && n.getInteractingCharacter().equals(Players.getMyPlayer());
        }
    };

    @Override
    public boolean activate() {
        final Npc[] bobs = Npcs.getNearest(FILTER);
        return BOBS_ISLAND.contains(Players.getMyPlayer().getLocation()) && bobs.length > 0 && bobs[0] != null;
    }

    @Override
    public void execute() {
        final Npc bob = Npcs.getNearest(FILTER)[0];
        if (bob != null) {
            final SceneObject[] possiblePortals = SceneObjects.getNearest(PORTAL_OBJ);
            if (possiblePortals.length > 0) {
                for (final SceneObject portal : possiblePortals) {
                    if (portal != null) {
                        portal.interact(0);
                        Time.sleep(new SleepCondition() {
                            @Override
                            public boolean isValid() {
                                return portal.distanceTo() < 2;
                            }
                        }, 7500);
                    }
                }
            }
        }
    }
}

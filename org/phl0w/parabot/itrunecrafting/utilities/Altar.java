package org.phl0w.parabot.itrunecrafting.utilities;

public enum Altar {

    AIR(2478, 556),
    MIND(2479, 558),
    BODY(2483, 559),
    EARTH(2481, 557),
    FIRE(2491, 554),
    COSMIC(2484, 565),
    NATURE(2486, 561),
    CHAOS(2487, 562),
    LAW(2485, 563),
    DEATH(2488, 560),
    WATER(2480, 555);

    private int objectId, runeId;

    Altar(final int objectId, final int runeId) {
        this.objectId = objectId;
        this.runeId = runeId;
    }

    public int getObjectId() {
        return objectId;
    }

    public int getRuneId() {
        return runeId;
    }


    //BLOOD(7172, 7175, 7155)
    //soul(7172, 7155)
}

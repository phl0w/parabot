package org.phl0w.parabot.itimplinghunter.utilities;

public enum Impling {

    ZOMBIE(6342, 3200),
    DRAGON(6054, 5000), //xp per needs to be verified
    KINGLY(6343, 8000);

    private int npcId;
    private int xpPer;

    Impling(final int npcId, final int xpPer) {
        this.npcId = npcId;
        this.xpPer = xpPer;
    }

    public int getNpcId() {
        return npcId;
    }

    public int getXpPer() {
        return xpPer;
    }
}

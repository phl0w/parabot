package org.phl0w.parabot.italtar.utilities;

public enum Bone {

    BONE(0, 0),
    BIG_BONE(0, 0),
    DRAGON_BONE(536, 4000),
    DAGANNOTH_BONE(0, 0),
    FROST_DRAGON_BONE(0, 0);

    private int itemId;
    private int xpPer;

    Bone(final int itemId, final int xpPer) {
        this.itemId = itemId;
        this.xpPer = xpPer;
    }

    public int getItemId() {
        return itemId;
    }

    public int getXpPer() {
        return xpPer;
    }
}

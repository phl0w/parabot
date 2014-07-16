package org.phl0w.parabot.itpohplanker.utilities;

public enum Log {

    NORMAL(0, 1511),
    OAK(1521, 8778),
    WILLOW(0, 1519),
    MAPLE(0, 1517),
    YEW(0, 1515),
    MAGIC(0, 1513);

    private int treeId, itemId;

    Log(final int logItemId, final int plankItemId) {
        this.treeId = logItemId;
        this.itemId = plankItemId;
    }

    public int getLogId() {
        return treeId;
    }

    public int getPlankId() {
        return itemId;
    }
}

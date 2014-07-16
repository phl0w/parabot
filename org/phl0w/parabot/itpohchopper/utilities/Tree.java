package org.phl0w.parabot.itpohchopper.utilities;

public enum Tree {

    NORMAL(0, 1511),
    OAK(1281, 1521),
    WILLOW(0, 1519),
    MAPLE(0, 1517),
    YEW(0, 1515),
    MAGIC(0, 1513);

    private int treeId, itemId;

    Tree(final int treeId, final int itemId) {
        this.treeId = treeId;
        this.itemId = itemId;
    }

    public int getId() {
        return treeId;
    }

    public int getItemId() {
        return itemId;
    }
}

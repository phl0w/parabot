package org.phl0w.parabot.itpohchopper.utilities;

public enum Tree {

    NORMAL(1276, 1511),
    OAK(1281, 1521),
    WILLOW(5551, 1519),
    MAPLE(1307, 1517),
    YEW(1309, 1515),
    MAGIC(1306, 1513);

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

    @Override
    public String toString() {
        return name();
    }
}

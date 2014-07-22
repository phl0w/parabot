package org.phl0w.parabot.itfletcher.utilities;

public enum FletchData {

    //what do we need?
    //log id
    //product id
    //action id
    //action1
    //action2
    //action3

    //log ids:
    //normal: 1511
    //oak: 1521
    //willow: 1519
    //maple : 1517
    //yew: 1515
    //magic: 1513

    NORMAL_ARROW_SHAFT(1511, 52, 315, 1517, 450, 8906),
    NORMAL_SHORTBOW(1511, 50, 315, 946, 404, 8910),
    NORMAL_LONGBOW(1511, 48, 315, 946, 425, 8914),
    NORMAL_CROSSBOW_STOCK(1511, 9440, 315, 946, 389, 8918),

    OAK_SHORTBOW(1521, 54, 315, 1521, 392, 8886),
    OAK_LONGBOW(1521, 56, 315, 1521, 4, 8890),
    OAK_CROSSBOW_STOCK(1521, 9442, 315, 1521, 4, 8894),

    WILLOW_SHORTBOW(1519, 60, 315, 1519, 390, 8886),
    WILLOW_LONGBOW(1519, 58, 315, 1519, 444, 8890),
    WILLOW_CROSSBOW_STOCK(1519, 9444, 315, 1519, 188, 8894),

    MAPLE_SHORTBOW(1517, 64, 315, 1517, 117, 8886),
    MAPLE_LONGBOW(1517, 62, 315, 1517, 2, 8890),
    MAPLE_CROSSBOW_STOCK(1517, 9448, 315, 1517, 2, 8894),

    YEW_SHORTBOW(1515, 68, 315, 1515, 1, 8886),
    YEW_LONGBOW(1515, 66, 315, 1515, 2, 8890),
    YEW_CROSSBOW_STOCK(1515, 9453, 315, 1515, 3, 8894),

    MAGIC_SHORTBOW(1513, 72, 315, 946, 423, 8871),
    MAGIC_LONGBOW(1513, 70, 315, 1513, 1, 8875);

    private int logId, productId, actionId, action1, action2, action3;

    FletchData(final int logId, final int productId, final int actionId, final int action1, final int action2, final int action3) {
        this.logId = logId;
        this.productId = productId;
        this.actionId = actionId;
        this.action1 = action1;
        this.action2 = action2;
        this.action3 = action3;
    }

    public int getLogId() {
        return logId;
    }

    public int getProductId() {
        return productId;
    }

    public int getActionId() {
        return actionId;
    }

    public int getAction1() {
        return action1;
    }

    public int getAction2() {
        return action2;
    }

    public int getAction3() {
        return action3;
    }
}

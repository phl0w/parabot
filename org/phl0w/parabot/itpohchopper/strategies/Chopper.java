package org.phl0w.parabot.itpohchopper.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itpohchopper.iTPOHChopper;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Chopper implements Strategy {

    public boolean activate() {
        return !Inventory.isFull() && Players.getMyPlayer().getAnimation() == -1;
    }

    @Override
    public void execute() {
        iTPOHChopper.status = "Chopping";
        SceneObject tree = SceneObjects.getClosest(iTPOHChopper.selectedTree.getId());
        if (tree != null) {
            Menu.sendAction(502, tree.getHash(), tree.getLocalRegionX(), tree.getLocalRegionY());
            Time.sleep(1500, 2500);
        }
    }
}

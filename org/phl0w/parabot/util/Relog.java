package org.phl0w.parabot.util;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itruneminer.utilities.Utilities;
import org.rev317.min.api.methods.SceneObjects;

public class Relog implements Strategy {

    private String password;

    public Relog(String password) {
        this.password = password;
    }

    @Override
    public boolean activate() {
        return SceneObjects.getNearest().length == 0;
    }

    @Override
    public void execute() {
        Keyboard.getInstance().sendKeys(password);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Utilities.isLoggedIn();
            }
        }, 6000);
    }
}

package org.phl0w.parabot.util;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Relog implements Strategy {

    private String username, password;

    public Relog(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean activate() {
        return !isLoggedIn();
    }

    @Override
    public void execute() {
        login(username, password);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return isLoggedIn();
            }
        }, 6000);
    }

    private boolean isLoggedIn() {
        try {
            final Class<?> c = Loader.getClient().getClass();
            final Field f = c.getDeclaredField("aj");
            return f.getBoolean(Loader.getClient());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void login(String username, String password) {
        try {
            Class<?> c = Loader.getClient().getClass();
            Method m = c.getDeclaredMethod("a", String.class, String.class, boolean.class);
            m.setAccessible(true);
            m.invoke(Loader.getClient(), username, password, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

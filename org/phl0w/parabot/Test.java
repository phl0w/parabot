package org.phl0w.parabot;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

@ScriptManifest(author = "phl0w", category = Category.OTHER, description = "Test", name = "Test", servers = {"PKHonor"}, version = 1.0D)
public class Test extends Script implements Paintable {

    private final ArrayList<Strategy> strategies = new ArrayList<>();
    public String currentVar;
    public int oldvalue, newValue = 0;

    @Override
    public boolean onExecute() {
        strategies.add(new Testing());
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawString("Changing var: " + currentVar, 12, 100);
        graphics.drawString("Old value: " + oldvalue, 12, 150);
        graphics.drawString("New value: " + newValue, 12, 150);
    }

    public class Testing implements Strategy {

        @Override
        public boolean activate() {
            return true;
        }

        @Override
        public void execute() {
            setInterface(3824);
            Time.sleep(5000);
        }
    }

    public int getInputState() {
        try {
            Class<?> c = Loader.getClient().getClass();
            Field f = c.getDeclaredField("aq");
            f.setAccessible(true); // it's a private field
            return f.getInt(Loader.getClient());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setInterface(int interfaceId) {
        try {
            Class<?> c = Loader.getClient().getClass();
            Field f = c.getDeclaredField("cJ"); //openInterfaceID field
            f.setAccessible(true);
            f.setInt(Loader.getClient(), interfaceId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBackDialog(int backDialogId){
        try{
            Class<?>c = Loader.getClient().getClass();
            Field f = c.getDeclaredField("jm"); //backDialogID field
            f.setAccessible(true);
            f.setInt(Loader.getClient(), backDialogId);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

package org.phl0w.parabot.api;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * Banking.java
 * <p/>
 * Provides utilities for handling banking on PkHonor.
 *
 * @author phl0w
 * @version 1.1
 * @since 21-7-2014
 */
public class Banking {

    /**
     * Stores the bank interface ID.
     */
    public static final int INTERFACE_BANK_OPEN = 23350;

    /**
     * Stores all known bank booth object IDs.
     */
    public static final int[] BANK_BOOTHS = {2213};

    /**
     * Stores the action3 for depositing items
     */
    public static int ACTION_DEPOSIT = 5064;

    /**
     * Stores the action3 for withdrawing items
     */
    public static int ACTION_WITHDRAW = 5382;

    /**
     * A function that allows the developer to open the bank.
     *
     * @return <tt>true</tt> if the bank was open or successfully opened, <tt>false</tt> if the bank did not open.
     */
    public static boolean open() {
        if (isOpen()) return true;
        final SceneObject[] banks = SceneObjects.getNearest(BANK_BOOTHS);
        if (banks.length > 0) {
            final SceneObject bank = banks[0];
            if (bank != null) {
                bank.interact(0);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return isOpen();
                    }
                }, 2000);
            }

        }
        return false;
    }

    /**
     * A function to check is the bank is open.
     *
     * @return <tt>true</tt> if the bank interface is visible, <tt>false</tt> if the bank interface is not visible.
     */
    public static boolean isOpen() {
        return Loader.getClient().getOpenInterfaceId() == INTERFACE_BANK_OPEN;
    }

    /**
     * A function to check if the withdraw amount screen open.
     *
     * @return <tt>true</tt>  if the withdraw amount back dialog is visible, <tt>false</tt> if the withdraw amount back dialog is not.
     */
    public static boolean isAmountScreenOpen() {
        return Loader.getClient().getOpenInterfaceId() == 23350;
    }

    /**
     * Tries to withdraw an item from the bank.
     * Do not use this to withdraw using withdraw-x.
     *
     * @param itemId     The ID of the item
     * @param slotInBank The slot that the item is in
     * @param option     <tt>optional</tt>, method will withdraw all by default but you can specify the option using the Amount class
     * @see org.phl0w.parabot.api.Banking.Option
     * @see #withdrawAmount
     */
    public static void withdrawItem(final int itemId, final int slotInBank, final Option... option) {
        if (!isOpen()) return;
        int actionId = Option.ALL.getActionId();
        if (option.length > 0) {
            if (option.length > 1) return;
            if (option[0].equals(Option.X)) return;
            actionId = option[0].getActionId();
        }
        Menu.sendAction(actionId, itemId, slotInBank, ACTION_WITHDRAW);
    }

    /**
     * Tries to withdraw a certain amount of an item from the bank.
     * Do not use this for anything other than withdrawing using withdraw-x.
     *
     * @param itemId     The ID of the item
     * @param slotInBank The slot that the item is in
     * @param amount     The amount of the item to withdraw
     */
    public static void withdrawAmount(final int itemId, final int slotInBank, final int amount) {
        if (!isOpen()) return;
        if (isAmountScreenOpen()) {
            Keyboard.getInstance().sendKeys(Integer.toString(amount));
        } else {
            Menu.sendAction(Option.X.getActionId(), itemId, slotInBank, ACTION_WITHDRAW);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return isAmountScreenOpen();
                }
            }, 2000);
            Keyboard.getInstance().sendKeys(Integer.toString(amount));
        }
        Time.sleep(400, 750);
    }

    /**
     * Tries to deposit an item to the bank.
     * Do not use this to deposit using deposit-x.
     *
     * @param itemId          The ID of the item
     * @param slotInInventory The slot that the item is in
     * @param option          <tt>optional</tt>, method will deposit all by default but you can specify the option using the Amount class
     * @see org.phl0w.parabot.api.Banking.Option
     * @see #depositAmount
     */
    public static void depositItem(final int itemId, final int slotInInventory, final Option... option) {
        if (!isOpen()) return;
        int actionId = Option.ALL.getActionId();
        if (option.length > 0) {
            if (option.length > 1) return;
            if (option[0].equals(Option.X)) return;
            actionId = option[0].getActionId();
        }
        Menu.sendAction(actionId, itemId, slotInInventory, ACTION_DEPOSIT);
    }

    /**
     * Tries to deposit a certain amount of an item to the bank.
     * Do not use this for anything other than depositing using deposit-x.
     *
     * @param itemId          The ID of the item
     * @param slotInInventory The slot that the item is in
     * @param amount          The amount of the item to withdraw
     */
    public static void depositAmount(final int itemId, final int slotInInventory, final int amount) {
        if (!isOpen()) return;
        if (isAmountScreenOpen()) {
            Keyboard.getInstance().sendKeys(Integer.toString(amount));
        } else {
            Menu.sendAction(Option.X.getActionId(), itemId, slotInInventory, ACTION_DEPOSIT);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return isAmountScreenOpen();
                }
            }, 2000);
            Keyboard.getInstance().sendKeys(Integer.toString(amount));
        }
        Time.sleep(400, 750);
    }

    /**
     * Provides the user with an option to select what option he would like to use to withdraw items.
     */
    public enum Option {

        ONE(632),
        FIVE(78),
        TEN(867),
        X(431),
        ALL(53),
        ALL_BUT_ONE(432);

        /**
         * Stores the action id for the specified action.
         */
        private int actionId;

        /**
         * Creates a new Option
         *
         * @param actionId The action ID associated with the option.
         */
        Option(final int actionId) {
            this.actionId = actionId;
        }

        /**
         * Returns the action id of the option.
         *
         * @return the action id of the option.
         */
        public int getActionId() {
            return actionId;
        }
    }

    /**
     * Provides a function for the user to deposit the player's inventroy into their bank.
     *
     * @return <tt>true</tt> if the inventory was deposited into the bank, <tt>false</tt> if the inventory was not
     * empty after 2 seconds of executing this method.
     */
    public static boolean depositInventory() {
        if (!isOpen()) return false;
        Menu.sendAction(646, 2481, 0, 23412);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.isEmpty();
            }
        }, 2000);
        return Inventory.isEmpty();
    }

    /**
     * Provides a function for the user to deposit the player's equipment into their bank.
     *
     * @return <tt>true</tt> if the equipment was deposited into the bank, <tt>false</tt> if the equipment was not
     * deposited after 2 seconds of executing this method.
     * TODO: Check if the equipment was deposited, waiting for Equipment hook.
     */
    public static boolean depositEquipment() {
        if (!isOpen()) return false;
        Menu.sendAction(646, 233, 153, 23416);
        Time.sleep(500, 800);
        return true;
    }

    /**
     * Provides a function for the user to deposit their beast of burden's inventory into their bank.
     *
     * @return <tt>true</tt> if the BoB's inventory was deposited into the bank, <tt>false</tt> if the BoB's inventory
     * was not deposited after 2 seconds of executing this method.
     * TODO: Wait for summoning to be released.
     * TODO: Wait for BoB hook.
     */

    public static boolean depositFamiliarInventory() {
        if (!isOpen()) return false;
        Menu.sendAction(646, 8782, 11, 23420);
        Time.sleep(500, 800);
        return true;
    }

    /**
     * A function that allows the developer to close the bank.
     *
     * @return <tt>true</tt> if the bank was closed, <tt>false</tt> if the bank was not closed.
     */
    public static boolean close() {
        if (!isOpen()) return true;
        Menu.sendAction(200, 5343, 29, 5384);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return !isOpen();
            }
        }, 2000);
        return !isOpen();
    }
}
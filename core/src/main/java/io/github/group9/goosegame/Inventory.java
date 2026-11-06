package io.github.group9.goosegame;

import io.github.group9.goosegame.Entities.Item;

public class Inventory {
    public static final int INV_SIZE = 5;

    private final Item[] items = new Item[INV_SIZE];
    private final int[] counts = new int[INV_SIZE];
    private int selected = 0;
   
    /* Adds item to stack if same item is already in inventory, 
        or adds to first available free space*/
    public boolean add(Item item, int amount) {
        if (amount <= 0) return true;
        
        for (int i = 0; i < INV_SIZE; i++) {
            if (items[i] != null && items[i].getName().equals(item.getName())) {
                counts[i] += amount;
                return true;
            }
        }
        for (int i = 0; i < INV_SIZE; i++) {
            if (items[i] == null) {
                items[i] = item;
                counts[i] = amount;
                return true;
            }
        }
        return false;
    }

    public Item getItem(int slot) {
        if (slot < 0 || slot >= INV_SIZE) return null;
        return items[slot];
    }

    public int getCount(int slot) {
        if (slot < 0 || slot >= INV_SIZE) return 0;
        return counts[slot];
    }

    // Returns item in selected slot
    public Item getSelected() {
        return items[selected];
    }

    // Returns number of items in selected slot
    public int getSelectedCount() {
        return counts[selected];
    }

    // Removes one item from the selected slot, empties slot when it reaches 0
    public void removeSelected() {
        if (items[selected] == null) return;
        counts[selected]--;
        if (counts[selected] <= 0) {
            items[selected] = null;
            counts[selected] = 0;
        }
    }

    public int getSelectedSlot() {
        return selected;
    }

    public void select(int slot) {
        if (slot >= 0 && slot < INV_SIZE) selected = slot;
    }
}

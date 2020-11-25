package Game;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;

    Inventory()
    {
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public Inventory removeItem(Item item)
    {
        items.remove(item);
        return this;
    }
}

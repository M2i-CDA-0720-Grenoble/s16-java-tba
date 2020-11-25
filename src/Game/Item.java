package Game;

import java.util.HashMap;
import java.util.function.Function;

public class Item
{
    private String name;
    private String description;

    private HashMap<Action, Function<ActionParameters, String> > actions;
    private HashMap<String, Boolean> switches;
    

    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
        actions = new HashMap<>();
        switches = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<Action, Function<ActionParameters, String> > getActions() {
        return actions;
    }

    public Item addAction(Action action, Function<ActionParameters, String> function) {
        actions.put(action, function);
        return this;
    }

    public Boolean getSwitch(String key)
    {
        return switches.get(key);
    }

    public Item setSwitch(String key, Boolean value) {
        switches.put(key, value);
        return this;
    }


    /**
     * Output a description of the item
     */
    public void describe() {
        System.out.println(ConsoleColor.CYAN + description + ConsoleColor.RESET);
    }
}

import java.util.HashMap;

public class Item
{
    private String name;
    private String description;

    private HashMap<Action, String> actions;


    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
        actions = new HashMap<Action, String>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<Action, String> getActions() {
        return actions;
    }

    public Item addAction(Action action, String text) {
        actions.put(action, text);
        return this;
    }


    /**
     * Output a description of the item
     */
    public void describe() {
        System.out.println(ConsoleColor.CYAN + description + ConsoleColor.RESET);
    }
}

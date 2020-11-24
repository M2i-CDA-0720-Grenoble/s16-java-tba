import java.util.ArrayList;

public class Room
{
    private String name;
    private String description;

    private Room[] directions;
    private ArrayList<Item> items;


    /**
     * Default constructor
     */
    public Room()
    {
        name = "";
        description = "";
        directions = new Room[Direction.values().length];
        items = new ArrayList<Item>();
    }


    /**
     * Constructor including name and description initializers
     * @param name Room name
     * @param description Room description
     */
    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
        directions = new Room[Direction.values().length];
        items = new ArrayList<Item>();
    }


    /**
     * Output a description of the room, including possible directions
     */
    public void describe()
    {
        // Affiche le nom de la pièce
        System.out.println(ConsoleColor.CYAN + "You are in the " + name + "." + ConsoleColor.RESET);
        // Affiche la description de la pièce
        System.out.println(ConsoleColor.CYAN + description + ConsoleColor.RESET);
        // Affiche la liste des objets disponibles dans la pièce
        if (items.size() > 0) {
            String[] itemNames = new String[items.size()];
            for (int i = 0; i < items.size(); i += 1) {
                itemNames[i] = items.get(i).getName();
            }
            String itemListString = String.join(", ", itemNames);
            System.out.println(ConsoleColor.GREEN + "Available items: " + itemListString + "." + ConsoleColor.RESET);
        }
        // Affiche la liste des directions disponibles dans la pièce
        for (Direction direction : Direction.values()) {
            Room otherRoom = directions[direction.ordinal()];
            if (otherRoom != null) {
                System.out.println(ConsoleColor.GREEN + direction.name() + " is the " + otherRoom.getName() + "." + ConsoleColor.RESET);
            }
        }
    }


    /**
     * {@link Room#name}
     * @return Room name
     */
    public String getName()
    {
        return name;
    }


    /**
     * {@link Room#description}
     * @return Room description
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * {@link Room#directions}
     * @return Array of rooms that can be accessed from this room
     */
    public Room[] getDirections()
    {
        return directions;
    }


    /**
     * Scan given direction for a room
     * @param direction Direction to scan
     * @see Direction
     * @return Room that can be accessed from this room in given direction
     */
    public Room getDirection(Direction direction)
    {
        return directions[direction.ordinal()];
    }


    /**
     * Make a room accessible from this room in given location
     * @param direction Direction in which the room to link is located
     * @param room Room to link
     * @return This room
     */
    public Room setDirection(Direction direction, Room room)
    {
        directions[direction.ordinal()] = room;

        return this;
    }


    public ArrayList<Item> getItems() {
        return items;
    }


    public Room addItem(Item item) {
        items.add(item);
        return this;
    }


    public Room removeItem(Item item) {
        items.remove(item);
        return this;
    }
}

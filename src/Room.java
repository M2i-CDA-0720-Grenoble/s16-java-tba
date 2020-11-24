public class Room
{
    private String name;
    private String description;

    private Room[] directions;


    /**
     * Default constructor
     */
    public Room()
    {
        name = "";
        description = "";
        directions = new Room[Direction.values().length];
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
    }


    /**
     * Output a description of the room, including possible directions
     */
    public void describe()
    {
        System.out.println(ConsoleColor.CYAN + "You are in the " + name + "." + ConsoleColor.RESET);
        System.out.println(ConsoleColor.CYAN + description + ConsoleColor.RESET);
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
}

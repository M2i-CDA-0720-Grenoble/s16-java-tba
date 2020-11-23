public class Room
{
    private String name;
    private String description;

    private Room[] directions;

    public Room()
    {
        name = "";
        description = "";
        directions = new Room[Direction.values().length];
    }

    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
        directions = new Room[Direction.values().length];
    }

    public void describe()
    {
        System.out.println(ConsoleColor.CYAN + "You are in the " + name + "." + ConsoleColor.RESET);
        System.out.println(ConsoleColor.CYAN + description + ConsoleColor.RESET);
        for (Direction direction : Direction.values()) {
            Room otherRoom = directions[direction.getIndex()];
            if (otherRoom != null) {
                System.out.println(ConsoleColor.GREEN + direction.name() + " is the " + otherRoom.getName() + "." + ConsoleColor.RESET);
            }
        }
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Room[] getDirections()
    {
        return directions;
    }

    public Room getDirection(Direction direction)
    {
        return directions[direction.getIndex()];
    }

    public Room setDirection(Direction direction, Room room)
    {
        directions[direction.getIndex()] = room;

        return this;
    }
}

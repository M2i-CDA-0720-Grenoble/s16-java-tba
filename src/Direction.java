enum Direction {
    East(0, "east"),
    South(1, "south"),
    West(2, "west"),
    North(3, "north");

    private int index;
    private String command;

    private Direction(int index, String command)
    {
        this.index = index;
        this.command = command;
    }

    public int getIndex()
    {
        return index;
    }

    public String getCommand()
    {
        return command;
    }
}

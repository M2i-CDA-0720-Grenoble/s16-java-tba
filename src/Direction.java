enum Direction {
    East("east"),
    South("south"),
    West("west"),
    North("north");

    private String command;


    /**
     * Enum constructor
     * @param command User input matching this direction
     */
    private Direction(String command)
    {
        this.command = command;
    }

    
    /**
     * {@link Direction#command}
     * @return User input matching this direction
     */
    public String getCommand()
    {
        return command;
    }
}

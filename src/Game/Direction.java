package Game;

public enum Direction {
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


    public static Direction match(String command)
    {
        // Cherche parmi toutes les directions, celle qui correspond à la commande demandée
        for (Direction direction : Direction.values()) {
            if (direction.getCommand().equals(command)) {
                return direction;
            }
        }
        // Si la boucle s'est terminée, c'est donc que la commande ne correspond à aucune direction
        return null;
    }
}

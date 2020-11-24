package Game;

public enum Action {
    Examine("examine", "There's nothing special about it."),
    Open("open", "This does not seem to open."),
    Close("close", "This does not seem to close."),
    Use("use", "You have no idea how to use that."),
    Break("break", "You cannot break this with your bare hands.");

    private String command;
    private String defaultText;

    private Action(String command, String defaultText)
    {
        this.command = command;
        this.defaultText = defaultText;
    }

    public String getCommand()
    {
        return command;
    }

    public String getDefaultText()
    {
        return defaultText;
    }

    public static Action match(String command)
    {
        // Cherche parmi toutes les actions disponibles, celle qui correspond à la commande demandée
        for (Action action : Action.values()) {
            if (action.command.equals(command)) {
                return action;
            }
        }
        // Si la boucle s'est terminée, c'est donc qu'aucune action ne correspond à la commande
        return null;
    }
}

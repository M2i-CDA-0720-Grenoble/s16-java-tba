package Game;

public enum Action {
    Examine("examine"),
    Open("open"),
    Use("use"),
    Break("break");

    private String command;

    private Action(String command)
    {
        this.command = command;
    }

    public String getCommand()
    {
        return command;
    }
}

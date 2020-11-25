package Game;

public enum BaseCommand
{
    Exit("exit"),
    Help("help"),
    Navigation("room"),
    Inventory("inventory");

    private String command;

    private BaseCommand(String command)
    {
        this.command = command;
    }

    public String getCommand()
    {
        return command;
    }

    public static BaseCommand match(String input)
    {
        for (BaseCommand command : BaseCommand.values()) {
            if (command.getCommand().equals(input)) {
                return command;
            }
        }
        return null;
    }
}

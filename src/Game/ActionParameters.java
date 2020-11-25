package Game;

public class ActionParameters {
    private Game game;
    private Item item;

    public ActionParameters(Game game, Item item)
    {
        this.game = game;
        this.item = item;
    }

    public Game getGame() {
        return game;
    }

    public Item getItem() {
        return item;
    }
}

package Game.GameMode;

import Game.Game;

abstract public class GameMode
{
    final static public int NAVIGATION_MODE_TYPE = 0;
    final static public int INVENTORY_MODE_TYPE = 1;

    protected Game game;

    GameMode(Game game)
    {
        this.game = game;
    }

    abstract public void describe();

    abstract public void interpret(String userInput);
}

package Game.GameMode;

import Game.Game;

abstract public class GameMode
{
    protected Game game;

    GameMode(Game game)
    {
        this.game = game;
    }

    abstract public void describe();

    abstract public void interpret(String userInput);
}

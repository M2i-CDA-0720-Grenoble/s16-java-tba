package Game.GameMode;

import Game.ConsoleColor;
import Game.Direction;
import Game.Game;
import Game.Item;
import Game.Room;

public class NavigationMode extends GameMode
{
    public NavigationMode(Game game)
    {
        super(game);
    }

    public void describe()
    {
        System.out.println(ConsoleColor.MAGENTA + "Navigating " + game.getCurrentRoom().getName() + ConsoleColor.RESET + "\n");
        // Affiche la description de la pièce actuelle
        game.getCurrentRoom().describe();
    }

    public void interpret(String userInput)
    {
        // Cherche si la saisie de l'utilisateur correspond à une direction,
        // et se déplace dans la pièce correspondante le cas échéant
        Direction direction = Direction.match(userInput);

        if (direction != null) {
            Room newRoom = game.getCurrentRoom().getDirection(direction);

            if (newRoom == null) {
                System.out.println(ConsoleColor.YELLOW + "You cannot go into that direction." + ConsoleColor.RESET);
            } else {
                game.setCurrentRoom(newRoom);
                game.setMode(new NavigationMode(game));
            }
            
            return;
        }

        // Cherche si la saisie de l'utilisateur correspond à un objet présent dans la pièce,
        // et passe en mode "interaction" avec cet objet le cas échéant
        for (Item item : game.getCurrentRoom().getVisibleItems()) {
            if (item.getName().equals(userInput)) {
                game.setMode(new InteractionMode(game, item, GameMode.NAVIGATION_MODE_TYPE));
                return;
            }
        }

        System.out.println(ConsoleColor.YELLOW + "Unknown command." + ConsoleColor.RESET);
    }
}

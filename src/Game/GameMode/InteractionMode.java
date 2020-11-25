package Game.GameMode;

import java.util.function.Function;

import Game.Action;
import Game.ConsoleColor;
import Game.Game;
import Game.Item;

public class InteractionMode extends GameMode
{
    private Item currentItem;

    public InteractionMode(Game game, Item currentItem)
    {
        super(game);

        this.currentItem = currentItem;
    }

    public void describe()
    {
        System.out.println(ConsoleColor.MAGENTA + "Interactng with " + currentItem.getName() + ConsoleColor.RESET + "\n");
        // Affiche la description de l'objet concerné
        currentItem.describe();
    }

    public void interpret(String userInput)
    {
        // Si la saisie de l'utilisateur est vide, retourne en mode "navigation"
        if ("".equals(userInput)) {
            game.setMode(new NavigationMode(game));
            return;
        }
        // Cherche si la saisie de l'utilisateur correspond à une action définie pour l'objet,
        // et affiche son résultat le cas échéant
        Action userAction = Action.match(userInput);

        if (userAction == null) {
            System.out.println(ConsoleColor.YELLOW + "This action does not exist." + ConsoleColor.RESET);
        } else {
            Function<Item, String> function = currentItem.getActions().get(userAction);

            String text;
            if (function == null) {
                text = userAction.getDefaultText();
            } else {
                text = function.apply(currentItem);
            }

            System.out.println(text);
        }
    }
}

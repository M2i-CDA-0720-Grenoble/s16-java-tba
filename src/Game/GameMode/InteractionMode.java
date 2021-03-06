package Game.GameMode;

import java.util.function.Function;

import Game.Action;
import Game.ActionParameters;
import Game.ConsoleColor;
import Game.Game;
import Game.Item;

public class InteractionMode extends GameMode
{
    private Item currentItem;
    private int previousModeType;

    public InteractionMode(Game game, Item currentItem, int previousModeType)
    {
        super(game);
        
        this.currentItem = currentItem;
        this.previousModeType = previousModeType;
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
            GameMode newMode;
            switch (previousModeType) {
                case GameMode.NAVIGATION_MODE_TYPE:
                    newMode = new NavigationMode(game);
                    break;
                case GameMode.INVENTORY_MODE_TYPE:
                    newMode = new InventoryMode(game);
                    break;
                default:
                    newMode = this;
            }
            game.setMode(newMode);
            return;
        }
        // Cherche si la saisie de l'utilisateur correspond à une action définie pour l'objet,
        // et affiche son résultat le cas échéant
        Action userAction = Action.match(userInput);

        if (userAction == null) {
            System.out.println(ConsoleColor.YELLOW + "This action does not exist." + ConsoleColor.RESET);
        } else {
            Function<ActionParameters, String> function = currentItem.getActions().get(userAction);

            String text;
            if (function == null) {
                text = userAction.getDefaultText();
            } else {
                ActionParameters parameters = new ActionParameters(game, currentItem);

                text = function.apply(parameters);
            }

            System.out.println(text);
        }
    }
}

package Game.GameMode;

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
            String text = currentItem.getActions().get(userAction);

            if (text == null) {
                text = userAction.getDefaultText();
            }

            System.out.println(text);
        }
    }
}

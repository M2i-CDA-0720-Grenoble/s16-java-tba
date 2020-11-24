package Game.GameMode;

import Game.Action;
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
        }
        // Cherche si la saisie de l'utilisateur correspond à une action définie pour l'objet,
        // et affiche son résultat le cas échéant
        for (Action action : Action.values()) {
            if (action.getCommand().equals(userInput)) {
                String text = currentItem.getActions().get(action);
                System.out.println(text);
                return;
            }
        }
    }
}

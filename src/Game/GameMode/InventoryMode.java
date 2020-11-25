package Game.GameMode;

import java.util.List;

import Game.ConsoleColor;
import Game.Game;
import Game.Item;

public class InventoryMode extends GameMode
{
    public InventoryMode(Game game)
    {
        super(game);
    }

    public void describe()
    {
        System.out.println(ConsoleColor.MAGENTA + "Inventory" + ConsoleColor.RESET + "\n");
        // Affiche la liste des objets présents dans l'inventaire,
        // ou un message précisant que celui-ci est vide la cas échéant
        List<Item> items = game.getInventory().getItems();
        if (items.size() == 0) {
            System.out.println(ConsoleColor.YELLOW + "Your inventory is empty." + ConsoleColor.RESET);
        } else {
            String[] itemNames = new String[items.size()];
            for (int i = 0; i < items.size(); i += 1) {
                itemNames[i] = items.get(i).getName();
            }
            System.out.println(ConsoleColor.GREEN + "Your inventory contains: " + String.join(", ", itemNames) + ConsoleColor.RESET);
        }
    }

    public void interpret(String userInput)
    {
        // Si la saisie de l'utilisateur est vide, retourne en mode navigation
        if ("".equals(userInput)) {
            game.setMode(new NavigationMode(game));
            return;
        }

        // Cherche si la saisie de l'utilisateur correspond à un objet présent dans l'inventaire,
        // et passe en mode "interaction" le cas échéant
        List<Item> items = game.getInventory().getItems();
        for (Item item : items) {
            if (item.getName().equals(userInput)) {
                game.setMode(new InteractionMode(game, item));
                return;
            }
        }

        System.out.println(ConsoleColor.YELLOW + "You don't have such an item." + ConsoleColor.RESET);
    }
}

package Game;

import java.util.Scanner;

import Game.GameMode.*;

public class Game
{
    private Scanner scanner;
    private boolean isRunning;
    private GameMode mode;
    private Inventory inventory;

    private Room[] rooms;
    private Room currentRoom;


    /**
     * Class constructor
     */
    public Game() {
        // Crée un objet Scanner qui va permettre de traiter les saisies de
        // l'utilisateur
        scanner = new Scanner(System.in);

        // Crée les pièces du jeu
        rooms = DataProvider.createRooms();

        // Définit la pièce de départ
        currentRoom = rooms[0];

        // Initialise le marqueur permettant de savoir que la partie est en cours
        isRunning = true;

        // Initialise le jeu en mode "navigation"
        setMode(new NavigationMode(this));

        // Initialise l'inventaire
        inventory = new Inventory();
    }


    /**
     * Update game state
     */
    public void update() {
        // Invite l'utilisateur à rentrer une ligne de texte
        System.out.println("");
        System.out.print("> ");
        String userInput = scanner.nextLine().trim();

        // Si la saisie de l'utilisateur correspond à une commande de base (accessible de n'importe quel endroit du jeu),
        // exécute l'action correspondante, sinon passe la main au mode pour l'interpréter
        BaseCommand command = BaseCommand.match(userInput);
        if (command == null) {
            // Interprète la saisie utilisateur en fonction du mode
            mode.interpret(userInput);
        } else {
            switch (command) {
                // Arrête le jeu
                case Exit:
                    terminate();
                    break;
                // Affiche une aide pour l'utilisateur
                case Help:
                    displayHelp();
                    break;
                // Passe en mode "navigation"
                case Navigation:
                    setMode(new NavigationMode(this));
                    break;
                // Passe en mode "inventaire"
                case Inventory:
                    setMode(new InventoryMode(this));
                    break;
            }
        }

        System.out.println("");
    }


    /**
     * Flag game as terminated and clean up before exit
     */
    private void terminate() {
        isRunning = false;
        scanner.close();
    }

    
    /** 
     * Find a room by name
     * 
     * @param name The name of the room to be looked up
     * @return The room matching the given name, null if none found
     */
    private Room findRoomByName(String name) {
        // Cherche parmi les pièces disponibles, celle correspondant au nom demandé
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }

        // Si aucune pièce n'a été trouvée, renvoie null
        return null;
    }

    
    /**
     * {@link Game#isRunning}
     * @return Whether the game is flagged as running
     */
    public boolean getIsRunning() {
        return isRunning;
    }


    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public Game setCurrentRoom(Room room)
    {
        this.currentRoom = room;
        return this;
    }

    public GameMode getMode() {
        return mode;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
        
        System.out.println("");

        // Décrit la situation acutelle en fonction du mode
        mode.describe();
    }


    public Inventory getInventory()
    {
		return inventory;
    }
    

    private void displayHelp()
    {
        System.out.println(ConsoleColor.MAGENTA + "\nReference" + ConsoleColor.RESET + "\n");
        System.out.println(ConsoleColor.CYAN + "Base commands" + ConsoleColor.RESET + "\n");

        for (BaseCommand command : BaseCommand.values()) {
            System.out.println(ConsoleColor.GREEN + command.getCommand() + ConsoleColor.RESET);
        }

        System.out.println(ConsoleColor.CYAN + "\nDirections (in navigation mode)" + ConsoleColor.RESET + "\n");
        for (Direction direction : Direction.values()) {
            System.out.println(ConsoleColor.GREEN + direction.getCommand() + ConsoleColor.RESET);
        }
        System.out.println(ConsoleColor.GREEN + "<item name> - enter interaction mode with specified item" + ConsoleColor.RESET);

        System.out.println(ConsoleColor.CYAN + "\nActions (in interaction mode)" + ConsoleColor.RESET + "\n");
        for (Action action : Action.values()) {
            System.out.println(ConsoleColor.GREEN + action.getCommand() + ConsoleColor.RESET);
        }
    }
}

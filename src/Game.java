import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private boolean isRunning;
    private int mode;

    private Room[] rooms;
    private Room currentRoom;
    private Item currentItem;


    /**
     * Class constructor
     */
    public Game() {
        // Crée un objet Scanner qui va permettre de traiter les saisies de
        // l'utilisateur
        scanner = new Scanner(System.in);

        // Crée les pièces du jeu
        Room bedroom = new Room("bedroom", "There is a bed and a desk.");
        Room bathroom = new Room("bathroom", "There is a toothbrush and a towel.");
        Room kitchen = new Room("kitchen", "There are leftovers from last night's dinner.");
        Room corridor = new Room("corridor", "It's empty.");

        bedroom.setDirection(Direction.East, bathroom);
        bathroom.setDirection(Direction.West, bedroom);
        bedroom.setDirection(Direction.North, corridor);
        corridor.setDirection(Direction.South, bedroom);
        corridor.setDirection(Direction.West, kitchen);
        kitchen.setDirection(Direction.East, corridor);

        rooms = new Room[] {
            bedroom,
            bathroom,
            kitchen,
            corridor,
        };

        // Crée les objets dans les pièces
        Item bedroomDesk = new Item("desk", "Beautiful description of the desk");
        bedroomDesk.addAction(Action.Examine, "It's very old.");
        bedroomDesk.addAction(Action.Open, "You opened a desk drawer.");
        bedroomDesk.addAction(Action.Use, "You don't need to work right now.");

        bedroom.addItem( bedroomDesk );
        bedroom.addItem( new Item("bed", "Beautiful description of the bed") );
        kitchen.addItem( new Item("food", "Beautiful description of the food") );
        bathroom.addItem( new Item("toothbrush", "Beautiful description of the toothbrush") );
        bathroom.addItem( new Item("towel", "Beautiful description of the towel") );

        // Définit la pièce de départ
        currentRoom = rooms[0];

        // Initialise le marqueur permettant de savoir que la partie est en cours
        isRunning = true;

        // Initialise le jeu en mode "navigation"
        mode = 0;
    }


    /**
     * Update game state
     */
    public void update() {
        // Décrit la situation actuelle
        switch (mode) {
            // Mode navigation
            case 0:
                currentRoom.describe();
                break;
            // Mode interaction
            case 1:
                currentItem.describe();
                break;
        }

        // Invite l'utilisateur à rentrer une ligne de texte
        System.out.println("");
        System.out.print("> ");
        String userInput = scanner.nextLine().trim();

        // Si l'utilisateur a rentré "exit", termine le jeu
        if ("exit".equals(userInput)) {
            terminate();
            return;
        }

        switch (mode) {
            // Mode navigation
            case 0:
                // Cherche si la saisie de l'utilisateur correspond à une direction,
                // et se déplace dans la pièce correspondante le cas échéant
                for (Direction direction : Direction.values()) {
                    if (direction.getCommand().equals(userInput)) {
                        Room newRoom = currentRoom.getDirection(direction);
        
                        if (newRoom == null) {
                            System.out.println(ConsoleColor.YELLOW + "You cannot go into that direction." + ConsoleColor.RESET);
                        } else {
                            currentRoom = newRoom;
                        }
        
                        break;
                    }
                }
                // Cherche si la saisie de l'utilisateur correspond à un objet présent dans la pièce,
                // et passe en mode "interaction" avec cet objet le cas échéant
                for (Item item : currentRoom.getItems()) {
                    if (item.getName().equals(userInput)) {
                        currentItem = item;
                        mode = 1;
                        break;
                    }
                }
                break;
            // Mode interaction
            case 1:
                // Si la saisie de l'utilisateur est vide, retourne en mode "navigation"
                if ("".equals(userInput)) {
                    mode = 0;
                }
                // Cherche si la saisie de l'utilisateur correspond à une action définie pour l'objet,
                // et affiche son résultat le cas échéant
                for (Action action : Action.values()) {
                    if (action.getCommand().equals(userInput)) {
                        String text = currentItem.getActions().get(action);
                        System.out.println(text);
                        break;
                    }
                }
                break;
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
}

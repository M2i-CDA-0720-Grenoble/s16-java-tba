package Game;

import java.util.Scanner;

import Game.GameMode.*;

public class Game
{
    private Scanner scanner;
    private boolean isRunning;
    private GameMode mode;

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
        bedroomDesk.setSwitch("open", false);
        bedroomDesk.addAction(Action.Examine,
            (item) -> {
                String text;
                if (item.getSwitch("open")) {
                    text = "The drawer is open.";
                } else {
                    text = "The drawer is closed.";
                }
                return text;
            }
        );
        bedroomDesk.addAction(Action.Open,
            (item) -> {
                if (item.getSwitch("open")) {
                    return "The drawer is already open.";
                }
                item.setSwitch("open", true);
                return "You open the drawer.";
            }
        );
        bedroomDesk.addAction(Action.Close,
            (item) -> {
                if (!item.getSwitch("open")) {
                    return "The drawer is already closed.";
                }
                item.setSwitch("open", false);
                return "You close the drawer.";
            }
        );
        bedroomDesk.addAction(Action.Use,
            (item) -> {
                return "You don't need to work right now.";
            }
        );

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
        mode = new NavigationMode(this);
    }


    /**
     * Update game state
     */
    public void update() {
        // Décrit la situation acutelle en fonction du mode
        mode.describe();
        
        // Invite l'utilisateur à rentrer une ligne de texte
        System.out.println("");
        System.out.print("> ");
        String userInput = scanner.nextLine().trim();

        // Si l'utilisateur a rentré "exit", termine le jeu
        if ("exit".equals(userInput)) {
            terminate();
            return;
        }

        // Interprète la saisie utilisateur en fonction du mode
        mode.interpret(userInput);

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
    }
}

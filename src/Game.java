import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private boolean isRunning;

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

        // Définit la pièce de départ
        currentRoom = rooms[0];

        // Initialise le marqueur permettant de savoir que la partie est en cours
        isRunning = true;
    }


    /**
     * Update game state
     */
    public void update() {
        // Décrit la pièce actuelle
        currentRoom.describe();

        // Invite l'utilisateur à rentrer une ligne de texte
        System.out.println("");
        System.out.print("> ");
        String userInput = scanner.nextLine().trim();

        // Si l'utilisateur a rentré "exit", termine le jeu
        if ("exit".equals(userInput)) {
            terminate();
            return;
        }

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

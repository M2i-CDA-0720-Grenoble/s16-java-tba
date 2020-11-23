import java.util.Scanner;

public class Game
{
    private Scanner scanner;
    private boolean isRunning;

    private Room[] rooms;
    private Room currentRoom;

    public Game()
    {
        // Crée un objet Scanner qui va permettre de traiter les saisies de l'utilisateur
        scanner = new Scanner(System.in);

        // Crée les pièces du jeu
        rooms = new Room[] {
            new Room("bedroom", "You are in the bedroom"),
            new Room("bathroom", "You are in the bathroom."),
            new Room("kitchen", "You are in the kitchen.")
        };

        // Définit la pièce de départ
        currentRoom = rooms[0];

        // Initialise le marqueur permettant de savoir que la partie est en cours
        isRunning = true;
    }

    private Room findRoomByName(String name)
    {
        // Cherche parmi les pièces disponibles, celle correspondant au nom demandé
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }

        // Si aucune pièce n'a été trouvée, renvoie null
        return null;
    }

    public void update()
    {
        // Décrit la pièce actuelle
        System.out.println(currentRoom.getDescription());

        // Invite l'utilisateur à rentrer une ligne de texte
        System.out.println("");
        System.out.print("> ");
        String userInput = scanner.nextLine().trim();

        // Si l'utilisateur a rentré "exit", termine le jeu
        if ("exit".equals(userInput)) {
            terminate();
            return;
        }

        // Cherche si la saisie de l'utilisateur correspond à une pièce, et change de pièce le cas échéant
        Room newRoom = findRoomByName(userInput);
        if (newRoom == null) {
            System.out.println("This room does not exist!");
        } else {
            currentRoom = newRoom;
        }

        System.out.println("");
    }

    private void terminate()
    {
        isRunning = false;
        scanner.close();
    }

    public boolean getIsRunning()
    {
        return isRunning;
    }
}

import java.util.Scanner;

public class Game
{
    public Scanner scanner;
    public boolean isRunning;

    public Room[] rooms;
    public Room currentRoom;

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

        currentRoom = rooms[0];

        isRunning = true;
    }

    public Room findRoomByName(String name)
    {
        for (Room room : rooms) {
            if (room.name.equals(name)) {
                return room;
            }
        }

        return null;
    }

    public void update()
    {
        // Décrit la situation actuelle
        System.out.println(currentRoom.description);

        // Invite l'utilisateur à rentrer une ligne de texte
        System.out.println("");
        System.out.print("> ");
        String userInput = scanner.nextLine().trim();

        if ("exit".equals(userInput)) {
            terminate();
            return;
        }

        // Change la situation actuelle en fonction de la saisie de l'utilisateur
        Room newRoom = findRoomByName(userInput);
        if (newRoom == null) {
            System.out.println("This room does not exist!");
        } else {
            currentRoom = newRoom;
        }

        System.out.println("");
    }

    public void terminate()
    {
        isRunning = false;
        scanner.close();
    }
}

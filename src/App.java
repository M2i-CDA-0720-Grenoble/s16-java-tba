import java.util.Scanner;

public class App {
    // Cette méthode est appelée automatiquement au lancement de l'application
    public static void main(String[] args) throws Exception {
        // Crée un objet Scanner qui va permettre de traiter les saisies de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        String userInput = null;

        // Crée les pièces du jeu
        Room[] rooms = {
            new Room("bedroom", "You are in the bedroom"),
            new Room("bathroom", "You are in the bathroom."),
            new Room("kitchen", "You are in the kitchen.")
        };

        // Définit la pièce de départ
        Room currentRoom = rooms[0];

        // Boucle principale
        // Tant que l'application n'a pas reçu le signal de s'arrêter, elle continue son cycle d'exécution
        while (!"exit".equals(userInput)) {

            // Décrit la situation actuelle
            System.out.println(currentRoom.description);

            // Invite l'utilisateur à rentrer une ligne de texte
            System.out.println("");
            System.out.print("> ");
            userInput = scanner.nextLine().trim();

            // Change la situation actuelle en fonction de la saisie de l'utilisateur
            for (Room room : rooms) {
                if (room.name.equals(userInput)) {
                    currentRoom = room;
                    break;
                }
            }

            System.out.println("");
        }

        System.out.println("Goodbye!");

        scanner.close();
    }
}

import java.util.Scanner;

public class App {
    // Cette méthode est appelée automatiquement au lancement de l'application
    public static void main(String[] args) throws Exception {
        // Crée un objet Scanner qui va permettre de traiter les saisies de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        String userInput = null;

        int currentRoom = 0;

        // Boucle principale
        // Tant que l'application n'a pas reçu le signal de s'arrêter, elle continue son cycle d'exécution
        while (!"exit".equals(userInput)) {

            // Décrit la situation actuelle
            switch (currentRoom) {
                // Chambre
                case 0:
                    System.out.println("You are in the bedroom.");
                    break;
                // Cuisine
                case 1:
                    System.out.println("You are in the kitchen.");
                    break;
                // Salle de bain
                case 2:
                    System.out.println("You are in the bathroom.");
                    break;
            }

            // Invite l'utilisateur à rentrer une ligne de texte
            System.out.println("");
            System.out.print("> ");
            userInput = scanner.nextLine().trim();

            // Change la situation actuelle en fonction de la saisie de l'utilisateur
            if ("bedroom".equals(userInput)) {
                currentRoom = 0;
            }
            if ("kitchen".equals(userInput)) {
                currentRoom = 1;
            }
            if ("bathroom".equals(userInput)) {
                currentRoom = 2;
            }

            System.out.println("");
        }

        System.out.println("Goodbye!");

        scanner.close();
    }
}

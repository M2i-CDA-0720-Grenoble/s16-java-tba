import java.util.Scanner;

public class App
{
    // Cette méthode est appelée automatiquement au lancement de l'application
    public static void main(String[] args) throws Exception {
        Game currentGame = new Game();

        // Boucle principale
        // Tant que l'application n'a pas reçu le signal de s'arrêter, elle continue son cycle d'exécution
        while (currentGame.isRunning) {
            currentGame.update();
        }

        System.out.println("Goodbye!");
    }
}

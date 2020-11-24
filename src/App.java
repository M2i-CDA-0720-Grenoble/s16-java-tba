import Game.Game;

public class App
{
    // Cette méthode est appelée automatiquement au lancement de l'application
    public static void main(String[] args) throws Exception {
        // Efface la console
        System.out.print("\033[H\033[2J");   
        System.out.flush();
        
        // Créer une nouvelle partie
        Game currentGame = new Game();

        // Boucle principale
        // Tant que l'application n'a pas reçu le signal de s'arrêter, elle continue son cycle d'exécution
        while (currentGame.getIsRunning()) {
            // Demande à la partie de se mettre à jour
            currentGame.update();
        }

        System.out.println("Goodbye!");
    }
}

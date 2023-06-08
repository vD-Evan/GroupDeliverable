/* Class: SYST 17796 - Fundamentals of Software Design and Development
 * Assignment: Group Deliverable
 * Authors: Juliana Sebastian, May Myat Noe Swe, Evan VanDuzer
 * Last Revision: 8 June 2023 
 */

package ca.sheridan.syst17796.groupdeliverable;

import ca.sheridan.syst17796.groupdeliverable.misc.Player;
import ca.sheridan.syst17796.groupdeliverable.ui.UserInput;
import ca.sheridan.syst17796.groupdeliverable.game.BlackJack;
import java.util.ArrayList;

public class MainMenu {
    public static void main(String[] args) {
        UserInput get = new UserInput();

        // Variable Declaration
        int numberOfGames = 1; // Update this number when adding more games
        int selection = 0;

        String[] gameIndex = new String[numberOfGames + 1];
        gameIndex[0] = "(-1 to Exit)";
        gameIndex[1] = "Black Jack";

        // Display Menu and offer choice
        while (selection != -1) {
            get.aCleanSlate();
            System.out.println("Please select your game from the following list (only integers will be accepted):");
            for (int i = 1; i <= numberOfGames; ++i) { // Starts at 1 and cycles through list; array index 0 is reserved
                                                       // for exit label
                System.out.printf("(%d) %s%n", i, gameIndex[i]);
            }
            System.out.printf("%s%n", gameIndex[0]);

            // Get user choice
            selection = get.anInt();
            if (selection > 0 && selection <= numberOfGames)
                gameSelection(gameIndex[selection]); // Jump to gameSelection with choice
            else if (selection != -1)
                System.out.println("Invalid Entry");

            // Pauses either after each game (to display winning message) or after error
            // message
            if (selection != -1)
                get.aWait();

        }
    }

    private static void gameSelection(String gameChoice) {
        UserInput get = new UserInput();
        get.aCleanSlate();
        System.out.println("You have chosen " + gameChoice);

        ArrayList<Player> players = getPlayers(gameChoice); // Builds a list of players;

        if (players != null) { // Allows for users to select a different game.
            switch (gameChoice) { // Builds a new instance of the game, launches it
                case "Black Jack":
                    BlackJack playGame = new BlackJack(players);
                    playGame.runGame();
                    break;
                default:
                    System.out.println("Invalid game choice.");
                    break;
            }
        } else {
            System.out.println("Game selection canceled.");
        }
    }

    private static ArrayList<Player> getPlayers(String gameChoice) {
        UserInput get = new UserInput();
        ArrayList<Player> players = new ArrayList<>(); // ArrayList used for creating a variable amount of players

        // ENTER HARDCODE AMOUNT OF PLAYERS HERE IF CHOSEN GAME REQUIRES
        // EXAMPLE:
        // switch(gameChoice) {
        // case "Euchre":
        // numberOfPlayers = 4;
        // break;
        // default:
        int minPlayerCount = 2; // Hard cap of 10 is arbitrary; be careful when increasing it as running out of
                                // cards may have unintended effects.
        int maxPlayerCount = 10; // This has been accounted for but can mess with the game if game is not set up
                                 // for it.

        System.out.println("Enter the number of players (2-10):");
        int numberOfPlayers = get.anInt();

        if (numberOfPlayers == -1) {
            return null; // Return null to indicate new game selection
        }

        // Skips while loop if a valid number of players is entered
        while (numberOfPlayers < minPlayerCount || numberOfPlayers > maxPlayerCount) { // Counts in variables for ease
                                                                                       // of changing in future
            System.out.println("Invalid number of players. Please enter a number between 2 and 10:");
            numberOfPlayers = get.anInt();

            if (numberOfPlayers == -1) {
                return null; // Return null to indicate new game selection
            }
        }
        // END SWITCH FOR HARDCODE OPTION

        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.println("Enter name for Player " + i + ": ");
            String playerName = get.aString();
            players.add(new Player(playerName)); // Adds new Player Object to ArrayList
        }

        return players;
    }
}

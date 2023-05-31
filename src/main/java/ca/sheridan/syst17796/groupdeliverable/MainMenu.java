package ca.sheridan.syst17796.groupdeliverable;

import ca.sheridan.syst17796.groupdeliverable.misc.Player;
import ca.sheridan.syst17796.groupdeliverable.ui.UserInput;
import ca.sheridan.syst17796.groupdeliverable.game.BlackJack;
import java.util.ArrayList;

public class MainMenu {
    public static void main(String[] args) {
        UserInput get = new UserInput();
        int iGameCount = 1;
        int iChoose = 0;

        String[] sIndex = new String[iGameCount + 1];
        sIndex[0] = "(-1 to Exit)";
        sIndex[1] = "Black Jack";

        while (iChoose != -1) {
            get.aCleanSlate();
            System.out.println("Please select your game from the following list (only integers will be accepted):");
            for (int i = 1; i <= iGameCount; ++i) {
                System.out.printf("(%d) %s%n", i, sIndex[i]);
            }
            System.out.printf("%s%n", sIndex[0]);

            iChoose = get.anInt();
            if (iChoose > 0 && iChoose <= iGameCount) {
                GameSelection(sIndex[iChoose]);
            } else if (iChoose != -1) {
                System.out.println("Invalid Entry");
            }
            if(iChoose != -1) {
                get.aWait();
            }
        }
    }

    private static void GameSelection(String GameChoice) {
        UserInput get = new UserInput();
        get.aCleanSlate();
        System.out.println("You have chosen " + GameChoice);

        ArrayList<Player> players = getPlayers();

        if (players != null) {
            switch (GameChoice) {
                case "Black Jack":
                    BlackJack playGame = new BlackJack(players);
                    playGame.start();
                    break;
                default:
                    System.out.println("Invalid game choice.");
                    break;
            }
        } else {
            System.out.println("Game selection canceled.");
        }
    }

    private static ArrayList<Player> getPlayers() {
        UserInput get = new UserInput();
        ArrayList<Player> players = new ArrayList<>();

        System.out.println("Enter the number of players (2-10):");
        int numberOfPlayers = get.anInt();

        if (numberOfPlayers == -1) {
            return null; // Return null to indicate exit
        }

        while (numberOfPlayers < 2 || numberOfPlayers > 10) {
            System.out.println("Invalid number of players. Please enter a number between 2 and 10:");
            numberOfPlayers = get.anInt();

            if (numberOfPlayers == -1) {
                return null; // Return null to indicate exit
            }
        }

        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.println("Enter name for Player " + i + ": ");
            String playerName = get.aString();
            players.add(new Player(playerName));
        }

        return players;
    }
}

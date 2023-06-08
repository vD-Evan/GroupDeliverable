/* Class: SYST 17796 - Fundamentals of Software Design and Development
 * Assignment: Group Deliverable
 * Authors: Juliana Sebastian, May Myat Noe Swe, Evan VanDuzer
 * Last Revision: 8 June 2023 
 */

package ca.sheridan.syst17796.groupdeliverable.game;

import ca.sheridan.syst17796.groupdeliverable.deck.StandardDeck;
import ca.sheridan.syst17796.groupdeliverable.card.CreateCard;
import ca.sheridan.syst17796.groupdeliverable.misc.GameUtils;
import ca.sheridan.syst17796.groupdeliverable.ui.UserInput;
import ca.sheridan.syst17796.groupdeliverable.misc.Player;
import java.util.ArrayList;

public class BlackJack {
    private ArrayList<Player> players;
    private StandardDeck deck;
    UserInput get = new UserInput();

    public BlackJack(ArrayList<Player> players) {
        this.players = players;
        this.deck = new StandardDeck();
    }

    public void runGame() {
        // Shuffle the deck
        deck.shuffle();
        GameUtils goTo = new GameUtils();

        // Deal 2 cards to each player
        for (Player player : players) {
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
        }

        // check for any immediate winners (ie. Black Jack)
        if (determineOutcome() == "Game Done")
            return;

        // Run the Game
        boolean gameEnd = false;
        while (!gameEnd) {
            for (int i = 0; i < players.size(); i++) {
                Player currentPlayer = players.get(i);
                if (calculateHandValueForGame(currentPlayer) > 21 || currentPlayer.hasDecidedToStand()) {
                    continue; // Skip the turn if the player 'Bust' or has decided to 'Stand'
                }

                // Displays cards visible on the table
                showTable(currentPlayer);

                // Prompt the player for their action
                getAction(currentPlayer);

                if (determineOutcome() == "Game Done")
                    gameEnd = true;
                else if (determineOutcome() == "All Bust") {
                    get.aCleanSlate();
                    System.out.println("End of Game - All Players Bust.");
                    gameEnd = true;
                }

                if (!gameEnd)
                    goTo.nextPlayer(); // Only run this after checking for winner; no need to start new round if
                                       // current round ends game
            }

        }
    }

    private void showTable(Player currentPlayer) {
        get.aCleanSlate();
        System.out.println("----- Table -----");

        // Display other player's cards
        for (Player player : players) {
            if (player != currentPlayer) { // Do not show current player's card yet
                String playerState = " - Active";
                if (calculateHandValueForGame(player) > 21)
                    playerState = " - Bust";
                else if (player.hasDecidedToStand())
                    playerState = " - Stand";
                System.out.println("Player: " + player.getName() + playerState);
                System.out.print("Cards: ");
                ArrayList<CreateCard> hand = player.getHand();
                for (int i = 1; i < hand.size(); i++) { // Do not show the first card dealt; each player has a "Hidden"
                                                        // card
                    CreateCard card = hand.get(i);
                    System.out.print(card + "  ");
                }
                System.out.printf("%n-----------------%n"); // Separator between players
            }
        }
        System.out.println("");

        // Display Current Player's hand, including the hidden card
        System.out.println(currentPlayer.getName() + "'s Turn - Your Hand:");
        ArrayList<CreateCard> currentHand = currentPlayer.getHand();
        for (int i = 0; i < currentHand.size(); i++) {
            CreateCard card = currentHand.get(i);
            if (i == 0)
                System.out.println("[Hidden Card] " + card);
            else
                System.out.println(card);
        }
        System.out.printf("Value of Hand: %d%n", calculateHandValueForGame(currentPlayer)); // Sums up players hand for
                                                                                            // quick reference
    }

    private void getAction(Player currentPlayer) { // This holds the player's turn
        boolean validChoice = false;
        while (!validChoice) { // Again, syntax for using a boolean as an argument; ![boolean] indicates if
                               // *not* true
            System.out.println("Choose an action: [1] Hit, [2] Stand");
            int choice = get.anInt();
            if (choice == 1) {
                // Deal a new card to the player
                CreateCard newCard = deck.dealCard(); // Creates a new Card object
                currentPlayer.addCard(newCard); // Adds the new card to end of player's hand Array List
                System.out.println("New card dealt: " + newCard);
                validChoice = true;
                if (calculateHandValueForGame(currentPlayer) > 21) {
                    System.out.println("Bust."); // Oops
                }
            } else if (choice == 2) {
                System.out.println("Player " + currentPlayer.getName() + " has decided to stand!");
                currentPlayer.setDecidedToStand(true);
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please try again."); // Ensures a valid (1 or 2) has been entered
            }
        }
    }

    private String determineOutcome() {
        int numOfPlayers = players.size();
        int numOfBusts = 0;
        int numOfStands = 0;

        for (int i = 0; i < numOfPlayers; i++) {
            Player player = players.get(i);
            if (calculateHandValueForGame(player) > 21)
                numOfBusts++;
            if (player.hasDecidedToStand())
                numOfStands++;
        }
        if (numOfBusts == numOfPlayers)
            return "All Bust";
        if (numOfBusts == (numOfPlayers - 1)) {
            winMessage(numOfPlayers);
            return "Game Done";
        }
        if ((numOfBusts + numOfStands) == numOfPlayers) {
            winMessage(numOfPlayers);
            return "Game Done";
        } else
            return "Continue";
    }

    private void winMessage(int numOfPlayers) {
        get.aCleanSlate();
        ArrayList<Player> validPlayers = new ArrayList<>();
        ArrayList<Player> bustPlayers = new ArrayList<>();

        for (int i = 0; i < numOfPlayers; i++) {
            Player player = players.get(i);
            int handValue = calculateHandValueForGame(player);

            if (handValue <= 21)
                validPlayers.add(player);
            else
                bustPlayers.add(player);
        }
        sortPlayers(bustPlayers);
        sortPlayers(validPlayers);

        if (bustPlayers.size() > 0) {
            System.out.println("Players that Bust:");
            for (Player player : bustPlayers) {
                System.out.printf("%s %s %d (%d %s).%n",
                        player.getName(), "busts with a hand of",
                        calculateHandValueForGame(player),
                        player.getHand().size(), "cards");
            }
            System.out.println("----");
        }

        Player winner = validPlayers.get(0);
        System.out.printf("%s %s %d (%d %s).%n",
                winner.getName(), "wins with a hand of",
                calculateHandValueForGame(winner),
                winner.getHand().size(), "cards");
        if (validPlayers.size() > 1) {
            System.out.println("----");
            System.out.println("Runners Up:");
            for (int i = 1; i < validPlayers.size(); i++) {
                Player player = validPlayers.get(i);
                System.out.printf("#%d - %s %s %d (%d %s).%n", (validPlayers.indexOf(player) + 1),
                        player.getName(), "places with a hand of",
                        calculateHandValueForGame(player),
                        player.getHand().size(), "cards");
            }
        }
        players.clear(); // Delete the entire players list
        validPlayers.clear();
        bustPlayers.clear();
    }

    private void sortPlayers(ArrayList<Player> players) {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = 0; j < players.size() - i - 1; j++) {
                Player p1 = players.get(j);
                Player p2 = players.get(j + 1);

                int handValueComparison = Integer.compare(calculateHandValueForGame(p2), calculateHandValueForGame(p1));
                if (handValueComparison > 0) {
                    swapPlayers(players, j, j + 1);
                    continue;
                }
                if (handValueComparison < 0)
                    continue;

                int numCardsComparison = Integer.compare(p2.getHand().size(), p1.getHand().size());
                if (numCardsComparison > 0) {
                    swapPlayers(players, j, j + 1);
                    continue;
                }
                if (numCardsComparison < 0)
                    continue;

                ArrayList<CreateCard> hand1 = p1.getHand();
                ArrayList<CreateCard> hand2 = p2.getHand();
                for (int k = 0; k < hand1.size(); k++) {
                    int cardValueComparison = Integer.compare(hand2.get(k).getValueAsInt(),
                            hand1.get(k).getValueAsInt());
                    if (cardValueComparison > 0) {
                        swapPlayers(players, j, j + 1);
                        break;
                    }
                    if (cardValueComparison < 0) {
                        break;
                    }
                }
            }
        }
    }

    private void swapPlayers(ArrayList<Player> players, int i, int j) {
        Player temp = players.get(i);
        players.set(i, players.get(j));
        players.set(j, temp);
    }

    private int calculateHandValueForGame(Player player) { // Needs a special Hand Value to accommodate for Aces being 1
                                                           // or 11, Face being 10
        int handValue = 0;
        int numAces = 0;

        for (CreateCard card : player.getHand()) {
            if (card.getValueAsInt() == 1) { // Ace
                handValue += 11;
                numAces++;
            } else if (card.getValueAsInt() >= 11 && card.getValueAsInt() <= 13) { // Face Card (Jack, Queen, King)
                handValue += 10;
            } else {
                handValue += card.getValueAsInt();
            }
        }

        // Adjust the value of Ace if needed
        while (numAces > 0 && handValue > 21) {
            handValue -= 10;
            numAces--; // Using a boolean to check for aces might encounter a niche case:
        } // If the player has a 10, a 2, and two aces, it would return 24 instead of 14
          // Looping through the aces enusures they offer maximum value while not forcing
          // a Bust

        return handValue;
    }

    // Potential for a new Method to handle displaying win / game-end screen
}

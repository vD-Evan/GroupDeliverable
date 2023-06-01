package ca.sheridan.syst17796.groupdeliverable.game;

import ca.sheridan.syst17796.groupdeliverable.misc.Player;
import ca.sheridan.syst17796.groupdeliverable.ui.UserInput;
import ca.sheridan.syst17796.groupdeliverable.card.CreateCard;
import ca.sheridan.syst17796.groupdeliverable.deck.StandardDeck;
import java.util.ArrayList;

public class BlackJack {
    private ArrayList<Player> players;
    private StandardDeck deck;
    UserInput get = new UserInput();

    public BlackJack(ArrayList<Player> players) {
        this.players = players;
        this.deck = new StandardDeck();
    }

    public void start() {
        // Shuffle the deck
        deck.shuffle();

        // Deal 2 cards to each player
        for (Player player : players) {
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
        }

        // Check if any player has an ace and a face card for a winning hand
        for (Player player : players) {
            if (hasAceAndFace(player)) { // If using a boolean in an if: boolean value is used (true = run if; false =
                                         // do not run if, optionally run else)
                System.out.println(player.getName() + " wins!");
                return; // End the game
            }
        }

        // Run the Game
        boolean allPlayersStand = false;
        boolean gameEnd = false; // May delete this variable; it appears as though it has no functionality and
                                 // exists as an artifact of an older concept
        while (!allPlayersStand && players.size() > 1 && !gameEnd) { // Need to workshop this condition; allow for an
                                                                     // easy cancel game condition
            for (int i = 0; i < players.size(); i++) {
                Player currentPlayer = players.get(i);
                if (calculateHandValueForGame(currentPlayer) > 21 || currentPlayer.hasDecidedToStand()) {
                    continue; // Skip the turn if the player 'Bust' or has decided to 'Stand'
                }

                // Displays cards visible on the table
                showTable(currentPlayer);

                // Prompt the player for their action
                getAction(currentPlayer);

                // Check for game-ending condition, switch the result
                String winner = determineWinner(currentPlayer);
                switch (winner) {
                    case "Bust":
                        System.out.println("All Players have Bust. Please Play Again");
                        return;
                    case "Continue": // separating for further versatility
                        continue;
                    default: // Might tweak the display winner message.
                        System.out.println(winner + " wins!");
                        return;
                }
            }
        }
    }

    private boolean hasAceAndFace(Player player) {
        boolean hasAce = false;
        boolean hasFace = false;

        // Sets Ace to 11, face to 10, for early win-condition
        for (CreateCard card : player.getHand()) {
            if (card.getValueAsInt() == 1) { // Ace
                hasAce = true;
            } else if (card.getValueAsInt() >= 11 && card.getValueAsInt() <= 13) { // Face card (Jack, Queen, King)
                hasFace = true;
            }
        }

        return hasAce && hasFace; // Returns true *only if* both conditions are true
    }

    private void showTable(Player currentPlayer) {
        get.aCleanSlate();
        System.out.println("----- Table -----");

        // Display other player's cards
        for (Player player : players) {
            if (player != currentPlayer) { // Do not show current player's card yet
                System.out.println("Player: " + player.getName());
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

        // Code to allow for multiple players to use this code without seeing each
        // other's hidden card
        // May move this to a separate method later
        get.aWait();
        get.aCleanSlate();
        System.out.println("Please pass the device to the next player.");
        get.aWait();
    }

    private String determineWinner(Player currentPlayer) {
        boolean allPlayersBust = true;
        Player winner = null;
        int highestScore = 0;

        int handValue = calculateHandValueForGame(currentPlayer); // Variable used instead of function to keep code
                                                                  // concise
        if (handValue == 21)
            return currentPlayer.getName(); // First one to proc this will have the fewest cards in their hand; thus the
                                            // winner

        // Should check to see if all but one player has bust regardless if the last
        // player has decided to Stand.
        // This check would go here.

        // Checks to ensure not all players exceed 21;
        for (Player player : players) {
            handValue = calculateHandValueForGame(player);
            if (handValue <= 21)
                allPlayersBust = false;
        }
        if (allPlayersBust)
            return "Bust";
        // If it reaches here, not all players have bust.

        for (Player player : players) { // Checks to see if there are still active (ie. those not yet deceided to
                                        // "Stand") players
            handValue = calculateHandValueForGame(player);
            if (!player.hasDecidedToStand() && handValue <= 21)
                return "Continue"; // At least one player is still active: Not decided to Stand, Not Bust
        }

        // If it reaches here, all players have decided to Stand or are Bust
        for (Player player : players) {
            handValue = calculateHandValueForGame(player);
            if (handValue <= 21 && handValue > highestScore)
                winner = player;
        }
        return winner.getName();
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

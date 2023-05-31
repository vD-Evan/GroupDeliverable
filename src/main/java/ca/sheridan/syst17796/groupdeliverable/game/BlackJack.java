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
        String winner;

        // Deal 2 cards to each player
        for (Player player : players) {
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
        }

        // Check if any player has an ace and a face card for a winning hand
        for (Player player : players) {
            if (hasAceAndFace(player)) {
                System.out.println(player.getName() + " wins!");
                return; // End the game
            }
        }

        // Run the Game
        boolean allPlayersStand = false;
        boolean gameEnd = false;
        while (!allPlayersStand && players.size() > 1 && !gameEnd) { // Need to workshop this condition; allow for an easy cancel game condition
            for (int i = 0; i < players.size(); i++) {
                Player currentPlayer = players.get(i);
                if (calculateHandValueForGame(currentPlayer) > 21 || currentPlayer.hasDecidedToStand()) {
                    continue; // Skip the turn
                }

                // Displays cards visible on the table
                showTable(currentPlayer);

                // Prompt the player for their action
                getAction(currentPlayer);

                // Check for game-ending condition
                winner = determineWinner(currentPlayer);
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
            if (card.getiValue() == 1) { // Ace
                hasAce = true;
            } else if (card.getiValue() >= 11 && card.getiValue() <= 13) { // Face card (Jack, Queen, King)
                hasFace = true;
            }
        }

        return hasAce && hasFace;
    }

    private void showTable(Player currentPlayer) {
        get.aCleanSlate();
        System.out.println("----- Table -----");
        for (Player player : players) {
            if (player != currentPlayer) {
                System.out.println("Player: " + player.getName());
                System.out.print("Cards: ");
                ArrayList<CreateCard> hand = player.getHand();
                for (int i = 1; i < hand.size(); i++) {
                    CreateCard card = hand.get(i);
                    System.out.print(card + "  ");
                }
                System.out.printf("%n-----------------%n");
            }
        }
        System.out.println("");
        System.out.println(currentPlayer.getName() + "'s Turn - Your Hand:");
        ArrayList<CreateCard> currentHand = currentPlayer.getHand();
        for (int i = 0; i < currentHand.size(); i++) {
            CreateCard card = currentHand.get(i);
            if (i == 0)
                System.out.println("[Hidden Card] " + card);
            else
                System.out.println(card);
        }
        System.out.printf("Value of Hand: %d%n", calculateHandValueForGame(currentPlayer));
    }

    private void getAction(Player currentPlayer) {
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("Choose an action: [1] Hit, [2] Stand");
            int choice = get.anInt();
            if (choice == 1) {
                // Deal a new card to the player
                CreateCard newCard = deck.dealCard();
                currentPlayer.addCard(newCard);
                System.out.println("New card dealt: " + newCard);
                validChoice = true;
                if (calculateHandValueForGame(currentPlayer) > 21) {
                    System.out.println("Bust.");
                }
            } else if (choice == 2) {
                System.out.println("Player " + currentPlayer.getName() + " has decided to stand!");
                currentPlayer.setDecidedToStand(true);
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        get.aWait();
        get.aCleanSlate();
        System.out.println("Please pass the device to the next player.");
        get.aWait();
    }

    private String determineWinner(Player currentPlayer) {
        boolean allPlayersBust = true;
        Player winner = null;
        int highestScore = 0;

        int handValue = calculateHandValueForGame(currentPlayer);
        if (handValue == 21)
            return currentPlayer.getName();

        for (Player player : players) {
            handValue = calculateHandValueForGame(player);
            if (handValue <= 21)
                allPlayersBust = false;
        }
        if (allPlayersBust)
            return "Bust";
        // If it reaches here, not all players have bust.

        for (Player player : players) {
            handValue = calculateHandValueForGame(player);
            if (!player.hasDecidedToStand() && handValue <= 21)
                return "Continue"; // Player is still active: Not decided to Stand, Not Bust
        }

        // If it reaches here, all players have decided to Stand or are Bust
        for (Player player : players) {
            handValue = calculateHandValueForGame(player);
            if (handValue <= 21 && handValue > highestScore)
                winner = player;
        }
        return winner.getName();
    }

    private int calculateHandValueForGame(Player player) {
        int handValue = 0;
        boolean hasAce = false;

        for (CreateCard card : player.getHand()) {
            if (card.getiValue() == 1) { // Ace
                handValue += 11;
                hasAce = true;
            } else if (card.getiValue() >= 11 && card.getiValue() <= 13) { // Face Card (Jack, Queen, King)
                handValue += 10;
            } else {
                handValue += card.getiValue();
            }
        }

        // Adjust the value of Ace if needed
        if (hasAce && handValue > 21) {
            handValue -= 10;
        }

        return handValue;
    }
}


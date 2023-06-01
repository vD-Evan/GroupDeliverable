package ca.sheridan.syst17796.groupdeliverable.misc;

import ca.sheridan.syst17796.groupdeliverable.card.CreateCard;
import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<CreateCard> hand; // Array List for dynamically created hands rather than a hardcap of 52
    private boolean decidedToStand; // Variable for Black Jack; Recommended to rename later if another game has a
                                    // similarly named mechanic

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.decidedToStand = false; // Black Jack
    }

    public String getName() {
        return name;
    }

    public void addCard(CreateCard card) {
        hand.add(card); // Syntax for adding a new object at end of Array List
    }

    public ArrayList<CreateCard> getHand() {
        return hand; // Array List is passed as a variable rather than as an array (No need for
                     // square brackets)
    }

    public int getHandValue() { // Originally made for Black Jack, this code is redundant (Black Jack has
                                // game-specific rules) and remains to be used in other games
        int value = 0;
        for (CreateCard card : hand) {
            value += card.getValueAsInt(); // syntax for accessing an object's properties (read: variables) when object
                                           // is stored in an array
        }
        return value;
    }

    // Below is for Black Jack; similar recommendation as above
    public boolean hasDecidedToStand() {
        return decidedToStand;
    }

    public void setDecidedToStand(boolean decidedToStand) {
        this.decidedToStand = decidedToStand;
    }
}

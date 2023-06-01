package ca.sheridan.syst17796.groupdeliverable.deck;

import ca.sheridan.syst17796.groupdeliverable.card.CreateCard;
import java.util.ArrayList;
import java.util.Collections;

public class DeckUtils {
    public static void shuffle(ArrayList<CreateCard> deck, ArrayList<CreateCard> dealtCards) {
        deck.addAll(dealtCards); // Add back the dealt cards to the deck
        dealtCards.clear(); // Clear the dealt cards list
        Collections.shuffle(deck); // Shuffle all the cards in the deck
    }

    public static CreateCard dealCard(ArrayList<CreateCard> deck, ArrayList<CreateCard> alDealtCards) {
        if (deck.isEmpty()) {
            return null; // To be handled on a game-by-game basis
        } // May add additional code and overwrite if most games have similar results

        CreateCard card = deck.remove(0); // Remove and return the top card
        alDealtCards.add(card); // Add the card to the dealt cards list
        return card; // Sends top card to calling method.
    }
}

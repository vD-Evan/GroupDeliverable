package ca.sheridan.syst17796.groupdeliverable.deck;

import ca.sheridan.syst17796.groupdeliverable.card.CreateCard;
import java.util.ArrayList;
import java.util.Collections;

public class DeckUtils {
    public static void shuffle(ArrayList<CreateCard> alDeck, ArrayList<CreateCard> alDealtCards) {
        alDeck.addAll(alDealtCards); // Add back the dealt cards to the deck
        alDealtCards.clear(); // Clear the dealt cards list
        Collections.shuffle(alDeck); // Shuffle all the cards in the deck
    }

    public static CreateCard dealCard(ArrayList<CreateCard> alDeck, ArrayList<CreateCard> alDealtCards) {
        if (alDeck.isEmpty()) {
            return null; // Or handle the situation when there are no cards left
        }

        CreateCard objCard = alDeck.remove(0); // Remove and return the top card
        alDealtCards.add(objCard); // Add the card to the dealt cards list
        return objCard; // Sends top card to calling method.
    }
}

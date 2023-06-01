package ca.sheridan.syst17796.groupdeliverable.deck;

import ca.sheridan.syst17796.groupdeliverable.card.CreateCard;
import java.util.ArrayList;

public class EuchreDeck { // Specialty Deck; to be used in a game without the standard 52
    private ArrayList<CreateCard> deck; // See StandardDeck for comments
    private ArrayList<CreateCard> dealtCards;

    public EuchreDeck() {
        deck = new ArrayList<>();
        dealtCards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (int suitAsInt = 1; suitAsInt <= 4; suitAsInt++) {
            for (int valueAsInt = 1; valueAsInt <= 13; valueAsInt++) {
                if (valueAsInt == 1 || valueAsInt >= 9) {
                    CreateCard objCard = new CreateCard(suitAsInt, valueAsInt);
                    deck.add(objCard);
                }
            }
        }
    }

    public void shuffle() {
        DeckUtils.shuffle(deck, dealtCards);
    }

    public CreateCard dealCard() {
        return DeckUtils.dealCard(deck, dealtCards);
    }
}

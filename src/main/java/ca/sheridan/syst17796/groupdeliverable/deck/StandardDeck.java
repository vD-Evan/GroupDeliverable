package ca.sheridan.syst17796.groupdeliverable.deck;

import ca.sheridan.syst17796.groupdeliverable.card.CreateCard;
import java.util.ArrayList;

public class StandardDeck {
    private ArrayList<CreateCard> alCards;
    private ArrayList<CreateCard> alDealtCards;

    public StandardDeck() {
        alCards = new ArrayList<>();
        alDealtCards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (int iSuit = 1; iSuit <= 4; iSuit++) {
            for (int iValue = 1; iValue <= 13; iValue++) {
                CreateCard objCard = new CreateCard(iSuit, iValue);
                alCards.add(objCard);
            }
        }
    }

    public void shuffle() {
        DeckUtils.shuffle(alCards, alDealtCards);
    }

    public CreateCard dealCard() {
        return DeckUtils.dealCard(alCards, alDealtCards);
    }
}

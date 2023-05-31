package ca.sheridan.syst17796.groupdeliverable.deck;

import ca.sheridan.syst17796.groupdeliverable.card.CreateCard;
import java.util.ArrayList;

public class EuchreDeck {
    private ArrayList<CreateCard> alCards;
    private ArrayList<CreateCard> alDealtCards;

    public EuchreDeck() {
        alCards = new ArrayList<>();
        alDealtCards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (int iSuit = 1; iSuit <= 4; iSuit++) {
            for (int iValue = 1; iValue <= 13; iValue++) {
                if (iValue == 1 || iValue >= 9) {
                    CreateCard objCard = new CreateCard(iSuit, iValue);
                    alCards.add(objCard);
                }
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

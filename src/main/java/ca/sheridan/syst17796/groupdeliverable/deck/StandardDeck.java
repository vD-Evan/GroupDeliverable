/* Class: SYST 17796 - Fundamentals of Software Design and Development
 * Assignment: Group Deliverable
 * Authors: Juliana Sebastian, May Myat Noe Swe, Evan VanDuzer
 * Last Revision: 8 June 2023 
 */

package ca.sheridan.syst17796.groupdeliverable.deck;

import ca.sheridan.syst17796.groupdeliverable.card.CreateCard;
import java.util.ArrayList;

public class StandardDeck {
    private ArrayList<CreateCard> deck;
    private ArrayList<CreateCard> dealtCards;

    public StandardDeck() {
        deck = new ArrayList<>();
        dealtCards = new ArrayList<>();
        initializeDeck(); // On building a deck, add the cards
    }

    private void initializeDeck() {
        for (int suitAsInt = 1; suitAsInt <= 4; suitAsInt++) {
            for (int valueAsInt = 1; valueAsInt <= 13; valueAsInt++) {
                CreateCard card = new CreateCard(suitAsInt, valueAsInt);
                deck.add(card); // adds cards Ace -> King; suit by suit
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

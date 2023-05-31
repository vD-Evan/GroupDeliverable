package ca.sheridan.syst17796.groupdeliverable.misc;

import java.util.ArrayList;
import ca.sheridan.syst17796.groupdeliverable.card.CreateCard;

public class Player {
    private String sName;
    private ArrayList<CreateCard> alHand;
    private boolean decidedToStand;

    public Player(String sName) {
        this.sName = sName;
        this.alHand = new ArrayList<>();
        this.decidedToStand = false;
    }

    public String getName() {
        return sName;
    }

    public void addCard(CreateCard objCard) {
        alHand.add(objCard);
    }

    public ArrayList<CreateCard> getHand() {
        return alHand;
    }

    public int getHandValue() {
        int value = 0;
        for (CreateCard objCard : alHand) {
            value += objCard.getiValue();
        }
        return value;
    }

    public boolean hasDecidedToStand() {
        return decidedToStand;
    }

    public void setDecidedToStand(boolean decidedToStand) {
        this.decidedToStand = decidedToStand;
    }
}

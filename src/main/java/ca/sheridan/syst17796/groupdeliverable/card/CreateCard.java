package ca.sheridan.syst17796.groupdeliverable.card;

public class CreateCard {
    private int iSuit;
    private int iValue;

    public CreateCard(int iSuit, int iValue) {
        this.iSuit = iSuit;
        this.iValue = iValue;
    }

    public int getiSuit() {
        return iSuit;
    }

    public int getiValue() {
        return iValue;
    }

    public void setiSuit(int iSuit) {
        this.iSuit = iSuit;
    }

    public void setiValue(int iValue) {
        this.iValue = iValue;
    }

    @Override
    public String toString() {
        String sSuit = CardUtils.getSuitString(iSuit);
        String sValue = CardUtils.getRankString(iValue);
        return sValue + " of " + sSuit;
    }
}
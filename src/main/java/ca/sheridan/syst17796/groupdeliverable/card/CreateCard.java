package ca.sheridan.syst17796.groupdeliverable.card;

public class CreateCard {
    private int suitAsInt;
    private int valueAsInt;

    public CreateCard(int suitAsInt, int valueAsInt) {
        this.suitAsInt = suitAsInt;
        this.valueAsInt = valueAsInt;
    }

    public int getSuitAsString() {
        return suitAsInt;
    }

    public int getValueAsInt() {
        return valueAsInt;
    }

    public void setSuitAsInt(int suitAsInt) {
        this.suitAsInt = suitAsInt;
    }

    public void setValueAsInt(int valueAsInt) {
        this.valueAsInt = valueAsInt;
    }

    @Override
    public String toString() {
        String suitAsString = CardUtils.getSuitString(suitAsInt);
        String valueAsString = CardUtils.getRankString(valueAsInt);
        return valueAsString + " of " + suitAsString;
    }
}
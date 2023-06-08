/* Class: SYST 17796 - Fundamentals of Software Design and Development
 * Assignment: Group Deliverable
 * Authors: Juliana Sebastian, May Myat Noe Swe, Evan VanDuzer
 * Last Revision: 8 June 2023 
 */

package ca.sheridan.syst17796.groupdeliverable.card;

public class CreateCard {
    private int suitAsInt; // Handle suits as integers by default for ease of implementation
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
    public String toString() { // Translation of Cards into Strings is handled by Card Utilities
        String suitAsString = CardUtils.getSuitString(suitAsInt); // And passed here to limit
        String valueAsString = CardUtils.getRankString(valueAsInt); // Import Statements
        return valueAsString + " of " + suitAsString;
    }
}
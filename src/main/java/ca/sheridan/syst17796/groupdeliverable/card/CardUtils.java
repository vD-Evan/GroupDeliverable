/* Class: SYST 17796 - Fundamentals of Software Design and Development
 * Assignment: Group Deliverable
 * Authors: Juliana Sebastian, May Myat Noe Swe, Evan VanDuzer
 * Last Revision: 8 June 2023 
 */

package ca.sheridan.syst17796.groupdeliverable.card;

public class CardUtils {
    public static String getRankString(int valueAsInt) {
        if (valueAsInt >= 2 && valueAsInt <= 10) {
            return String.valueOf(valueAsInt); // Simply returns the value
        } else {
            switch (valueAsInt) { // For Ace and Face cards
                case 1:
                    return "Ace";
                case 11:
                    return "Jack";
                case 12:
                    return "Queen";
                case 13:
                    return "King";
                default:
                    return "";
            }
        }
    }

    public static String getSuitString(int suitAsInt) {
        switch (suitAsInt) {
            case 1:
                return "Hearts";
            case 2:
                return "Clubs";
            case 3:
                return "Diamonds";
            case 4:
                return "Spades";
            default:
                return "";
        }
    }
}

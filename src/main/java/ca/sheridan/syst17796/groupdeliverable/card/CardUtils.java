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

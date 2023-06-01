package ca.sheridan.syst17796.groupdeliverable.ui;

import java.util.Scanner;
import java.util.InputMismatchException;

// Purpose of Class is for all Input with built in validation
// This validation ensures that entry matches data type;
// If further validation is required; it should be handled on a per use basis
// This Class also has some utilities: Clearing the console; Forcing a wait before continuing

// Note: Variable names are [x]Temp where [x] = the variable type and Temp = 'Temporary'
//bFlag is a boolean 'Good Input' flag; if false, there is an error with input that may have thrown an exception
// Note: Regions are collapsible in VS Code; they serve little other practical purpose

public class UserInput {
    Scanner input = new Scanner(System.in);
    // #region Declare Variables
    // #region Number Variables
    private double dTemp = 0.0;
    private float fTemp = 0.0f;
    private int iTemp = 0; // #endregion numbers
    // #region text variables
    private String sTemp = "";
    private char cTemp = ' ';
    private boolean bTemp = false; // #endregion text

    private boolean bFlag = false; // #endregion variables

    // #region input
    // #region input numbers
    public double aDouble() { // start of user entry for a double variable
        bFlag = false; // sets a valid entry flag to false
        while (bFlag == false) { // runs while valid entry == false
            try { // tries to get valid input
                dTemp = Double.parseDouble(input.nextLine()); // grabs a number
                bFlag = true; // if no error detected, number = valid, set valid flag to true
            } // end of try block
            catch (NumberFormatException e) { // if number is not valid
                bFlag = false; // ensure valid entry flag is false
            } // end of catch
        } // loops until valid entry given
        return dTemp; // returns valid entry
    } // end of double user entry

    public float aFloat() { // follows same logic as double class
        bFlag = false; // refer to it for comments
        while (bFlag == false) {
            try {
                fTemp = Float.parseFloat(input.nextLine());
                bFlag = true;
            } catch (NumberFormatException e) {
                bFlag = false;
            }
        }
        return fTemp;
    }

    public int anInt() { // follows same logic as double class
        bFlag = false; // refer to it for comments
        while (bFlag == false) {
            try {
                iTemp = Integer.parseInt(input.nextLine());
                bFlag = true;
            } catch (NumberFormatException e) {
                bFlag = false;
            }
        }
        return iTemp;
    } // #endregion input numbers

    // #region input text
    public String aString() { // gets a string
        sTemp = input.nextLine(); // no error checking needed
        return sTemp; // simply returns user input as a string
    }

    public char aCharacter() { // grabs first character entered
        cTemp = input.next().charAt(0); // 'a' and 'asd' will both retun 'a'
        return cTemp; // as 'a' and 'asd' both begin with 'a'
    }

    public boolean aBoolean() { // gets a boolean
        bFlag = false; // could result in a crash, needs a flag for valid entry
        while (bFlag == false) { // runs while no valid entry given
            try { // tries to get a boolean value
                bTemp = input.nextBoolean(); // takes user input
                bFlag = true; // if user input is valid, marks flag as valid
            } catch (InputMismatchException e) { // if entry is not valid
                bFlag = false; // ensure flag is false
            } // and attempt a new input
        } // runs while input is invalid
        return bTemp; // returns a valid boolean
    } // #endregion text input
      // #endregion input

    // #region add-ons
    public void aWait() {
        sTemp = "";
        while (sTemp == "") {
            sTemp = input.nextLine();
        }
    }

    public void aCleanSlate() {
        System.out.print("\033[H\033[2J"); // clears terminal for clean look
    } // #endregion add-ons
}

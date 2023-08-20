/* Class: SYST 17796 - Fundamentals of Software Design and Development
 * Assignment: Group Deliverable
 * Authors: Juliana Sebastian, May Myat Noe Swe, Evan VanDuzer
 * Last Revision: 20 August 2023 
 */

package ca.sheridan.syst17796.groupdeliverable.game;

import java.util.ArrayList;
import ca.sheridan.syst17796.groupdeliverable.misc.Player;
import ca.sheridan.syst17796.groupdeliverable.deck.StandardDeck;

public abstract class AbstractGame {
    //Every game will have a list of players and a standard deck
    protected ArrayList<Player> players;
    protected StandardDeck deck;
    
    public abstract void runGame();
    protected abstract String determineOutcome();

}

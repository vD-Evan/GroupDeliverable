package ca.sheridan.syst17796.groupdeliverable.misc;

import ca.sheridan.syst17796.groupdeliverable.ui.UserInput;

public class GameUtils {
    UserInput get = new UserInput();

    public void nextPlayer() {
        get.aWait();
        get.aCleanSlate();
        System.out.println("Please pass the device to the next player.");
        get.aWait();
    }
}

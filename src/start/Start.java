package start;

import game.TurnHandler;
import game.character.PlayerCharacter;
import game.character.enemies.Ghost;

public class Start {
    public static void main(String[] args) {

       new PlayerCharacter("Pacman");
       new Ghost("Blinky");
       new Ghost("Pinky");
       new Ghost("Clyde").switchSides();

       TurnHandler.getInstance().round();
        TurnHandler.getInstance().round();
        TurnHandler.getInstance().round();
        TurnHandler.getInstance().round();
        TurnHandler.getInstance().round();
        TurnHandler.getInstance().round();

    }
}

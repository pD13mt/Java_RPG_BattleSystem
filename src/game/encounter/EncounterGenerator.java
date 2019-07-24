package game.encounter;

import game.TurnHandler;
import game.character.enemies.Ghost;
import game.character.enemies.Wolf;

public class EncounterGenerator {

    public void generateEncounter(Environment environment){
        new Wolf();
        //new Wolf();
        //new Wolf();

        TurnHandler.getInstance().start();
    }
}

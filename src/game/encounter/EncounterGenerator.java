package game.encounter;

import game.TurnHandler;
import game.character.enemies.Ghost;
import game.character.enemies.Wolf;

public class EncounterGenerator {

    public void generateEncounter(Instantiator instantiator){
        instantiator.instantiate();
        TurnHandler.getInstance().start();
    }
}

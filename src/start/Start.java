package start;

import game.CharacterGenerator;
import game.GameHandler;
import game.TurnHandler;
import game.character.enemies.Ghost;
import game.character.playerRoles.Doctor;
import game.character.playerRoles.Knight;
import game.encounter.EncounterGenerator;
import game.encounter.Environment;
import game.encounter.environments.HauntedForest;
import user.InOutput;

public class Start {
    public static void main(String[] args) {

        
        
        GameHandler.getInstance().addToParty(new Knight("Bob Seger"));
        GameHandler.getInstance().addToParty(new Knight("Geraldo"));
        GameHandler.getInstance().addToParty(new Doctor("House"));

        //new CharacterGenerator().generateChar();

        GameHandler.getInstance().start();
    }
}

package start;

import game.GameHandler;
import game.TurnHandler;
import game.character.enemies.Ghost;
import game.character.playerRoles.Knight;
import game.encounter.EncounterGenerator;
import game.encounter.Environment;
import user.InOutput;

public class Start {
    public static void main(String[] args) {

        GameHandler.getInstance().addToParty(new Knight("Bob Seger"));
        GameHandler.getInstance().addToParty(new Knight("Geraldo"));

       new EncounterGenerator().generateEncounter(new Environment());

        new EncounterGenerator().generateEncounter(new Environment());
    }
}

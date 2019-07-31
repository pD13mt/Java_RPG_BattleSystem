package game;

import game.character.GameCharacter;
import game.encounter.EncounterGenerator;
import game.encounter.Environment;
import game.encounter.environments.HauntedForest;
import user.InOutput;

import java.util.ArrayList;
import java.util.List;

public class GameHandler {

    private static GameHandler instance;
    private List<GameCharacter> party;
    private EncounterGenerator encounterGenerator;
    private List<Environment> environments;

    private GameHandler() {
        party = new ArrayList<>();
        encounterGenerator = new EncounterGenerator();
        environments = new ArrayList<>();
        environments.add(new HauntedForest());
    }

    public void start() {

        chooseArea();

    }

    public void initializParty(){

    }

    public void chooseArea(){
        String[] envNames = new String[environments.size()];
        for (Environment e : environments) {
            if(e.isCompleted()){
                envNames[environments.indexOf(e)] = e.getName() + " (completed)";
            }
            envNames[environments.indexOf(e)] = e.getName();
        }

        boolean chosen = false;
        while(!chosen){
            int envX = InOutput.chooseFromList(envNames, "where do you want to go?");

            if(environments.get(envX).isCompleted()){
                InOutput.ln("area already completed");
            }else{
                InOutput.out("entering " + environments.get(envX).getName());
                encounterGenerator.generateEncounter(environments.get(envX));
            }

        }
    }

    public static GameHandler getInstance() {
        if (instance == null) {
            instance = new GameHandler();
        }
        return instance;
    }

    public void addToParty(GameCharacter character) {
        if (!party.contains(character)) {
            party.add(character);
        }
    }

    public void removeFromParty(GameCharacter character) {
        party.remove(character);
    }

    public List<GameCharacter> getParty() {
        return party;
    }


}

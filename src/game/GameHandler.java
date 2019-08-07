package game;

import game.character.GameCharacter;
import game.environment.Environment;

import java.util.ArrayList;
import java.util.List;

public class GameHandler {

    private static GameHandler instance;
    private List<GameCharacter> party;


    private GameHandler() {
        party = new ArrayList<>();

    }

    public void start(Environment environment) {

        Environment currentEnv = environment;

        new CharacterGenerator().generateChar();


        while(true){
            currentEnv.play();

            currentEnv = currentEnv.getNextEnv();
        }

    }

    public void initializParty(){

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

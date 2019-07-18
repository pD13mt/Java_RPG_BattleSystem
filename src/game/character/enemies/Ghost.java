package game.character.enemies;

import game.TurnHandler;
import game.character.EnemyCharacter;
import game.character.GameCharacter;
import game.character.PlayerCharacter;
import game.character.actions.Attack;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Ghost extends EnemyCharacter {
    public Ghost(String name) {
        super(name);
    }
    
    @Override
    public void act() {
        //get possible targets
        ArrayList<GameCharacter> players = TurnHandler.getInstance().getCharacters().stream().filter(p -> p.isPlayer() != this.isPlayer()).filter(p -> !p.isDead()).collect(Collectors.toCollection(ArrayList::new));
        //attack random target
        if(players.size() > 0)
        new Attack(this).performRandom(players);
    }
    
    //the following is for tespurposes
    public void setInitiative(int initiative){
        this.initiative = initiative;
    }
    
}

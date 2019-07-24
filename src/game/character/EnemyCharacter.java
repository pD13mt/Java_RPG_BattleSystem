package game.character;

import game.character.enemies.Scripted;

import java.util.List;
import java.util.Random;

public abstract class EnemyCharacter extends GameCharacter implements Scripted, Actor {

    protected static int instanceCounter;

    public EnemyCharacter() {
        super();
        position = 2;
        instanceCounter++;
    }

    @Override
    public void turn() {
        this.act();
    }

    @Override
    public String getDescription() {
        return this.type.toString();
    }

    public GameCharacter chooseRandomTarget(List<GameCharacter> possibleTargets) {
        if(possibleTargets.size() > 0){
            return possibleTargets.get(new Random().nextInt(possibleTargets.size()));
        }else{
            return null;
        }
    }
}

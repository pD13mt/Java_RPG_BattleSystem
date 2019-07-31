package game.character;

import game.character.effects.Effect;
import game.character.effects.TurnActivated;
import game.character.enemies.Scripted;

import java.util.List;
import java.util.Random;

public abstract class EnemyCharacter extends GameCharacter implements Scripted, Actor {

    public EnemyCharacter() {
        super();
        position = 2;
    }

    @Override
    public void turn() {
        this.act();

        //triggger turnactivated effects
        for (Effect e:effects) {
            if(e instanceof TurnActivated){
                ((TurnActivated) e).turn();
            }
        }
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

package game.character;

import game.GameConstants;
import game.TurnHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import static java.util.stream.Collectors.toCollection;

public abstract class EnemyCharacter extends GameCharacter implements Scripted, Actor {

    public EnemyCharacter() {
        super();
        this.playerSide = false;
    }

    @Override
    public void turn() {
        this.act();
    }

    @Override
    public String getDescription() {
        return this.type.toString();
    }

    //targeting
    protected GameCharacter targetRandom(int range) {
        ArrayList<GameCharacter> possible = TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(this,p) <= range).collect(toCollection(ArrayList::new));
        if(possible.isEmpty()){
            return null;
        }
        return possible.get(new Random().nextInt(possible.size()));
    }

    protected GameCharacter targetOpponent(int range) {
        ArrayList<GameCharacter> possible = TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(this,p) <= range).filter(p -> p.isPlayerSide() != this.isPlayerSide()).collect(toCollection(ArrayList::new));
        if(possible.isEmpty()){
            return null;
        }
        return possible.get(new Random().nextInt(possible.size()));
    }
    protected GameCharacter targetOpponent(int range, int preferredRow) {
        ArrayList<GameCharacter> possible = TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(this,p) <= range).filter(p -> p.isPlayerSide() != this.isPlayerSide()).filter(p -> p.getPosition() == preferredRow).collect(toCollection(ArrayList::new));
        if(possible.isEmpty()){
            possible = TurnHandler.getInstance().getCharacters().stream().filter(p-> p.isPlayerSide() != this.isPlayerSide()).filter(p -> GameConstants.distance(this,p) <= range).collect(toCollection(ArrayList::new));
            if(possible.isEmpty()){
                return null;
            }
        }
        return possible.get(new Random().nextInt(possible.size()));
    }

    //Integer instead of int because an array of a primitive is treated as a single object by Arrays.asList()
    protected GameCharacter targetRandom(int range, Integer... rows) {
        ArrayList<GameCharacter> possible = TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(this,p) <= range).filter(p -> Arrays.asList(rows).contains(p.getPosition())).collect(toCollection(ArrayList::new));
        if(possible.isEmpty()){
            return null;
        }
        return possible.get(new Random().nextInt(possible.size()));
    }
    @FunctionalInterface
    protected interface CustomTargeting{
        GameCharacter customTarget();
    }
}

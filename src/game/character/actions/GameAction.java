package game.character.actions;

import game.GameConstants;
import game.character.GameCharacter;
import user.InOutput;

import java.util.List;
import java.util.Random;

public abstract class GameAction implements Performable, Targetable {

    protected String name, description;
    protected GameConstants.DamageType type;
    protected GameCharacter owner;
    protected int cost, range;

    public GameAction(GameCharacter owner){
        this.owner = owner;
    }

    public String generateDescription(){
        return "range:" + range + ", cost:" + cost + ", type: " + type;
    }

    @Override
    public GameCharacter chooseTarget(List<GameCharacter> possibleTargets) {
        //specify target    (!write a static method chooseTarget later!)
        String[] names = new String[possibleTargets.size()+1];
        for (int i = 0; i < possibleTargets.size(); i++) {
            names[i] = possibleTargets.get(i).getName();
        }
        names[names.length-1] = "abort";
        int targetNum = InOutput.chooseFromList(names, "choose a target:");
        if(targetNum == possibleTargets.size()){
            return null;
        }else{
            return possibleTargets.get(targetNum);
        }
    }

    @Override
    public GameCharacter chooseRandomTarget(List<GameCharacter> possibleTargets) {
        if(possibleTargets.size() > 0){
            return possibleTargets.get(new Random().nextInt(possibleTargets.size()));
        }else{
            return null;
        }
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

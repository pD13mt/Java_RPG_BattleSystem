package game.character.actions;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import user.InOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Attack extends GameAction{

    public Attack(GameCharacter owner) {
        super(owner);
        name = "attack";
        range = 1;
        description = generateDescription();
    }

    @Override
    public boolean perform() {
        //specify target    (!write a static method chooseTarget later!)
        ArrayList<GameCharacter> possibleTargets = TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner,p) <= range).filter(p -> !p.isDead()).collect(Collectors.toCollection(ArrayList::new));
        String[] names = new String[possibleTargets.size()+1];
        for (int i = 0; i < possibleTargets.size(); i++) {
            names[i] = possibleTargets.get(i).getName();
        }
        names[names.length-1] = "abort";
        int targetNum = InOutput.chooseFromList(names, owner.getName() + "'s turn");
        if(targetNum == possibleTargets.size()){
            return false;
        }
        GameCharacter target = possibleTargets.get(targetNum);
         //GameCharacter target = TurnHandler.getInstance().getCharacters().get(InOutput.chooseCharacter("specify a target"));
        //deal damage
        int power = owner.getStrength();
        target.takeDamage(power);
        //inform player
        TurnHandler.getInstance().addMessage(owner.getName() + " has attacked " + target.getName() + ", dealing " + power + " points of damage");
        return true;
    }
    
    //attack previously specified target
    public boolean perform(GameCharacter target){
        //deal damage
        int power = owner.getStrength();
        target.takeDamage(power);
        //inform player
        TurnHandler.getInstance().addMessage(owner.getName() + " has attacked " + target.getName() + ", dealing " + power + " points of damage");
        return true;
    }
    public boolean performRandom(List<GameCharacter> targets){
        //determine
        ArrayList<GameCharacter> possibleTargets = targets.stream().filter(p -> GameConstants.distance(owner,p) <= range).filter(p -> !p.isDead()).collect(Collectors.toCollection(ArrayList::new));
        GameCharacter target = null;
        if(possibleTargets.size() > 0){
            target = possibleTargets.get(new Random().nextInt(possibleTargets.size()));
        }else{
            return false;
        }
        //deal damage
        int power = owner.getStrength();
        target.takeDamage(power);
        //inform player
        TurnHandler.getInstance().addMessage(owner.getName() + " has attacked " + target.getName() + ", dealing " + power + " points of damage");
        return true;

    }
}

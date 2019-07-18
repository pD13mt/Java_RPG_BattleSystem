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
    public void perform() {
        //specify target
        ArrayList<GameCharacter> possibleTargets = TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner,p) <= range).filter(p -> !p.isDead()).collect(Collectors.toCollection(ArrayList::new));
        String[] names = new String[possibleTargets.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = possibleTargets.get(i).getName();
        }
        GameCharacter target = possibleTargets.get(InOutput.chooseFromList(names, owner.getName() + "'s turn"));
         //GameCharacter target = TurnHandler.getInstance().getCharacters().get(InOutput.chooseCharacter("specify a target"));
        //deal damage
        int power = owner.getStrength();
        target.takeDamage(power);
        //inform player
        TurnHandler.getInstance().addMessage(owner.getName() + " has attacked " + target.getName() + ", dealing " + power + " points of damage");
    }
    
    //attack previously specified target
    public void perform(GameCharacter target){
        //deal damage
        int power = owner.getStrength();
        target.takeDamage(power);
        //inform player
        TurnHandler.getInstance().addMessage(owner.getName() + " has attacked " + target.getName() + ", dealing " + power + " points of damage");
    }
    public void performRandom(List<GameCharacter> targets){
        //determine
        ArrayList<GameCharacter> possibleTargets = targets.stream().filter(p -> GameConstants.distance(owner,p) <= range).filter(p -> !p.isDead()).collect(Collectors.toCollection(ArrayList::new));
        GameCharacter target = possibleTargets.get(new Random().nextInt(possibleTargets.size()));
        //deal damage
        int power = owner.getStrength();
        target.takeDamage(power);
        //inform player
        TurnHandler.getInstance().addMessage(owner.getName() + " has attacked " + target.getName() + ", dealing " + power + " points of damage");

    }
}

package game.characterObservers.actions.attacks;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;
import user.InOutput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Attack extends GameAction {

    public Attack(GameCharacter owner) {
        super(owner);
        name = "attack";
        range = 1;
        type = GameConstants.DamageType.PHYSICAL;
        description = generateDescription();
    }



    @Override
    public boolean perform() {

        GameCharacter target = chooseTarget(TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner,p) <= range).collect(Collectors.toCollection(ArrayList::new))); //Arraylist::new method reference to default constructor of Arraylist
        if(target==null){
            InOutput.ln("no valid targets");
            return false;
        }
        //deal damage
        int power = owner.getStrength();
        TurnHandler.getInstance().addMessage(owner.getName() + " has attacked " + target.getName() + " for " + power + " points of damage");
        target.takeDamage(power, type);
        //inform player

        return true;
    }
}

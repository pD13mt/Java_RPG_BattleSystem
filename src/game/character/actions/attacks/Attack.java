package game.character.actions.attacks;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.character.actions.GameAction;
import user.InOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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

        GameCharacter target = chooseTarget(TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner,p) <= range).collect(Collectors.toCollection(ArrayList::new)));
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

    public boolean performEnemy(){
        //get possible targets
        ArrayList<GameCharacter> targets = TurnHandler.getInstance().getCharacters().stream().filter(p -> p.isPlayer() != owner.isPlayer()).filter(p -> !p.isDead()).collect(Collectors.toCollection(ArrayList::new));
        //attack random target
        if(targets.size() > 0){
            GameCharacter target = chooseRandomTarget(targets.stream().filter(p -> GameConstants.distance(owner,p) <= range).collect(Collectors.toCollection(ArrayList::new)));
            if(target==null){
                return false;
            }
            //deal damage
            int power = owner.getStrength();
            TurnHandler.getInstance().addMessage(owner.getName() + " has attacked " + target.getName() + " for " + power + " points of damage");
            target.takeDamage(power, type);
            return true;
        }else{
            return false;
        }



    }
}

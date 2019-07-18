package game.character.actions;

import game.TurnHandler;
import game.character.GameCharacter;
import user.InOutput;

public class Attack extends GameAction{

    public Attack(GameCharacter owner) {
        super(owner);
        name = "attack";
    }

    @Override
    public void perform() {
        //specify target
         GameCharacter target = TurnHandler.getInstance().getCharacters().get(InOutput.chooseCharacter("specify a target"));
        //deal damage
        int power = owner.getStrength();
        target.takeDamage(power);
        //inform player
        InOutput.out(owner.getName() + " has attacked " + target.getName() + ", dealing " + power + " points of damage");
    }
    
    //attack previously specified target
    public void perform(GameCharacter target){
        //deal damage
        int power = owner.getStrength();
        target.takeDamage(power);
        //inform player
        InOutput.out(owner.getName() + " has attacked " + target.getName() + ", dealing " + power + " points of damage");
    }
}

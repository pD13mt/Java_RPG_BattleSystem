package game.character.actions.attacks;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.character.actions.GameAction;
import user.InOutput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Charge extends GameAction {
    public Charge(GameCharacter owner) {
        super(owner);
        name = "charge";
        range = 2;
        cost = 2;
        type = GameConstants.DamageType.PHYSICAL;
        description = generateDescription() + ", charges forward on horseback";
    }

    @Override
    public boolean perform() {

        //subtract cost
        if(owner.getRp() < cost){
            InOutput.ln("not enough " + owner.getResourceName());
            return false;
        }

        //check if character is on the back row
        if (owner.getPosition() != GameConstants.LONGRANGEPLAYER && owner.getPosition() != GameConstants.LONGRANGEENEMY) {
            InOutput.ln("not enough space to charge forward");
            return false;
        }

        //acquire target
        GameCharacter target = chooseTarget(TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner, p) <= range).collect(Collectors.toCollection(ArrayList::new)));
        if (target == null) {
            InOutput.ln("no valid targets");
            return false;
        }

        //calculate attackpower
        int power = owner.getStrength() * 2;

        //execute
        if (owner.isPlayer()) owner.setPosition(GameConstants.CLOSERANGEPLAYER);
        if (!owner.isPlayer()) owner.setPosition(GameConstants.CLOSERANGEENEMY);

        TurnHandler.getInstance().addMessage(owner.getName() + " charged at " + target.getName() + ", dealing " + power + " damage");
        target.takeDamage(power, type);
        return true;
    }

    @Override
    public boolean performEnemy() {
        return false;
    }
}

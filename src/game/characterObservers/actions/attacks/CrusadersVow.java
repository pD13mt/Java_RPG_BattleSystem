package game.characterObservers.actions.attacks;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;
import user.InOutput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CrusadersVow extends GameAction {
    public CrusadersVow(GameCharacter owner) {
        super(owner);
        this.range = 3;
        this.cost = 1;
        this.name = "crusader's vow";
        this.description = generateDescription() + " deals a small amount of holy damage";
    }

    @Override
    public boolean perform() {


        GameCharacter target = chooseTarget(TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner, p) <= range).collect(Collectors.toCollection(ArrayList::new))); //Arraylist::new method reference to default constructor of Arraylist
        if (target == null) {
            InOutput.ln("no valid targets");
            return false;
        }

        TurnHandler.getInstance().addMessage(owner.getName() + " prays for " + target.getName() + "'s demise");
        target.takeDamage(1, GameConstants.DamageType.HOLY);
        return true;
    }
}

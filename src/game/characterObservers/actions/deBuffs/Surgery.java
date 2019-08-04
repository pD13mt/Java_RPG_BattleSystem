package game.characterObservers.actions.deBuffs;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;
import game.characterObservers.effects.specificEffects.Stunned;
import user.InOutput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Surgery extends GameAction {

    private int recoveryTime = 1;

    public Surgery(GameCharacter owner) {
        super(owner);
        name = "surgery";
        range = 1;
        cost = 3;

        description = generateDescription() + ", fully restores a characters hp but incapacitates them for " + recoveryTime + " rounds";
    }

    @Override
    public boolean perform() {

        GameCharacter target = chooseTarget(TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner, p) <= range).filter(p -> p != owner).collect(Collectors.toCollection(ArrayList::new)));
        if (target == null) {
            InOutput.ln("no valid targets");
            return false;
        }

        target.heal(42069);
        new Stunned(target, recoveryTime);
        TurnHandler.getInstance().addMessage(owner.getName() + " performs surgery on " + target.getName());
        return true;

    }
}

package game.characterObservers.actions.deBuffs;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;
import game.characterObservers.effects.specificEffects.Poinsoned;
import user.InOutput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class InjectPoison extends GameAction {
    public InjectPoison(GameCharacter owner) {
        super(owner);
        name = "inject poison";
        cost = 3;
        range = 1;
        description = generateDescription();
    }

    @Override
    public boolean perform() {

        GameCharacter target = chooseTarget(TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner, p) <= range).collect(Collectors.toCollection(ArrayList::new)));
        if(target == null){
            InOutput.ln("no valid targets");
            return false;
        }

        TurnHandler.getInstance().addMessage(owner.getName() + " injects " + target.getName() + " with poison");
        new Poinsoned(target, 4, 3);
        return true;
    }
}

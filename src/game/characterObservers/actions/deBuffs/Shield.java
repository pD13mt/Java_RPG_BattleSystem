package game.characterObservers.actions.deBuffs;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;
import game.characterObservers.effects.specificEffects.Shielded;
import user.InOutput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Shield extends GameAction {
    public Shield(GameCharacter owner) {
        super(owner);
        range = 1;
        cost = 3;
        name = "shield";
        description = generateDescription() + ", protects a character with a shield";
    }

    @Override
    public boolean perform() {

        GameCharacter target = chooseTarget(TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner,p) <= range).collect(Collectors.toCollection(ArrayList::new)));
        if(target==null){
            InOutput.ln("no valid targets");
            return false;
        }

        if(target == owner){
            new Shielded(target,2);
        }else {
            new Shielded(target,1);
        }
        TurnHandler.getInstance().addMessage(owner.getName() + " protects " + target.getName() + " with their shield");
        return true;
    }
}

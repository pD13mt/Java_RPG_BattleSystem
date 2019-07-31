package game.character.actions.buffs;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.character.actions.GameAction;
import game.character.effects.TurnActivated;
import game.character.effects.specificEffects.Shielded;
import user.InOutput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Shield extends GameAction {
    public Shield(GameCharacter owner) {
        super(owner);
        range = 0;
        cost = 3;
        name = "shield";
        description = "range: " + range + ", cost: " + cost +   ", protects a character with a shield";
    }

    @Override
    public boolean perform() {

        GameCharacter target = chooseTarget(TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner,p) <= range).collect(Collectors.toCollection(ArrayList::new)));
        if(target==null){
            InOutput.ln("no valid targets");
            return false;
        }

        new Shielded(target);
        TurnHandler.getInstance().addMessage(owner.getName() + " protects " + target.getName() + " with their shield");
        return true;
    }

    @Override
    public boolean performEnemy() {
        return false;
    }
}

package game.characterObservers.actions.deBuffs;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;
import game.characterObservers.effects.specificEffects.OnDrugs;
import user.InOutput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GiveDrugs extends GameAction {
    public GiveDrugs(GameCharacter owner) {
        super(owner);
        this.name = "give drugs";
        this.cost = 3;
        this.range = 1;
        this.description = generateDescription() + ", gives performance enhancing drugs to a character";
    }

    @Override
    public boolean perform() {
        GameCharacter target = chooseTarget(TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner, p) <= range).filter(p -> p != owner).collect(Collectors.toCollection(ArrayList::new)));
        if (target == null) {
            InOutput.ln("no valid targets");
            return false;
        }

        TurnHandler.getInstance().addMessage(owner.getName() + " administered drugs to " + target.getName());
        new OnDrugs(target, 3, 2);
        return true;
    }
}

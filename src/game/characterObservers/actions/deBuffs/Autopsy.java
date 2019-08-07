package game.characterObservers.actions.deBuffs;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;
import user.InOutput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Autopsy extends GameAction {

    private int strength;

    public Autopsy(GameCharacter owner) {
        super(owner);
        this.name = "autopsy";
        this.cost = 0;
        this.range = 1;
        this.strength = 2;

        this.description = generateDescription() + ", performs an Autopsy on a " + GameConstants.Type.DEAD + " character in the graveyard and restores " + strength + " " + owner.getResourceName();
    }

    @Override
    public boolean perform() {

        if (TurnHandler.getInstance().getGraveYard().isEmpty()) {
            InOutput.ln("graveyard empty");
            return false;
        }

        GameCharacter target = chooseTarget(TurnHandler.getInstance().getGraveYard().stream().filter(p -> p.getType() == GameConstants.Type.DEAD).collect(Collectors.toCollection(ArrayList::new)));
        if (target == null) {
            return false;
        }

        owner.replenishRp(strength);
        TurnHandler.getInstance().getCharacters().remove(target);
        TurnHandler.getInstance().addMessage(owner.getName() + " performed an autopsy on " + target.getName() + " and regained " + strength + " " + owner.getResourceName());
        return true;
    }
}

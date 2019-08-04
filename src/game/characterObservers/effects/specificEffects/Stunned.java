package game.characterObservers.effects.specificEffects;

import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;
import game.characterObservers.actions.misc.Nothing;
import game.characterObservers.effects.Effect;
import game.characterObservers.effects.TurnActivated;

import java.util.ArrayList;
import java.util.List;

public class Stunned extends Effect implements TurnActivated {


    private List<GameAction> oldActions;

    public Stunned(GameCharacter owner, int turns) {
        super(owner);
        name = "stunned";
        counter = turns;

        this.apply();
    }

    @Override
    public void apply() {
        oldActions = new ArrayList<>();
        oldActions.addAll(owner.getActions());
        owner.getActions().clear();
        for (GameAction a:oldActions) {
            new Nothing(owner);
        }
    }

    @Override
    public void end() {
        owner.getActions().clear();
        owner.getActions().addAll(oldActions);
        TurnHandler.getInstance().addMessage(owner.getName() + " is no longer " + name);
        owner.getEffects().remove(this);
    }

    @Override
    public void turn() {
        counter--;
    }

    @Override
    public String getDescription() {
        return "temporarily incapacitated";
    }
}

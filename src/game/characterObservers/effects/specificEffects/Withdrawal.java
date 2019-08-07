package game.characterObservers.effects.specificEffects;

import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.effects.Effect;
import game.characterObservers.effects.TurnActivated;

public class Withdrawal extends Effect implements TurnActivated {

    int strength;

    public Withdrawal(GameCharacter owner, int strength, int turns) {
        super(owner);
        this.name = "withdrawal";
        this.counter = turns;
        this.strength = strength;

        this.apply();
    }

    @Override
    public String getDescription() {
        return "-" + strength + " strength";
    }

    @Override
    public void apply() {
        TurnHandler.getInstance().addMessage(owner.getName() + " is experiencing withdrawal");
        owner.setStrength(owner.getStrength() - strength);
    }

    @Override
    public void end() {
        TurnHandler.getInstance().addMessage(owner.getName() + " is no longer experiencing withdrawal");
        owner.setStrength(owner.getStrength() + strength);
        owner.getEffects().remove(this);
    }

    @Override
    public void turn() {
        counter--;
    }
}

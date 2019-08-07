package game.characterObservers.effects.specificEffects;

import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.effects.Effect;
import game.characterObservers.effects.TurnActivated;

public class OnDrugs extends Effect implements TurnActivated {

    private int strength;
    private int duration;

    public OnDrugs(GameCharacter owner, int strength, int turns) {
        super(owner);
        this.name = "on drugs";
        this.duration = turns;
        this.counter = turns;
        this.strength = strength;
        this.apply();
    }

    @Override
    public String getDescription() {
        return "+" + strength + " strength";
    }

    @Override
    public void apply() {
        TurnHandler.getInstance().addMessage(owner.getName() + " is on drugs (+" + strength + " strength)");
        owner.setStrength(owner.getStrength() + strength);
    }

    @Override
    public void end() {
        TurnHandler.getInstance().addMessage(owner.getName() + " is no longer on drugs");
        owner.setStrength(owner.getStrength() - strength);
        new Withdrawal(owner, strength, duration);
        owner.getEffects().remove(this);
    }

    @Override
    public void turn() {
        counter--;
    }
}

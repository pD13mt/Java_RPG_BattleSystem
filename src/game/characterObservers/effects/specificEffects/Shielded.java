package game.characterObservers.effects.specificEffects;

import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.effects.Effect;
import game.characterObservers.effects.TurnActivated;

public class Shielded extends Effect implements TurnActivated {

    private int bonus;

    public Shielded(GameCharacter owner, int turns) {
        super(owner);
        counter = turns;
        bonus = 5;
        this.name = "shielded";

        this.apply();
    }

    @Override
    public void apply() {
        owner.setDefence(owner.getDefence() + bonus);

    }

    @Override
    public void end() {
        owner.setDefence((owner.getDefence() - bonus));
        TurnHandler.getInstance().addMessage(owner.getName() + " is no longer " + name);
        owner.getEffects().remove(this);
    }

    @Override
    public void turn() {
        counter--;
    }

    @Override
    public String getDescription() {
        return "hidden behind cover (+" + bonus + " defence)";
    }
}

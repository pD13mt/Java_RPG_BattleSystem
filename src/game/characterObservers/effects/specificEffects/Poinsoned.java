package game.characterObservers.effects.specificEffects;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.deBuffs.InjectPoison;
import game.characterObservers.effects.Effect;
import game.characterObservers.effects.TurnActivated;

import java.security.acl.Owner;

public class Poinsoned extends Effect implements TurnActivated {

    private int strength;

    public Poinsoned(GameCharacter owner, int rounds, int strength) {
        super(owner);

        this.name = "poisoned";
        this.counter = rounds;
        this.strength = strength;

        this.apply();
    }

    @Override
    public void apply() {

        Poinsoned prevPoison = null;
        for (Effect e : owner.getEffects()) {
            if (e instanceof Poinsoned) {
                prevPoison = (Poinsoned) e;
                break;
            }
        }

        if (prevPoison != null && prevPoison != this) {
            TurnHandler.getInstance().addMessage(owner.getName() + " is even more poisoned now");
            prevPoison.setStrength(prevPoison.getStrength() + 1);
            prevPoison.setCounter(prevPoison.getCounter() + 1);
            owner.getEffects().remove(this);
        }else{
            TurnHandler.getInstance().addMessage(owner.getName() + " is poisoned");
        }
    }

    @Override
    public void end() {
        owner.getEffects().remove(this);
        TurnHandler.getInstance().addMessage(owner.getName() + " is no longer poisoned");
    }

    @Override
    public void turn() {
        counter--;
        owner.takeDamage(strength, GameConstants.DamageType.POISON);
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String getDescription() {
        return "takes " + strength + " damage every round";
    }
}

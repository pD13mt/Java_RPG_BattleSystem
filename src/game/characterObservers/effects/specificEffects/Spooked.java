package game.characterObservers.effects.specificEffects;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.effects.DamageActivated;
import game.characterObservers.effects.Effect;

public class Spooked extends Effect implements DamageActivated {
    public Spooked(GameCharacter owner) {
        super(owner);
        this.name = "spooked";
        this.counter = 1;
        this.apply();
    }

    @Override
    public String getDescription() {
        return "deteriorating mental state in anticipation of a big scare";
    }

    @Override
    public void apply() {

        Effect otherSpooked = null;
        for (Effect e : owner.getEffects()) {
            if (e instanceof Spooked && e != this) {
                otherSpooked = e;
                break;
            }
        }
        if (otherSpooked != null || owner.getVulnerabilities().contains(GameConstants.DamageType.PSYCHIC)) {
            TurnHandler.getInstance().addMessage(owner.getName() + " is already spooked");
            owner.getEffects().remove(this);
            return;
        }
        if (owner.getImmunities().contains(GameConstants.DamageType.PSYCHIC) || owner.getResistances().contains(GameConstants.DamageType.PSYCHIC)) {
            TurnHandler.getInstance().addMessage(owner.getName() + " is not spookable");
            owner.getEffects().remove(this);
            return;
        }

        TurnHandler.getInstance().addMessage(owner.getName() + " is spooked");
        owner.addVulnerability(GameConstants.DamageType.PSYCHIC);
    }

    @Override
    public void end() {
        owner.getVulnerabilities().remove(GameConstants.DamageType.PSYCHIC);
    }

    @Override
    public void onDamage() {
        counter--;
    }
}

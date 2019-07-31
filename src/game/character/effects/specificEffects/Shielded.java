package game.character.effects.specificEffects;

import game.GameConstants;
import game.character.GameCharacter;
import game.character.effects.Effect;
import game.character.effects.TurnActivated;

public class Shielded extends Effect implements TurnActivated {

    private int strength;

    public Shielded(GameCharacter owner) {
        super(owner);
        this.name = "shielded";
        counter = 1;
        strength = 3;
        this.apply();
    }

    @Override
    public void apply() {
        owner.setDefence(owner.getDefence() + strength);

    }

    @Override
    public void end() {
        owner.setDefence((owner.getDefence() - strength));
    }

    @Override
    public void turn() {
        if(counter < 1){
            this.end();
        }
        counter--;
    }

    @Override
    public String getDescription() {
        return "character hidden behind cover (+" + strength + " defence)";
    }
}

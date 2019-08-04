package game.character.enemies;

import game.GameConstants;
import game.TurnHandler;
import game.character.EnemyCharacter;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;
import game.characterObservers.effects.specificEffects.Spooked;

import java.util.Random;

import static game.GameConstants.BACKROW;
import static game.GameConstants.FRONTROW;

public class Ghost extends EnemyCharacter {
    public Ghost() {
        super();

        this.name = "Ghost";
        this.setType(GameConstants.Type.ETHEREAL);
        this.position = new Random().nextInt(2);
        this.strength = 0;
        this.defence = 0;
        this.hpMax = 4;
        init();

        new Scare(this);
        new Spook(this);
        new FloatAround(this);


    }

    @Override
    public void init() {

        this.hp = 4;
    }

    @Override
    public void act() {

        /*
        the ghost either spooks (psych. vuln.) or scares (psych. dmg.) a random opponent
        it also has a small chance of changing its position
         */

        int rng = new Random().nextInt(100);

        if(rng < 60){
            actions.get(0).perform();
        }else if(rng < 80){
            actions.get(1).perform();
        }else {
            actions.get(2).perform();
        }

    }

    //the following is for tespurposes
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    private class Scare extends GameAction {

        public Scare(GameCharacter owner) {
            super(owner);
        }

        @Override
        public boolean perform() {
            GameCharacter target = targetOpponent(3);
            if (target == null) {
                return false;
            }

            //IF TARGET ASLEEP MAKE GHOST HAUNT THEIR DREAMS!

            String scaryThing = " shouts \"boo!\" at ";
            int rng = new Random().nextInt(3);
            if (rng == 1) {
                scaryThing = " casts an eerie shadow over ";
            } else if (rng == 2) {
                scaryThing = " phases through ";
            }

            int power = 2;

            TurnHandler.getInstance().addMessage(owner.getName() + scaryThing + target.getName());
            target.takeDamage(2, GameConstants.DamageType.PSYCHIC);
            return true;
        }
    }

    private class Spook extends GameAction {

        public Spook(GameCharacter owner) {
            super(owner);
        }

        @Override
        public boolean perform() {
            GameCharacter target = targetOpponent(3);
            if (target == null) return false;

            TurnHandler.getInstance().addMessage(owner.getName() + " spooks " + target.getName());
            new Spooked(target);
            return true;
        }
    }

    private class FloatAround extends GameAction {

        public FloatAround(GameCharacter owner) {
            super(owner);
        }

        @Override
        public boolean perform() {
            if (owner.getPosition() == FRONTROW) {
                owner.setPosition(BACKROW);
            } else if (owner.getPosition() == BACKROW) {
                owner.setPosition(FRONTROW);
            } else {
                return false;
            }
            TurnHandler.getInstance().addMessage(owner.getName() + " floats around");
            return true;
        }
    }
}

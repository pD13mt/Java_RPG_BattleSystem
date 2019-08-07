package game.character.enemies;

import game.GameConstants;
import game.TurnHandler;
import game.character.EnemyCharacter;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;

public class Wolf extends EnemyCharacter {

    public Wolf() {
        this.name = "Wolf";
        this.setType(GameConstants.Type.BEAST);
        this.hpMax = 7;
        this.hp = 7;

        new Bite(this);
        new CloseIn(this);
        new Growl(this);

        this.init();
    }

    @Override
    public void init() {


        this.strength = 4;

    }

    @Override
    public void act() {

        /*
        the wolf starts in the front row
        and attacks a random PC on the front row each turn
        if it is somehow moved to the back row it uses its turn to move back to the front row
        if no characters are in range when on the front row, the wolf does nothing
         */

        for (GameAction a : actions) {
            if (a.perform()) break;
        }


    }

    private class Bite extends GameAction {

        public Bite(GameCharacter owner) {
            super(owner);
        }

        @Override
        public boolean perform() {

            GameCharacter target = targetOpponent(1,GameConstants.FRONTROW);
            if (target == null) return false;

            int power = owner.getStrength();
            TurnHandler.getInstance().addMessage(owner.getName() + " bites " + target.getName() + " for " + power + " points of damage");
            target.takeDamage(power, GameConstants.DamageType.PHYSICAL);
            return true;
        }
    }
    private class CloseIn extends GameAction{

        public CloseIn(GameCharacter owner) {
            super(owner);
        }

        @Override
        public boolean perform() {
            if(owner.getPosition() != GameConstants.FRONTROW){
                owner.setPosition(GameConstants.FRONTROW);
                TurnHandler.getInstance().addMessage(owner.getName() + " moves forward");
                return true;
            }else {
                return false;
            }
        }
    }

    private class Growl extends GameAction{

        public Growl(GameCharacter owner) {
            super(owner);
        }

        @Override
        public boolean perform() {
            TurnHandler.getInstance().addMessage(owner.getName() + " growls menacingly");
            return true;
        }
    }
}

package game.character.enemies;

import game.GameConstants;
import game.TurnHandler;
import game.character.EnemyCharacter;
import game.character.actions.attacks.Attack;

public class Ghost extends EnemyCharacter {
    public Ghost() {
        super();
    }

    @Override
    public void init() {
        this.name ="Ghost";

        this.setPosition(GameConstants.CLOSERANGEENEMY);

        this.setType(GameConstants.Type.ETHEREAL);
    }

    @Override
    public void act() {

        new Attack(this).performEnemy();
    }

    //the following is for tespurposes
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

}

package game.character.enemies;

import game.GameConstants;
import game.character.EnemyCharacter;
import game.character.actions.attacks.Attack;

public class Wolf extends EnemyCharacter {
    @Override
    public void init() {
        this.name = "Wolf" + instanceCounter;

        this.hp = 7;
        this.strength = 4;

        this.setPosition(GameConstants.CLOSERANGEENEMY);
        this.setType(GameConstants.Type.BEAST);
    }

    @Override
    public void act() {
        new Attack(this).performEnemy();
    }
}

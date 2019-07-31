package game.character.enemies;

import game.GameConstants;
import game.TurnHandler;
import game.character.EnemyCharacter;
import game.character.actions.attacks.Attack;

public class Wolf extends EnemyCharacter {

    public Wolf(){
        this.name = "Wolf";
        this.setType(GameConstants.Type.BEAST);
        this.setPosition(GameConstants.CLOSERANGEENEMY);
        this.hp = 7;

        this.init();
    }
    @Override
    public void init() {


        this.strength = 4;

    }

    @Override
    public void act() {
        new Attack(this).performEnemy();
    }
}

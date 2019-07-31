package game.character.playerRoles;

import game.GameConstants;
import game.character.PlayerCharacter;
import game.character.actions.attacks.Attack;
import game.character.actions.Move;
import game.character.actions.attacks.Charge;
import game.character.actions.buffs.Shield;

public class Knight extends PlayerCharacter {

    public Knight(String name) {
        super(name);

        actions.add(new Move(this));
        actions.add(new Attack(this));
        actions.add(new Charge(this));
        actions.add(new Shield(this));

        this.hpMax = 15;
        this.role = "Knight";
        this.type = GameConstants.Type.HUMAN;
        position = GameConstants.CLOSERANGEPLAYER;

        this.init();
    }

    @Override
    public void init() {

        this.defence = 2;
        this.strength = 5;
        this.initiativeBase = 8;

    }
}

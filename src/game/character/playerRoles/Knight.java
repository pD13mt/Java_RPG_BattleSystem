package game.character.playerRoles;

import game.GameConstants;
import game.character.PlayerCharacter;
import game.character.actions.attacks.Attack;
import game.character.actions.Move;
import game.character.actions.attacks.Charge;

public class Knight extends PlayerCharacter {

    public Knight(String name) {
        super(name);

    }

    @Override
    public void init() {
        actions.add(new Move(this));
        actions.add(new Attack(this));
        actions.add(new Charge(this));

        this.hpMax = 15;
        this.defence = 2;
        this.strength = 5;
        this.initiativeBase = 8;
        this.role = "Knight";
        this.type = GameConstants.Type.HUMAN;

        position = GameConstants.CLOSERANGEPLAYER;
    }
}

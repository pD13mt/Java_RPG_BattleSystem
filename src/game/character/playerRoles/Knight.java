package game.character.playerRoles;

import game.GameConstants;
import game.character.PlayerCharacter;
import game.characterObservers.actions.attacks.Attack;
import game.characterObservers.actions.attacks.CrusadersVow;
import game.characterObservers.actions.misc.Move;
import game.characterObservers.actions.attacks.Charge;
import game.characterObservers.actions.deBuffs.Shield;

public class Knight extends PlayerCharacter {

    public Knight(String name) {
        super(name);

        new Move(this);
        new Attack(this);
        new Charge(this);
        new Shield(this);
        new CrusadersVow(this);

        this.resourceName = "chivalry";

        this.hpMax = 20;
        this.hp = hpMax;

        this.rpMax = 10;
        this.rp = rpMax;

        this.role = GameConstants.PlayerRole.KNIGHT;
        this.type = GameConstants.Type.HUMAN;
        position = GameConstants.FRONTROW;

        this.init();
    }

    @Override
    public void init() {

        this.rp = rpMax;

        this.defence = 3;
        this.strength = 5;
        this.initiativeBase = 8;

    }
}

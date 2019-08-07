package game.character.playerRoles;

import game.GameConstants;
import game.character.PlayerCharacter;
import game.characterObservers.actions.deBuffs.Autopsy;
import game.characterObservers.actions.deBuffs.GiveDrugs;
import game.characterObservers.actions.deBuffs.InjectPoison;
import game.characterObservers.actions.misc.Analyze;
import game.characterObservers.actions.misc.Move;
import game.characterObservers.actions.attacks.Attack;
import game.characterObservers.actions.deBuffs.Surgery;

public class Doctor extends PlayerCharacter {

    public Doctor(String name) {
        super(name);

        new Move(this);
        new Attack(this);
        new InjectPoison(this);
        new GiveDrugs(this);
        new Surgery(this);
        new Autopsy(this);
        new Analyze(this);

        this.resourceName = "medicine";

        this.hpMax = 15;
        this.hp =hpMax;

        this.rpMax = 20;
        this.rp = rpMax;

        this.role = GameConstants.PlayerRole.DOCTOR;
        this.type = GameConstants.Type.HUMAN;
        this.position = GameConstants.BACKROW;

        this.init();
    }

    @Override
    public void init() {


        this.strength = 3;
        this.defence = 0;

        this.initiativeBase = 10;
    }
}

package game.character;

import game.GameConstants;
import game.GameHandler;
import game.TurnHandler;
import game.character.actions.GameAction;
import game.character.effects.DamageActivated;
import game.character.effects.Effect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.GameConstants.*;

public abstract class GameCharacter implements Damageable, Actor {
    protected int hp, hpMax, rp, rpMax, initiative, initiativeBase, strength, defence, position;
    protected boolean dead, player;
    protected String name, resourceName, description;
    protected Type type;
    protected List<DamageType> resistances, immunities, vulnerabilities;
    //protected Tag tag;
    protected TurnHandler handler;
    protected List<GameAction> actions;
    protected List<Effect> effects;

    public GameCharacter() {
        this.handler = TurnHandler.getInstance();
        this.actions = new ArrayList<>();
        this.effects = new ArrayList<>();
        this.handler.addCharacter(this);

        //if no hp/rp value is specified in constructor, hp and rp are set to default value

        this.hpMax = DEFAULTHP;

        this.rpMax = DEFAULTRP;
        this.initiativeBase = DEFAULTINITIATIVE;
        this.strength = DEFAULTSTRENGTH;
        this.defence = DEFAULTDEFENCE;

        this.resistances = new ArrayList<>();
        this.immunities = new ArrayList<>();
        this.vulnerabilities = new ArrayList<>();
        this.resourceName = "rp";
        this.description = "";

        this.hp = hpMax;
        this.rp = rpMax;

    }

    public abstract void init(); //initializes everything that is reset after every encounter, for everything else the constructor is used

    public int rollInitiative() {
        this.initiative = initiativeBase + new Random().nextInt(INITIATIVERANGE);
        return this.initiative;
    }

    public void addResistance(DamageType type) {
        resistances.add(type);
    }

    public void addImmunity(DamageType type) {
        immunities.add(type);
    }

    public void addVulnerability(DamageType type) {
        vulnerabilities.add(type);
    }

    public void addEffect(Effect effect) {
        this.effects.add(effect);
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public int takeDamage(int amount, GameConstants.DamageType type) {

            for (Effect e : effects) {
                if (e instanceof DamageActivated) {
                    ((DamageActivated) e).onDamage();
                    if(e.getCounter()<0){
                        e.end();
                        effects.remove(e);
                    }
                }
            }

        int damage = Math.abs(amount);
        int defence = this.defence;

        for (DamageType t : vulnerabilities) {
            if (t == type) {
                damage = damage * 2;
                TurnHandler.getInstance().addMessage(this.getName() + " is vulnerable to " + t + " damage");
            }
        }
        for (DamageType t : resistances) {
            if (t == type) {
                defence = defence + 3;
                damage = damage / 2;
                TurnHandler.getInstance().addMessage(this.getName() + " is resistant to " + t + " damage");
            }
        }
        for (DamageType t : immunities) {
            if (t == type) {
                damage = 0;
                TurnHandler.getInstance().addMessage(this.getName() + " is immune to " + t + " damage");
            }
        }

        if (isDead()) {
            TurnHandler.getInstance().removeCharacter(this);
            TurnHandler.getInstance().addMessage(this.getName() + "'s body was destroyed");
            return hp;
        }
        if (damage > defence || NONDEFENSIBLE.contains(type)) {
            this.hp -= damage;
        } else {
            TurnHandler.getInstance().addMessage(this.getName() + " took no damage");
        }
        if (this.hp < 1) {
            die();
        }
        return hp;
    }

    public void die() {
        //does different things depending on type
        if (this.getType() == Type.HUMAN || this.type == Type.BEAST) {
            this.setType(Type.DEAD);
        }
        if (this.getType() == Type.ETHEREAL) {
            TurnHandler.getInstance().removeCharacter(this);
        }
        this.dead = true;
        this.hp = 0;
        TurnHandler.getInstance().addMessage(this.getName() + " is dead");
    }

    public void switchSides() {
        this.player = !this.player;
        if (this.position == CLOSERANGEPLAYER) {
            this.position = CLOSERANGEENEMY;
        } else if (this.position == CLOSERANGEENEMY) {
            this.position = CLOSERANGEPLAYER;
        } else if (this.position == LONGRANGEPLAYER) {
            this.position = LONGRANGEENEMY;
        } else if (this.position == LONGRANGEENEMY) {
            this.position = LONGRANGEPLAYER;
        } else if (this.position == SPECIALRANGEPLAYER) {
            this.position = SPECIALRANGENEMY;
        } else if (this.position == SPECIALRANGENEMY) {
            this.position = SPECIALRANGEPLAYER;
        }
    }

    public int getHp() {
        return hp;
    }

    public int getRp() {
        return rp;
    }

    public int getStrength() {
        return strength;
    }

    public List<GameAction> getActions() {
        return actions;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getRpMax() {
        return rpMax;
    }

    public int getDefence() {
        return defence;
    }

    public String getResourceName() {
        return resourceName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        GameConstants.initType(this);
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public abstract String getDescription();
}
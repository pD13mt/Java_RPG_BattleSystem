package game.character;

import game.TurnHandler;
import game.character.actions.Attack;
import game.character.actions.GameAction;
import game.character.actions.TestAction1;
import game.character.actions.TestAction2;

import java.util.ArrayList;

import static game.GameConstants.*;

public abstract class GameCharacter {
    protected int hp,rp, initiative, strength;
    protected boolean dead, player;
    protected final String name;
    protected TurnHandler handler;
    protected ArrayList<GameAction> actions;

    public GameCharacter(String name){
        this.handler = TurnHandler.getInstance();
        this.name = name;
        this.actions = new ArrayList<>();
        this.handler.addCharacter(this);

        //if no hp/rp value is specified in constructor, hp and rp are set to default value
        this.hp = DEFAULTHP;
        this.rp = DEFAULTRP;
        this.initiative = DEFAULTINITIATIVE;
        this.strength = DEFAULTSTRENGTH;

        
    }

    public int takeDamage(int amount){
        this.hp-=amount;
        return hp;
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

    public ArrayList<GameAction> getActions() {
        return actions;
    }

    public int getInitiative(){
        return initiative;
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
}
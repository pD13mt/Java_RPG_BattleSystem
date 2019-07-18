package game.character;

import game.TurnHandler;
import game.character.actions.GameAction;

import java.util.ArrayList;

import static game.GameConstants.*;

public abstract class GameCharacter {
    protected int hp,rp, initiative, strength, position;
    protected boolean dead, player;
    protected String name;
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
        if(this.hp<1){
            die();
        }
        return hp;
    }
    public void die(){
        this.dead = true;
        this.hp = 0;
        this.name = name + DEADTAG;
    }

    public void switchSides(){
        this.player = !this.player;
        if(this.position == CLOSERANGEPLAYER){
            this.position = CLOSERANGEENEMY;
        }else if( this.position == CLOSERANGEENEMY){
            this.position =CLOSERANGEPLAYER;
        }else if(this.position == LONGRANGEPLAYER){
            this.position = LONGRANGEENEMY;
        }else if(this.position == LONGRANGEENEMY){
            this.position = LONGRANGEPLAYER;
        }else if(this.position == SPECIALRANGEPLAYER){
            this.position = SPECIALRANGENEMY;
        }else if(this.position == SPECIALRANGENEMY){
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

    public ArrayList<GameAction> getActions() {
        return actions;
    }

    public int getInitiative(){
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
}
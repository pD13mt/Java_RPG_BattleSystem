package game.character.effects;

import game.character.GameCharacter;

public abstract class Effect implements Applicable{

    protected GameCharacter owner;
    protected String name;
    protected java.lang.String description;

    protected int counter;

    public Effect(GameCharacter owner){
        this.owner = owner;
        this.owner.addEffect(this);
    }

    public String getName(){
        return this.name + " ("+ this.counter + ")";
    }

    public abstract String getDescription();

    public int getCounter() {
        return counter;
    }
}

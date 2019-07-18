package game.character.actions;

import game.character.GameCharacter;

public abstract class GameAction implements Performable {

    protected String name;
    protected GameCharacter owner;
    protected int cost;

    public GameAction(GameCharacter owner){
        this.owner = owner;
    }

    public String getName() {
        return name;
    }
}

package game.character.actions;

import game.character.GameCharacter;

public abstract class GameAction implements Performable {

    protected String name, description;
    protected GameCharacter owner;
    protected int cost, range;

    public GameAction(GameCharacter owner){
        this.owner = owner;
    }

    public String generateDescription(){
        return "range:" + range + ", cost:" + cost;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

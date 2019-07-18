package game.character.actions;

import game.character.GameCharacter;

public class Move extends GameAction{
    public Move(GameCharacter owner) {
        super(owner);
        name = "move";
    }

    @Override
    public void perform() {
        
    }
}

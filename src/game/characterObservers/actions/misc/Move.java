package game.characterObservers.actions.misc;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;

import static game.GameConstants.BACKROW;
import static game.GameConstants.FRONTROW;

public class Move extends GameAction {
    public Move(GameCharacter owner) {
        super(owner);
        name = "move";
    }

    @Override
    public boolean perform() {

        if(owner.getPosition() == FRONTROW){
            owner.setPosition(BACKROW);
        }else if(owner.getPosition() == BACKROW){
            owner.setPosition(FRONTROW);
        }else{
            TurnHandler.getInstance().addMessage(owner.getName() + " could not move");
            return false;
        }
        TurnHandler.getInstance().addMessage(owner.getName() + " moved");
        return true;
    }
}

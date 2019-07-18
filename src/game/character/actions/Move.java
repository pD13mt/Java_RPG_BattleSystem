package game.character.actions;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;

public class Move extends GameAction{
    public Move(GameCharacter owner) {
        super(owner);
        name = "move";
    }

    @Override
    public boolean perform() {
        if(owner.getPosition() == GameConstants.CLOSERANGEPLAYER){
            owner.setPosition(GameConstants.LONGRANGEPLAYER);
        }else if(owner.getPosition() == GameConstants.LONGRANGEPLAYER){
            owner.setPosition(GameConstants.CLOSERANGEPLAYER);
        }else if(owner.getPosition() == GameConstants.LONGRANGEENEMY){
            owner.setPosition(GameConstants.CLOSERANGEENEMY);
        }else if(owner.getPosition() == GameConstants.CLOSERANGEENEMY){
            owner.setPosition(GameConstants.LONGRANGEENEMY);
        }else{
            TurnHandler.getInstance().addMessage(owner.getName() + " could not move");
            return false;
        }
        TurnHandler.getInstance().addMessage(owner.getName() + " moved");
        return true;
    }
}

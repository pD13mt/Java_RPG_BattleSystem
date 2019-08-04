package game.characterObservers.actions.misc;

import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;

public class Nothing extends GameAction {
    public Nothing(GameCharacter owner) {
        super(owner);
        name = "nothing";
    }

    @Override
    public boolean perform() {

        TurnHandler.getInstance().addMessage(owner.getName() + " does nothing");
        return true;
    }
}

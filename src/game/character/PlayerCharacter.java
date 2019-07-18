package game.character;

import game.character.actions.Attack;
import game.character.actions.Move;

public class PlayerCharacter extends GameCharacter {
    public PlayerCharacter(String name) {
        super(name);
        player = true;
        position = 3;
    
        //initialize action list (in child class if a characterClassSystem has been implemented
        actions.add(new Move(this));
        actions.add(new Attack(this));
    }

    public void performAction(int i){
        actions.get(i).perform();
    }

    //the following is for tespurposes
    public void setInitiative(int initiative){
        this.initiative = initiative;
    }
}

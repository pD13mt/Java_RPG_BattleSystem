package game.character;

import game.character.actions.Attack;
import game.character.actions.TestAction1;
import game.character.actions.TestAction2;

public class PlayerCharacter extends GameCharacter {
    public PlayerCharacter(String name) {
        super(name);
        player = true;
    
        //initialize action list (in child class if a characterClassSystem has been implemented
        actions.add(new TestAction1(this));//test
        actions.add(new TestAction2(this));//test
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

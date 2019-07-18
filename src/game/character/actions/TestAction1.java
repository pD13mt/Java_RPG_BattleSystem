package game.character.actions;

import game.character.GameCharacter;

public class TestAction1 extends GameAction{

    public TestAction1(GameCharacter owner) {
        super(owner);
        name = "testAction1";
    }

    public void perform(){
        System.out.println(name + " performed");
    }
}

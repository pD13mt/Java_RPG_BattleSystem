package game.character.actions;

import game.character.GameCharacter;

public class TestAction2 extends GameAction{

    public TestAction2(GameCharacter owner) {
        super(owner);
        name = "testAction2";
    }

    public void perform(){
        System.out.println(name + " performed");
    }
}

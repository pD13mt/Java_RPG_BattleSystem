package game.environment;

import game.CharacterGenerator;
import game.GameConstants;
import game.GameHandler;
import game.TurnHandler;
import game.character.enemies.Wolf;
import user.InOutput;

public class TestEnv extends Environment{

    public TestEnv(){
    }

    @Override
    public void play() {
        InOutput.ln("the party enters the testEnvironment");

        InOutput.ln(600,"and encounters a pack of wolves");

        new Wolf().setPosition(GameConstants.BACKROW);
        new Wolf().setPosition(GameConstants.BACKROW);
        new Wolf().setPosition(GameConstants.BACKROW);
        new Wolf().setPosition(GameConstants.BACKROW);

        TurnHandler.getInstance().start();
    }
}

package game.encounter.environments;

import game.GameConstants;
import game.character.enemies.Ghost;
import game.character.enemies.Wolf;
import game.encounter.Environment;

public class TestEnv extends Environment {

    public TestEnv() {
        super();
        this.name = "TestEnvironment";
    }

    @Override
    public void instantiate() {
        switch (this.level){
            case 1:
                new Ghost();
                new Ghost();
                new Ghost();
                level++;
                break;
        }
    }
}

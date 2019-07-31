package game.encounter.environments;

import game.character.enemies.Ghost;
import game.character.enemies.Wolf;
import game.encounter.Environment;

public class HauntedForest extends Environment {

    public HauntedForest() {
        super();
        this.name = "Haunted Forest";
    }

    @Override
    public void instantiate() {
        switch (this.level) {
            case 1:
                new Wolf();
                new Wolf();
                new Wolf();
                level++;
                break;
            case 2:
                new Wolf();
                new Wolf();
                new Ghost();
                new Ghost();
                level++;
                break;
            case 3:
                new Ghost();
                new Ghost();
                new Wolf();
                new Wolf();
                level++;
                break;
            default:
                completed = true;
                break;

        }
    }
}

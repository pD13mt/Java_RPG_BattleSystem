package game.encounter;

import game.character.EnemyCharacter;
import game.character.enemies.Ghost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Environment implements Instantiator{

    protected int level;
    protected boolean completed;
    protected String name;

    public Environment() {
        this.level = 1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }
}

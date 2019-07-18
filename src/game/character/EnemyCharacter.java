package game.character;

public abstract class EnemyCharacter extends GameCharacter{
    public EnemyCharacter(String name) {
        super(name);
        position = 3;
    }
    public abstract void act();
}

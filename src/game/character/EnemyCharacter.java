package game.character;

public abstract class EnemyCharacter extends GameCharacter{
    public EnemyCharacter(String name) {
        super(name);
    }
    public abstract void act();
}

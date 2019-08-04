package game.character;

public interface Scripted {
    void act();

    /*
    the behavior of non player controlled characters is works as follows:
    each enemy has a list of custom actions that are called in order until one returns true or the list is exhausted
     */
}

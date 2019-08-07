package start;


import game.GameHandler;
import game.environment.Tavern;
import game.environment.TestEnv;
import user.InOutput;

public class Start {
    public static void main(String[] args) {


        GameHandler.getInstance().start(new Tavern());
    }
}

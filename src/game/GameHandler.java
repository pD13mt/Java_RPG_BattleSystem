package game;

public class GameHandler {

    private static GameHandler instance;

    private GameHandler(){

    }

    public static GameHandler getInstance(){
        if(instance == null){
            instance = new GameHandler();
        }
        return instance;
    }


}

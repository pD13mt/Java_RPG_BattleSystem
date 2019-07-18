package user;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;

import java.util.ArrayList;
import java.util.List;

public abstract class InOutput {

    public InOutput(){

    }

    //INPUT
    public static int chooseFromList(String[] list,String message){
        IOHelper.out("\n" + message + "\n");
        IOHelper.displayListIndex(list);
        while(true){
            String in = IOHelper.getString();
            checkForCommands(in);
            try {
                int inNum = Integer.parseInt(in);
                if(inNum < list.length && inNum >-1
                ) {
                    return inNum;
                }
            }catch (Exception e){

            }
        }
    }

    public static int chooseCharacter(String message){
        IOHelper.out("\n" + message + "\n");
        String[] list = new String[TurnHandler.getInstance().getCharacters().size()];
        for (int i = 0; i < list.length; i++){
            list[i] = TurnHandler.getInstance().getCharacters().get(i).getName();
        }
        IOHelper.displayListIndex(list);
        while(true){
            String in = IOHelper.getString();
            checkForCommands(in);
            try {
                int inNum = Integer.parseInt(in);
                if(inNum < list.length && inNum >-1
                ) {
                    return inNum;
                }
            }catch (Exception e){

            }
        }
    }

    private static void checkForCommands(String in){
        if(in.equals(GameConstants.COMMANDS[0])){
            System.exit(0);
        }
        if(in.equals(GameConstants.COMMANDS[1])){
            info();
        }
    }
    private static void info(){

    }

    //OUTPUT
    public static void out(String message){
        IOHelper.sep('-');
        IOHelper.out(message);
        IOHelper.sep('-');
    }
    public static void out(List<String> message){
        IOHelper.sep('-');
        for (String m:message) {
            IOHelper.out(m);
        }
        IOHelper.sep('-');
    }
    public static void out(String[] message){
        IOHelper.sep('-');
        for (String m:message) {
            IOHelper.out(m);
        }
        IOHelper.sep('-');
    }

    public static void displayBoard(){

        IOHelper.sep('=');
        int max = 4;
        for (int p = 1; p <= max; p++){
            for (GameCharacter c:TurnHandler.getInstance().getCharacters()) {

                if(c.getPosition() == p){

                    IOHelper.out("|" + c.getName() + ":" + c.getHp() + "|");
                }
            }
            if(p==GameConstants.CLOSERANGEENEMY)IOHelper.out("\n");
            if(p!=max) IOHelper.out("\n");
        }
        IOHelper.sep('=');

    }

    public void playerTurn(){
        System.out.println("playerturn");
    }

    public void enemyTurn(){
        System.out.println("enemyturn");
    }


}

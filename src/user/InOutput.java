package user;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;

import java.util.ArrayList;
import java.util.List;

public class InOutput {

    private InOutput() {

    }

    //INPUT
    public static void endTurn() {
        IOHelper.out("\ntype anything to continue: ");
        checkForCommands(IOHelper.confirm());
    }

    public static int chooseFromList(String[] list, String message) {
        IOHelper.out("\n" + message + "\n");
        IOHelper.displayListIndex(list);
        while (true) {
            String in = IOHelper.getString();
            checkForCommands(in);
            try {
                int inNum = Integer.parseInt(in);
                if (inNum < list.length && inNum > -1
                ) {
                    return inNum;
                }
            } catch (Exception e) {

            }
        }
    }

    public static int chooseCharacter(String message) {
        IOHelper.out("\n" + message + "\n");
        String[] list = new String[TurnHandler.getInstance().getCharacters().size()];
        for (int i = 0; i < list.length; i++) {
            list[i] = TurnHandler.getInstance().getCharacters().get(i).getName();
        }
        IOHelper.displayListIndex(list);
        while (true) {
            String in = IOHelper.getString();
            checkForCommands(in);
            try {
                int inNum = Integer.parseInt(in);
                if (inNum < list.length && inNum > -1
                ) {
                    return inNum;
                }
            } catch (Exception e) {

            }
        }
    }

    private static void checkForCommands(String in) {
        if (in.equals(GameConstants.COMMANDS[0])) {
            System.exit(0);
        }
        if (in.equals(GameConstants.COMMANDS[1])) {
            info();
        }
    }

    private static void info() {
        InOutput.displayBoard();
        characterInfo(TurnHandler.getInstance().getCharacters().get(chooseCharacter("which character would you like to know more about?")));
    }


    //OUTPUT
    public static void out(String message) {
        IOHelper.sep('-');
        IOHelper.out(message);
        IOHelper.sep('-');
    }

    public static void out(List<String> message) {
        IOHelper.sep('-');
        for (String m : message) {
            IOHelper.out(m);
        }
        IOHelper.sep('-');
    }
    public static void ln(String message){
        IOHelper.out(message + "\n");
    }

    public static void out(String[] message) {
        IOHelper.sep('-');
        for (String m : message) {
            IOHelper.out(m);
        }
        IOHelper.sep('-');
    }

    public static void displayBoard() {

        IOHelper.sep('=');
        int max = 4;
        for (int p = 1; p <= max; p++) {
            for (GameCharacter c : TurnHandler.getInstance().getCharacters()) {

                if (c.getPosition() == p) {

                    IOHelper.out("|" + c.getName() + ":" + c.getHp() + "|");
                }
            }
            if (p == GameConstants.CLOSERANGEENEMY) IOHelper.out("\n");
            if (p != max) IOHelper.out("\n");
        }
        IOHelper.sep('=');

    }

    public static void characterInfo(GameCharacter c) {
        IOHelper.out(c.getName() + ": " + c.getDescription() +
                "\nhp: " + c.getHp() + "/" + c.getHpMax() + "\t" + c.getResourceName() + ": " + c.getRp() + "/" + c.getRpMax()
                + "\nstrength: " + c.getStrength() + "\tdefence: " + c.getDefence() +
                "\ninitiative: " + c.getInitiative() + "\t" +
                "\n"); //dont remove the last \n (for formatting reasons)
    }

    public void playerTurn() {
        System.out.println("playerturn");
    }

    public void enemyTurn() {
        System.out.println("enemyturn");
    }


}

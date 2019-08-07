package user;

import game.GameConstants;
import game.GameHandler;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.effects.Effect;

import java.util.ArrayList;
import java.util.List;

public class InOutput {

    private InOutput() {

    }

    //INPUT
    public static String in(String message) {
        IOHelper.out(message);
        return IOHelper.getString();
    }

    public static void endTurn() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            IOHelper.out("type anything to continue");
            IOHelper.confirm();
        }
    }

    public static int chooseFromList(String[] list, String message) {
        IOHelper.out("\n" + message + "\n");
        IOHelper.displayListIndex(list);
        while (true) {
            String in = IOHelper.getString();
            if (checkForCommands(in)) {
                IOHelper.out("\n" + message + "\n");
                IOHelper.displayListIndex(list);
            }
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
            if (checkForCommands(in)) {
                IOHelper.out("\n" + message + "\n");
                IOHelper.displayListIndex(list);
            }
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

    private static boolean checkForCommands(String in) {
        if (in.equals(GameConstants.COMMANDS[0])) {
            System.exit(0);
            return true;
        }
        if (in.equals(GameConstants.COMMANDS[1])) {
            info();
            return true;
        }
        return false;
    }

    private static void info() {
        InOutput.displayBoard();

        String[] names = new String[TurnHandler.getInstance().getCharacters().size()];

        if (TurnHandler.getInstance().getCharacters().isEmpty()) {
            for (GameCharacter c : GameHandler.getInstance().getParty()) {
                names[GameHandler.getInstance().getParty().indexOf(c)] = c.getName() + " " + c.getType() + "\tinitiative: " + c.getInitiative();
            }

            characterInfo(GameHandler.getInstance().getParty().get(chooseFromList(names, "which character would you like to know more about?")));
        } else {

            String[] options = {"initiative", "graveyard", "characterInfo", "back"};

            int choice = chooseFromList(options, "what do you want to know about?");

            switch (choice) {
                case 0:
                    List<GameCharacter> ordered = TurnHandler.getInstance().reOrder(TurnHandler.getInstance().getCharacters(), 0);
                    String[] chars = new String[TurnHandler.getInstance().getCharacters().size()];
                    for (int i = 0; i < chars.length; i++) {
                        chars[i] = ordered.get(i).getInitiative() + " " + ordered.get(i).getName();
                    }
                    InOutput.ln("initiative order: ");
                    IOHelper.displayList(chars);
                    break;
                case 1:
                    String[] deads = new String[TurnHandler.getInstance().getGraveYard().size()];
                    if (TurnHandler.getInstance().getGraveYard().isEmpty()) InOutput.ln("graveyard empty");
                    for (int i = 0; i < deads.length; i++) {
                        deads[i] = TurnHandler.getInstance().getGraveYard().get(i).getInitiative() + " " + TurnHandler.getInstance().getCharacters().get(i).getName();
                    }
                    IOHelper.displayList(deads);
                    break;
                case 2:
                    for (GameCharacter c : TurnHandler.getInstance().getCharacters()) {
                        names[TurnHandler.getInstance().getCharacters().indexOf(c)] = c.getName() + " " + c.getType();
                    }

                    characterInfo(TurnHandler.getInstance().getCharacters().get(chooseFromList(names, "which character would you like to know more about?")));
                    break;
                default:
                    break;
            }


        }
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

    public static void ln(String message) {
        IOHelper.out(message + "\n");
    }

    public static void ln(long delay, String message) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        /*int max = 4;
        for (int p = 1; p <= max; p++) {
            for (GameCharacter c : TurnHandler.getInstance().getCharacters()) {

                if (c.getPosition() == p) {

                    IOHelper.out("|[" + TurnHandler.getInstance().getCharacters().indexOf(c) +"]" + c.getName() + ":" + c.getHp() + "|");
                }
            }
            if (p == GameConstants.CLOSERANGEENEMY) IOHelper.out("\n");
            if (p != max) IOHelper.out("\n");
        }*/

        for (GameCharacter c : TurnHandler.getInstance().getCharacters()) {
            if (!c.isPlayerSide() && c.getPosition() == GameConstants.BACKROW) {
                IOHelper.out("|[" + TurnHandler.getInstance().getCharacters().indexOf(c) + "]" + c.getName() + ":" + c.getHp() + "|");
            }

        }
        IOHelper.out("\n");
        for (GameCharacter c : TurnHandler.getInstance().getCharacters()) {
            if (!c.isPlayerSide() && c.getPosition() == GameConstants.FRONTROW) {
                IOHelper.out("|[" + TurnHandler.getInstance().getCharacters().indexOf(c) + "]" + c.getName() + ":" + c.getHp() + "|");
            }
        }
        IOHelper.out("\n\n");
        for (GameCharacter c : TurnHandler.getInstance().getCharacters()) {
            if (c.isPlayerSide() && c.getPosition() == GameConstants.FRONTROW) {
                IOHelper.out("|[" + TurnHandler.getInstance().getCharacters().indexOf(c) + "]" + c.getName() + ":" + c.getHp() + "|");
            }

        }
        IOHelper.out("\n");
        for (GameCharacter c : TurnHandler.getInstance().getCharacters()) {
            if (c.isPlayerSide() && c.getPosition() == GameConstants.BACKROW) {
                IOHelper.out("|[" + TurnHandler.getInstance().getCharacters().indexOf(c) + "]" + c.getName() + ":" + c.getHp() + "|");
            }

        }

        IOHelper.sep('=');

    }

    public static void characterInfo(GameCharacter c) {
        IOHelper.out(c.getName() + ": " + c.getDescription() +
                "\nhp: " + c.getHp() + "/" + c.getHpMax() + "\t" + c.getResourceName() + ": " + c.getRp() + "/" + c.getRpMax()
                + "\nstrength: " + c.getStrength() + "\tdefence: " + c.getDefence() +
                "\ninitiative: " + c.getInitiative() + "\t" +
                "\n"); //dont remove the last \n (for formatting reasons)


        if (!c.getEffects().isEmpty()) {
            IOHelper.out("effects:\n");
            for (Effect e : c.getEffects()) {
                IOHelper.out(e.getName() + ": " + e.getDescription() + "\n");
            }
        }
    }
}

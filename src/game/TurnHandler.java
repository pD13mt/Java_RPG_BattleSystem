package game;

import game.character.EnemyCharacter;
import game.character.GameCharacter;
import game.character.PlayerCharacter;
import user.InOutput;

import java.util.ArrayList;
import java.util.Collections;

/*
singleton DPa
 */
public final class TurnHandler {

    private static TurnHandler instance;
    private ArrayList<String> message;
    private ArrayList<GameCharacter> characters;

    private TurnHandler() {
        characters = new ArrayList<>();
        message = new ArrayList<>();
    }

    public static TurnHandler getInstance() {
        if (instance == null) {
            instance = new TurnHandler();
        }
        return instance;
    }

    public void addCharacter(GameCharacter c) {
        if (!characters.contains(c))
            characters.add(c);
    }

    public void removeCharacter(GameCharacter c) {
        characters.remove(c);
    }


    public void addMessage(String newMessage) {
        if (message.size() < 1) {
            this.message.add(newMessage);
        } else {
            this.message.add("\n" + newMessage);
        }

    }

    public ArrayList<GameCharacter> getCharacters() {
        return characters;
    }

    public void start() {
        for (GameCharacter c : GameHandler.getInstance().getParty()) {
            addCharacter(c);
        }
        InOutput.displayBoard();
        InOutput.out("you encounter enemies");
        InOutput.endTurn();
        round();

    }

    public void round() {
        boolean won = false;
        while (!won) {
            for (GameCharacter c : characters) {
                c.rollInitiative();
            }
            ArrayList<GameCharacter> charactersToGo = reOrder(characters, 0);
            for (GameCharacter c : charactersToGo) {


                if (c.isPlayer()) {
                    InOutput.displayBoard();
                    InOutput.characterInfo(c);
                }

                this.addMessage(c.getName() + "'s turn:");
                if (!c.isDead()) {
                    c.turn();
                    displayMessage();
                }


                charactersToGo = reOrder(charactersToGo, 1);

                InOutput.endTurn();

            }
            won = winCondition();
            if (loseCondition()) {
                InOutput.out("you lose");
                System.exit(0);
            }
            if (won) {
                end();
            }
        }
    }

    public synchronized void end() {
        InOutput.out("you win");
        characters.clear();
        for (GameCharacter c : GameHandler.getInstance().getParty()) {
            if (c.isDead()) {
                GameHandler.getInstance().removeFromParty(c);
            }
        }
    }

    private boolean winCondition() {
        for (GameCharacter c : characters) {
            if (!c.isPlayer() && !c.isDead()) {
                return false;
            }
        }
        return true;
    }

    private boolean loseCondition() {
        for (GameCharacter c : characters) {
            if (c.isPlayer() && !c.isDead()) {
                return false;
            }
        }
        return true;
    }

    private void displayMessage() {
        if (message.size() < 1) {
            addMessage("nothing happened");
        }
        InOutput.out(message);
        message.clear();
    }


    /*
    returns a version of an arraylist of characters that is sorted by initiative
    while removing a specified number of characters from the start of that arraylist
    also removes dead characters from the list
     */
    private ArrayList<GameCharacter> reOrder(ArrayList<GameCharacter> charactersToGo, int turnsPassed) {
        ArrayList<GameCharacter> newCharactersToGo = new ArrayList<>();
        //remove the characters who are dead or have already taken their turn
        for (GameCharacter c : charactersToGo) {
            if (charactersToGo.indexOf(c) >= turnsPassed && !c.isDead()) {
                newCharactersToGo.add(c);
            }
        }
        //sort the remaining characters based on their initiative
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < newCharactersToGo.size(); i++) {
                int ii = i + 1;

                if (ii != newCharactersToGo.size() && newCharactersToGo.get(i).getInitiative() < newCharactersToGo.get((ii)).getInitiative()) {
                    Collections.swap(newCharactersToGo, i, (ii));
                    sorted = false;
                }
            }
        }
        return newCharactersToGo;
    }
}

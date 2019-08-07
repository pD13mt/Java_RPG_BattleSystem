package game;

import game.character.GameCharacter;
import game.character.PlayerCharacter;
import user.InOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
singleton DPa
 */
public final class TurnHandler {

    private static TurnHandler instance;
    private List<String> message;
    private List<GameCharacter> characters;
    private List<GameCharacter> graveYard;

    private TurnHandler() {
        characters = new ArrayList<>();
        graveYard = new ArrayList<>();
        message = new ArrayList<>();
    }

    public static TurnHandler getInstance() {
        if (instance == null) {
            instance = new TurnHandler();
        }
        return instance;
    }

    public void addCharacter(GameCharacter c) {
        if (!characters.contains(c)) {
            characters.add(c);
            reOrder(characters, 0);
        }
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

    public List<GameCharacter> getCharacters() {
        return characters;
    }

    public List<GameCharacter> getGraveYard() {
        return graveYard;
    }

    public void start() {
        for (GameCharacter c : GameHandler.getInstance().getParty()) {
            addCharacter(c);
        }
        round();

    }

    public void round() {
        boolean won = false;
        int rounds = 1;
        while (!won) {
            for (GameCharacter c : characters) {
                c.rollInitiative();
            }
            List<GameCharacter> charactersToGo = reOrder(characters, 0);

            InOutput.out("\nround: " + rounds + "\n");
            InOutput.endTurn();

            for (GameCharacter c : charactersToGo) {

                if (!c.inGraveYard()) {

                    InOutput.displayBoard();

                    if (c instanceof PlayerCharacter) {
                        InOutput.characterInfo(c);
                    }

                    this.addMessage(c.getName() + "'s turn:");

                    c.turn();

                }
                c.triggerTurnEffects();
                displayMessage();


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

            rounds++;
        }
    }

    public synchronized void end() {
        InOutput.out("you win");
        characters.clear();
        graveYard.clear();

        for (int i = 0; i < GameHandler.getInstance().getParty().size(); i++) {
            GameCharacter c = GameHandler.getInstance().getParty().get(i);
            if (c.inGraveYard()) {
                GameHandler.getInstance().removeFromParty(c);
            }
            c.init();
        }
    }

    private boolean winCondition() {
        for (GameCharacter c : characters) {
            if (!(c instanceof PlayerCharacter) && !c.inGraveYard()) {
                return false;
            }
        }
        return true;
    }

    private boolean loseCondition() {
        for (GameCharacter c : characters) {
            if (c instanceof PlayerCharacter && !c.inGraveYard()) {
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
    public List<GameCharacter> reOrder(List<GameCharacter> charactersToGo, int turnsPassed) {
        ArrayList<GameCharacter> newCharactersToGo = new ArrayList<>();
        //remove the characters who are dead or have already taken their turn
        for (GameCharacter c : charactersToGo) {
            if (charactersToGo.indexOf(c) >= turnsPassed && !c.inGraveYard()) {
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

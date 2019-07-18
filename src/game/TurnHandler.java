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
    //private InOutput io;
    private ArrayList<GameCharacter> characters;

    private TurnHandler(){
        characters = new ArrayList<>();
        //io = new InOutput();
    }

    public static TurnHandler getInstance(){
        if (instance == null){
            instance = new TurnHandler();
        }
        return instance;
    }

    public void addCharacter(GameCharacter c){
        if(!characters.contains(c))
            characters.add(c);
    }

    public ArrayList<GameCharacter> getCharacters(){
        return characters;
    }

    /*

     */
    public void round(){
        ArrayList<GameCharacter> charactersToGo = reOrder(characters, 0);
        for (GameCharacter c:charactersToGo){

            InOutput.displayBoard();

            if(c.isPlayer()){
                playerTurn((PlayerCharacter)c);
            }else {
                enemyTurn((EnemyCharacter)c);
            }

            charactersToGo = reOrder(charactersToGo,1);

        }
    }

    private void playerTurn(PlayerCharacter character){
        String[] actionList = new String[character.getActions().size()];
        for (int i = 0; i < actionList.length; i++){
            actionList[i] = character.getActions().get(i).getName();
        }
        character.performAction(InOutput.chooseFromList(actionList, character.getName() + "'s turn"));
    }
    private void enemyTurn(EnemyCharacter character){

        character.act();
    }


    /*
    returns a version of an arraylist of characters that is sorted by initiative
    while removing a specified number of characters from the start of that arraylist
    also removes dead characters from the list
     */
    private ArrayList<GameCharacter> reOrder(ArrayList<GameCharacter> charactersToGo, int turnsPassed){
        ArrayList<GameCharacter> newCharactersToGo = new ArrayList<>();
        //remove the characters who are dead or have already taken their turn
        for (GameCharacter c:charactersToGo) {
            if(charactersToGo.indexOf(c) >= turnsPassed && !c.isDead()){
                newCharactersToGo.add(c);
            }
        }
        //sort the remaining characters based on their initiative
        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for (int i = 0; i<newCharactersToGo.size(); i++){
                int ii = i+1;

                if (ii!=newCharactersToGo.size() &&newCharactersToGo.get(i).getInitiative() < newCharactersToGo.get((ii)).getInitiative()){
                    Collections.swap(newCharactersToGo,i,(ii));
                    sorted = false;
                }
            }
        }
        return newCharactersToGo;
    }
}

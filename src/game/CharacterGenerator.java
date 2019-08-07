package game;

import game.character.GameCharacter;
import game.character.PlayerCharacter;
import game.character.playerRoles.Doctor;
import game.character.playerRoles.Knight;
import user.InOutput;

import java.util.ArrayList;
import java.util.Collections;

public class CharacterGenerator {

    public GameCharacter generateChar() {

        InOutput.out("generating new Character");

        //choose name

        String name = InOutput.in("what is your character's name?\n");

        //choose role
        ArrayList<GameConstants.PlayerRole> roleArrayList = new ArrayList<>();
        Collections.addAll(roleArrayList, GameConstants.PlayerRole.values());

        String[] roleNames = new String[roleArrayList.size()];

        for (GameConstants.PlayerRole pr : roleArrayList) {
            roleNames[roleArrayList.indexOf(pr)] = pr.toString();
        }

        GameConstants.PlayerRole role = roleArrayList.get(InOutput.chooseFromList(roleNames, "what is your character's job?"));

        PlayerCharacter nChar = null;

        switch (role) {
            case KNIGHT:
                nChar = new Knight(name);
                GameHandler.getInstance().addToParty(nChar);
                break;

            case DOCTOR:
                nChar = new Doctor(name);
                GameHandler.getInstance().addToParty(nChar);
                break;

            default:
                break;
        }
        InOutput.out(nChar.getName() + " the " + nChar.getRole() + " joins the party");
        return nChar;
    }

    public GameCharacter recruitChar() {    //used to recruit characters from a set list of roles (?specified in Environment?)
        return null;
    }

}

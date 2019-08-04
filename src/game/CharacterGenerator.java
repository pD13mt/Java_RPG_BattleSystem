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

        String name = InOutput.in("what is your name?\n");

        //choose role
        ArrayList<GameConstants.PlayerRole> roleArrayList = new ArrayList<>();
        Collections.addAll(roleArrayList, GameConstants.PlayerRole.values());

        String[] roleNames = new String[roleArrayList.size()];

        for (GameConstants.PlayerRole pr : roleArrayList) {
            roleNames[roleArrayList.indexOf(pr)] = pr.toString();
        }

        GameConstants.PlayerRole role = roleArrayList.get(InOutput.chooseFromList(roleNames, "what is your job?"));

        PlayerCharacter nChar = null;

        switch (role) {
            case KNIGHT:
                nChar = new Knight(name);
                GameHandler.getInstance().addToParty(nChar);

            case DOCTOR:
                nChar = new Doctor(name);
                GameHandler.getInstance().addToParty(nChar);

            default:
                InOutput.out(nChar.getName() + " the " + nChar.getRole() + " joins the party");
                return nChar;
        }
    }

    public GameCharacter recruitChar() {    //used to recruit characters from a set list of roles (?specified in Environment?)
        return null;
    }

}

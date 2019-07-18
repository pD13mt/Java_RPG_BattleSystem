package game;

import game.character.GameCharacter;

public final class GameConstants {
    public static final int DEFAULTHP = 10, DEFAULTRP = 10, DEFAULTINITIATIVE = 10, DEFAULTSTRENGTH = 3;

    public static final int CLOSERANGEPLAYER = 3, LONGRANGEPLAYER = 4, CLOSERANGEENEMY = 2, LONGRANGEENEMY = 1, SPECIALRANGEPLAYER = 5, SPECIALRANGENEMY = 0;

    public static final String[] COMMANDS = {"exit", "info"};

    public static final String DEADTAG = "(dead)";

    //positions range from 1 to 4 (0 to 5 in exceptions maybe)


    public static int distance(GameCharacter c1, GameCharacter c2){

        return Math.abs(c1.getPosition() - c2.getPosition());

    }

}

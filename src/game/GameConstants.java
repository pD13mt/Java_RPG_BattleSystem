package game;

import game.character.GameCharacter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public final class GameConstants {
    public static final int DEFAULTHP = 10, DEFAULTRP = 10, DEFAULTINITIATIVE = 10, INITIATIVERANGE = 5, DEFAULTSTRENGTH = 3, DEFAULTDEFENCE = 0;

    public static final int FRONTROW = 0, BACKROW = 1;

    public static final String[] COMMANDS = {"exit", "info"};

    public enum Type {HUMAN, DEAD, MACHINE, ETHEREAL, BEAST}

    public enum DamageType {PHYSICAL, FIRE, ELECTRIC, PSYCHIC, COLD, HOLY, POISON}

    public static List<DamageType> NONDEFENSIBLE = Arrays.asList(DamageType.FIRE, DamageType.COLD, DamageType.POISON, DamageType.PSYCHIC);

    public enum PlayerRole {KNIGHT, DOCTOR}
    
    
    public static void initType(GameCharacter c) {

        c.getImmunities().clear();
        c.getResistances().clear();
        c.getVulnerabilities().clear();

        if (c.getType() == Type.HUMAN) {

        }
        if (c.getType() == Type.DEAD) {
            c.addImmunity(DamageType.PSYCHIC);
            c.addImmunity(DamageType.POISON);
            c.addResistance(DamageType.COLD);
            c.addVulnerability(DamageType.FIRE);
            c.addVulnerability(DamageType.HOLY);
        }
        if (c.getType() == Type.MACHINE) {
            c.addImmunity(DamageType.PSYCHIC);
            c.addImmunity(DamageType.HOLY);
            c.addImmunity(DamageType.POISON);
            c.addResistance(DamageType.COLD);
            c.addResistance(DamageType.FIRE);
            c.addVulnerability(DamageType.ELECTRIC);
        }
        if (c.getType() == Type.ETHEREAL) {
            c.addImmunity(DamageType.PHYSICAL);
            c.addImmunity(DamageType.POISON);
            c.addResistance(DamageType.COLD);
            c.addResistance(DamageType.ELECTRIC);
            c.addVulnerability(DamageType.PSYCHIC);
            c.addVulnerability(DamageType.HOLY);
        }
        if (c.getType() == Type.BEAST){
            c.addVulnerability(DamageType.FIRE);
        }
    }

    //positions range from 1 to 4 (0 to 5 in exceptions maybe)

    public static int distance(GameCharacter c1, GameCharacter c2) {

        GameCharacter[] ca = {c1,c2};
        int[] p = new int[2];

        for (int i = 0; i < 2; i++) {
            if(ca[i].isPlayerSide() && ca[i].getPosition() == BACKROW){
                p[i] = 1;
            }else if(ca[i].isPlayerSide() && ca[i].getPosition() == FRONTROW){
                p[i] = 2;
            }else if(!ca[i].isPlayerSide() && ca[i].getPosition() == FRONTROW){
                p[i] = 3;
            }else if(!ca[i].isPlayerSide() && ca[i].getPosition() == BACKROW) {
                p[i] = 4;
            }else {
                return 10;
            }
        }

        return Math.abs(p[0] - p[1]);

    }

}

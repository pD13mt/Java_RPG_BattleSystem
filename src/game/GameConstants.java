package game;

import game.character.GameCharacter;

public final class GameConstants {
    public static final int DEFAULTHP = 10, DEFAULTRP = 10, DEFAULTINITIATIVE = 10, INITIATIVERANGE = 5, DEFAULTSTRENGTH = 3, DEFAULTDEFENCE = 0;

    public static final int CLOSERANGEPLAYER = 3, LONGRANGEPLAYER = 4, CLOSERANGEENEMY = 2, LONGRANGEENEMY = 1, SPECIALRANGEPLAYER = 5, SPECIALRANGENEMY = 0;

    public static final String[] COMMANDS = {"exit", "info"};

    public enum Type {HUMAN, DEAD, MACHINE, ETHEREAL, BEAST}

    public enum DamageType {PHYSICAL, FIRE, ELECTRIC, PSYCHIC, COLD, HOLY}


    public static void initType(GameCharacter c) {
        if (c.getType() == Type.HUMAN) {

        }
        if (c.getType() == Type.DEAD) {
            c.addImmunity(DamageType.PSYCHIC);
            c.addResistance(DamageType.COLD);
            c.addVulnerability(DamageType.FIRE);
            c.addVulnerability(DamageType.HOLY);
        }
        if (c.getType() == Type.MACHINE) {
            c.addImmunity(DamageType.PSYCHIC);
            c.addImmunity(DamageType.HOLY);
            c.addResistance(DamageType.COLD);
            c.addResistance(DamageType.FIRE);
            c.addVulnerability(DamageType.ELECTRIC);
        }
        if (c.getType() == Type.ETHEREAL) {
            c.addImmunity(DamageType.PHYSICAL);
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

        return Math.abs(c1.getPosition() - c2.getPosition());

    }

}

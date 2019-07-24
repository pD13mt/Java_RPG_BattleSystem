package game.character;

import game.GameConstants;

public interface Damageable {
    int takeDamage(int amount, GameConstants.DamageType type);
    void die();
}

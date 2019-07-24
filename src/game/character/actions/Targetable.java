package game.character.actions;

import game.character.GameCharacter;

import java.util.List;

public interface Targetable {
    GameCharacter chooseTarget(List<GameCharacter> possibleTargets);
    GameCharacter chooseRandomTarget(List<GameCharacter> possibleTargets);
}

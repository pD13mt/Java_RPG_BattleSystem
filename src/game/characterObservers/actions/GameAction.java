package game.characterObservers.actions;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import user.InOutput;

import java.util.List;

public abstract class GameAction implements Performable, Targetable {

    protected String name, description;
    protected GameConstants.DamageType type;
    protected GameCharacter owner;
    protected int cost, range;

    public GameAction(GameCharacter owner) {
        this.owner = owner;
        owner.getActions().add(this);
    }

    public String generateDescription() {
        return "range:" + range + ", cost:" + cost;
    }


    //lets the player choose a Gamecharacter from a previously specified list (based on parameters specific to each action like range, friend/foe or character type)
    @Override
    public GameCharacter chooseTarget(List<GameCharacter> possibleTargets) {
        //specify target    (!write a static method chooseTarget later!)
        String[] names = new String[possibleTargets.size() + 1];
        for (int i = 0; i < possibleTargets.size(); i++) {
            names[i] = "[" + TurnHandler.getInstance().getCharacters().indexOf(possibleTargets.get(i)) + "]" + possibleTargets.get(i).getName();
        }
        names[names.length - 1] = "abort";
        int targetNum = InOutput.chooseFromList(names, "choose a target:");
        if (targetNum == possibleTargets.size()) {
            return null;
        } else {
            return possibleTargets.get(targetNum);
        }
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

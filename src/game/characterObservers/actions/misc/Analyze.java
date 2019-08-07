package game.characterObservers.actions.misc;

import game.GameConstants;
import game.TurnHandler;
import game.character.GameCharacter;
import game.characterObservers.actions.GameAction;
import user.InOutput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Analyze extends GameAction {
    public Analyze(GameCharacter owner) {
        super(owner);
        this.name = "analyze";
        this.cost = 0;
        this.range = 3;
        this.description = generateDescription() + ", analyzes a character's strengths and weaknesses";
    }

    @Override
    public boolean perform() {
        GameCharacter target = chooseTarget(TurnHandler.getInstance().getCharacters().stream().filter(p -> GameConstants.distance(owner,p) <= range).collect(Collectors.toCollection(ArrayList::new))); //Arraylist::new method reference to default constructor of Arraylist
        if(target==null){
            InOutput.ln("no valid targets");
            return false;
        }

        TurnHandler.getInstance().addMessage(target.getName() + "'s type is " + target.getType());
        TurnHandler.getInstance().addMessage(target.getName() + " is vulnerable to: " + target.getVulnerabilities());
        TurnHandler.getInstance().addMessage(target.getName() + " is resistant to: " + target.getResistances());
        TurnHandler.getInstance().addMessage(target.getName() + " is immune to: " + target.getImmunities());
        return true;
    }
}

package game.character;

import game.GameConstants;
import user.InOutput;

public abstract class PlayerCharacter extends GameCharacter {
    
    protected GameConstants.PlayerRole role;


    public PlayerCharacter(String name) {
        super();
        this.name = name;
        this.playerSide = true;
    }

    public boolean performAction(int i) {

        if(actions.get(i).getCost() <= this.rp){
            this.rp -= actions.get(i).getCost();
            return actions.get(i).perform();
        }else {
            InOutput.ln("not enough " + this.resourceName);
            return false;
        }

    }

    @Override
    public void turn() {

        //

        String[] actionList = new String[this.getActions().size()];
        for (int i = 0; i < actionList.length; i++) {
            if (this.getActions().get(i).getDescription() != null) {
                actionList[i] = this.getActions().get(i).getName() + " (" + this.getActions().get(i).getDescription() + ")";
            } else {
                actionList[i] = this.getActions().get(i).getName();
            }
        }
        boolean actionChosen = false;
        while (!actionChosen) {
            actionChosen = this.performAction(InOutput.chooseFromList(actionList, this.getName() + "'s actions:"));
        }

        //trigger turnEffects
        triggerTurnEffects();
    }


    
    public GameConstants.PlayerRole getRole() {
        return role;
    }
    
    @Override
    public String getDescription() {
        return this.role + " " + this.type.toString();
    }
}

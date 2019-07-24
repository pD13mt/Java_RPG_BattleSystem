package game.character;

import user.InOutput;

public abstract class PlayerCharacter extends GameCharacter {

    protected String role;

    public PlayerCharacter(String name) {
        super();
        this.name = name;
        player = true;
    }

    public boolean performAction(int i) {
        return actions.get(i).perform();
    }

    @Override
    public void turn() {
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
            actionChosen = this.performAction(InOutput.chooseFromList(actionList, "actions:"));
        }

    }

    @Override
    public String getDescription() {
        return this.role + " " + this.type.toString();
    }
}

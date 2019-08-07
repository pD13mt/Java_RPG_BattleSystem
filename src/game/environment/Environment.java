package game.environment;

import user.InOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Environment {

    protected String name;
    protected List<Environment> connectedEnv;

    public Environment() {
        connectedEnv = new ArrayList<>();
    }

    public Environment getNextEnv(){

        String[] envNames = new String[connectedEnv.size()];

        for (Environment e:connectedEnv) {
            envNames[connectedEnv.indexOf(e)] = e.getName();
        }

        return connectedEnv.get(InOutput.chooseFromList(envNames,"where will you go next?"));

    }

    public abstract void play();

    public String getName() {
        return name;
    }

}

package game.environment;

import game.CharacterGenerator;
import user.InOutput;

public class Tavern extends Environment {

    public Tavern(){
        super();
        connectedEnv.add(new TestEnv());
    }

    @Override
    public void play() {

        InOutput.ln("you enter the tavern\n" +
                "it is packed with hundreds of adventurers arguing amongst each other\n" +
                "as you look around you see that the tavern keeper is trying to get your attention");


        String[] options = {"talk to the tavernkeeper", "recruit some adventurers", "leave"};

        boolean done = false;
        boolean recruited = false;

        while(!done) {

            int choice = InOutput.chooseFromList(options, "what will you do?");

            switch (choice) {
                case 0:
                    InOutput.ln("Tavern Keeper: \n" +
                            "Greetings adventurer!\n" +
                            "Looking at you I get the feeling that you are looking for an epic quest.\n" +
                            "Well, I happen to have one for you.\n" +
                            "Please go kill the evil vampire lord who has been terrorizing the local population for decades!\n" +
                            "As payment I can't offer more than the intrinsic reward of a job well done, but if that's not enough for you why are you even playing this game?\n" +
                            "To find him all you have to do is follow THIS MAP\n" +
                            "(THIS MAP added to inventory (don't bother looking for it, you don't actually have an inventory (video games, am I right?)))\n");
                    InOutput.ln(8000,"Oh I almost forgot, you should probably recruit some more adventurers before you go since it's dangerous to go alone and all that.");
                    options[2] = "embark on your quest to kill the evil vampire lord";
                    break;
                case 1:
                    if(!recruited){
                        InOutput.ln("because of the complex interpersonal relationships of the tavern's patrons\n you only manage to recruit two adventurers who don't hate eachother");
                        CharacterGenerator cg = new CharacterGenerator();
                        cg.generateChar();
                        cg.generateChar();
                        recruited = true;
                    }else {
                        InOutput.ln("all available adventurers are unwilling to join you because they hate your companions");
                    }
                    break;
                case 2:
                    InOutput.ln("your leave the tavern and begin your journey");
                    InOutput.ln(800,"you come to a crossroads");
                    done = true;
                    break;

                default:
                    break;
            }


        }
    }
}

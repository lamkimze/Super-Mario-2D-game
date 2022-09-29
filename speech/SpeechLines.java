package game.speech;

import java.util.ArrayList;
import java.util.Random;

/**
 * The SpeechLines class is basically an ArrayList for all the speechOptions. Which contains methods for utilities such
 * as adding speech options and getting random speech options.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public class SpeechLines {
    /**
     * ArrayList for all the speech options
     */
    private ArrayList<String> speechOptions;

    /**
     * Constructor
     */
    public SpeechLines(){
        this.speechOptions = new ArrayList<String>();
    }

    /**
     * Adds a speech line to the speechOptions ArrayList
     * @param speechLine the content of talking
     */
    public void addOption(String speechLine){
        this.speechOptions.add(speechLine);
    }

    /**
     * Returns a random speechOption using the Random class
     * @return a String which is the random speech option.
     */
    public String getRandomOption(){
        Random rand = new Random();
        int upperbound = this.speechOptions.size();

        int random_int = rand.nextInt(upperbound);
        return this.speechOptions.get(random_int);
    }
}

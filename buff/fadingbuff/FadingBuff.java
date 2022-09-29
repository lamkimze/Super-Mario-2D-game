package game.buff.fadingbuff;

import game.Status;

/**
 * A class indicate a Fading Buff for an actor.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1
 */
public class FadingBuff {

    /**
     * Keep track on the duration of invincible status on the player
     */
    private int duration;
    /**
     * The Status effect of the Buff
     */
    private final Status effect;

    /**
     * Constructor
     * @param duration initial duration of invincible status appear on the player
     * @param effect the invincible effect on the player
     */
    public FadingBuff(int duration, Status effect){
        this.duration = duration;
        this.effect = effect;
    }

    /**
     * Getter to give the duration of buff left
     * @return int which represents the remaining buff effect duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Getter to give the buff effect on the player
     * @return Status which represents the remaining buff effect
     */
    public Status getEffect() {
        return effect;
    }

    /**
     * Setter for the duration, allows for reseting the buff duration
     * @param duration the new duration of the buff
     */
    public void setDuration(int duration){
        this.duration = duration;
    }

    /**
     * Decrease the duration by one
     */
    public void decreaseDuration(){
        this.duration -= 1;
    }

    /**
     * Method that give the description of buff effect and remaining time
     * @return a String that tell the buff and duration left
     */
    @Override
    public String toString() {
        return this.effect + " for " + this.duration + " turns";
    }
}

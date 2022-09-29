package game.buff.fadingbuff;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  A Singleton manager class which will contain all the fading buffs in the system and using the instance of an Actor
 *  for the key for the HashMap. This allows for more actors to easily be added to the buff system.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1
 */
public class FadingBuffManager {
    /**
     * Creating a hashmap storing the buffs on all actor
     */
    private HashMap<Actor, ArrayList<FadingBuff>> allActorsBuffs  = new HashMap<>();

    /**
     * A singleton FadingBuffManager instance
     */
    private static FadingBuffManager instance;

    /**
     * Get the singleton instance of fading buff manager
     * @return FadingBuffManager singleton instance
     */
    public static FadingBuffManager getInstance(){
        if(instance == null){
            instance = new FadingBuffManager();
        }
        return instance;
    }

    /**
     * Adding the buffs on an actor
     * @param actor actor that has the buffs
     */
    public void addActor(Actor actor){
        this.allActorsBuffs.put(actor, new ArrayList<>());
    }

    /**
     * Method that add the buff to the actor
     * @param actor actor that adding the buff to
     * @param status status that the actor obtain
     * @param duration the duration of the buff status
     */
    public void addFadingBuff(Actor actor, Status status, int duration){
        boolean durationSet = false;
        for (FadingBuff buff: this.getFadingBuffs(actor)){
            if (buff.getEffect() == status){
                buff.setDuration(duration);
                durationSet = true;
            }
        }
        if(!durationSet){
            FadingBuff buff = new FadingBuff(duration, status);
            actor.addCapability(status);
            this.allActorsBuffs.get(actor).add(buff);
        }
    }

    /**
     * Getter to get all the buffs available on the actor
     * @param actor actor that has the buff
     * @return all the buffs on the actor
     */
    public ArrayList<FadingBuff> getFadingBuffs(Actor actor){
        return this.allActorsBuffs.get(actor);
    }

    /**
     * Method that clean the buff on the actor
     * @param actor actor that having the buff and being removed
     */
    public void cleanUpBuffs(Actor actor){
        ArrayList<FadingBuff> tempRemovalList = new ArrayList<>();
        for (FadingBuff buff : this.getFadingBuffs(actor)){
            if (buff.getDuration() <= -1){
                tempRemovalList.add(buff);
            }
        }
        for (FadingBuff buff : tempRemovalList){
            FadingBuffManager.getInstance().getFadingBuffs(actor).remove(buff);
        }
    }

    /**
     * removing the buff from the actor
     * @param actor actor that the buff removed from
     * @param status status to be removed
     */
    public void removeBuff(Actor actor, Status status){
        ArrayList<FadingBuff> tempRemovalList = new ArrayList<>();
        for (FadingBuff buff : this.getFadingBuffs(actor)){
            if (buff.getEffect() == status){
                tempRemovalList.add(buff);
            }
        }
        for (FadingBuff buff : tempRemovalList){
            FadingBuffManager.getInstance().getFadingBuffs(actor).remove(buff);
        }
    }

    /**
     * Method that ticks through all the fading buffs for an actor
     * @param actor the actor who's buffs are ticked through
     */
    public void tickFadingBuffs(Actor actor) {
        for (FadingBuff buff : this.getFadingBuffs(actor)) {
            if (buff.getDuration() == 0) {
                actor.removeCapability(buff.getEffect());
            } else {
                buff.decreaseDuration();
                System.out.println(actor + " is " + buff);
            }
        }
        this.cleanUpBuffs(actor);
    }

}

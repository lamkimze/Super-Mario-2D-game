package game.grounds.highgrounds.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Dirt;
import game.grounds.highgrounds.HighGround;
import game.reset.Resettable;

/**
 * Tree is an abstract class and parent of Mature, Sapling and Sprout.
 * @author Clinton Nguyen, 31500145.
 * @version 1
 */
public abstract class Tree extends HighGround implements Resettable {
    /**
     * Constants used in this class
     */
    private static final int TREE_TO_DIRT_CHANCE = 50;

    private int age = 0;

    /**
     * constructor
     * @param displayChar character represent tree
     */
    public Tree(char displayChar) {
        super(displayChar);
        registerInstance();
    }

    /**
     * Increase age of a tree method.
     */
    public void increaseAge(){
        this.age += 1;
    }

    /**
     * Getter function for age of a tree.
     * @return returns age of tree.
     */
    public int getAge() {
        return age;
    }

    /**
     * Method used to check if actor can enter a specific location.
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(Status.FLY) || actor.hasCapability(Status.NO_RESTRICTED)){
            return true;
        }
        return false;
    }

    /**
     * Method used to add status of reset.
     */
    @Override
    public void setToResetInstance() {
        addCapability(Status.RESET);
    }

    /**
     * Tree can experience the joys of time. Method used to set tree to dirt if game is reset.
     * @param location location instance of the Tree class.
     */
    @Override
    public void tick(Location location) {
        if (this.hasCapability(Status.RESET)){
            this.resetTheInstance(location);
        }
    }

    /**
     * Resets the Tree.
     * @param location the location of Tree that is being reset
     */
    @Override
    public void resetTheInstance(Location location) {
        int num = (int) (Math.random() * 100);
        if (num < TREE_TO_DIRT_CHANCE){
            location.setGround(new Dirt());
            this.addCapability(Status.CONVERTED_TO_DIRT);
        }
        else{
            this.removeCapability(Status.RESET);
        }
    }

    /**
     * initialised success rate as 0.
     * @return returns 0.
     */
    public static int success_rate() {
        return 0;
    }

    /**
     * initalised fall damage as 0.
     * @return returns 0.
     */
    public static int fall_damage() {
        return 0;
    }

    /**
     * Abstract method to be used later by children classes.
     * @return will return relevant string when used later.
     */
    public abstract String toString();

}

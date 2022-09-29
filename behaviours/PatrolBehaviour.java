package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;


import java.util.ArrayList;

/**
 * Class for the PatrolBehaviour.
 * @author Rory Tobin-Underwood
 * @version 1
 */
public class PatrolBehaviour implements Behaviour{

    /**
     * The ArrayList of Locations to patrol
     */
    private ArrayList<Location> patrolCoordinates;
    /**
     * The next Location to patrol too
     */
    private Location nextStop;
    /**
     * The current direction of Patrol along the ArrayList
     */
    private PatrolDirection patrolDirection = PatrolDirection.RIGHT;
    /**
     * The index of the next Location in the ArrayList
     */
    private int nextStopIndex = 0;

    /**
     * Constructor
     * @param patrolLocations the location to will be patrolled
     */
    public PatrolBehaviour(ArrayList<Location> patrolLocations){
        this.patrolCoordinates = patrolLocations;
        this.nextStop = patrolLocations.get(0);
    }

    /**
     * Compute the Euclidean distance between two locations. Reason behind this is it allows for the Actor to move
     * diagonally to closer locations in a euclidean system but not in a manhattan system.
     *
     * @param a the first location
     * @param b the first location
     * @return a double which represents the distance between the two locations.
     */
    private double euclideanDistance(Location a, Location b){
        return Math.sqrt(Math.pow(b.x()-a.x(),2)+Math.pow(b.y()-a.y(),2));
    }

    /**
     * Reverses the direction of the Patrol
     */
    public void reverseDirectionOfPatrol(){
        if (patrolDirection == PatrolDirection.RIGHT){
            patrolDirection = PatrolDirection.LEFT;
        }
        else{
            patrolDirection = PatrolDirection.RIGHT;
        }
    }

    /**
     * Determines the next location to patrol too
     */
    public void determineNewNextStop(){
        if (patrolDirection == PatrolDirection.RIGHT){
            if (this.nextStopIndex + 1 < patrolCoordinates.size()){
                this.nextStopIndex += 1;
            }
            else{
                this.nextStopIndex = 0;
            }
        }
        else if (patrolDirection == PatrolDirection.LEFT) {
            if (this.nextStopIndex - 1 >= 0) {
                this.nextStopIndex -= 1;
            } else {
                this.nextStopIndex = patrolCoordinates.size() - 1;

            }
        }
        this.nextStop = patrolCoordinates.get(this.nextStopIndex);
    }

    /**
     * Determines what action the actor should take
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action for the Actor to execute
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        if (here == this.nextStop){
            this.determineNewNextStop();
        }

        double currentEuclideanDistance = euclideanDistance(here, nextStop);
        double optimalEuclideanDistance = currentEuclideanDistance;
        Exit optimalExit = null;
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                double newEuclideanDistance = euclideanDistance(destination, nextStop);

                if (newEuclideanDistance < optimalEuclideanDistance) {
                    optimalExit = exit;
                    //optimalDistance = newDistance;
                    optimalEuclideanDistance = newEuclideanDistance;

                }
            }
        }

        // Means the path is blocked
        if (optimalExit == null){
            //Reverse direction so the Actor does not just stand still
            this.reverseDirectionOfPatrol();
            this.determineNewNextStop();
            return new DoNothingAction();
        }
        return new MoveActorAction(optimalExit.getDestination(), optimalExit.getName());
    }
}

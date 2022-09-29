package game.behaviours.partollocations;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;

/**
 * Base class for the generating of PatrolLocations
 * @author Rory Tobin-Underwood
 * @version 1
 */
public abstract class PatrolLocations {
    /**
     * The center x coord
     */
    protected int centerXCoordinate;
    /**
     * The center y coord
     */
    protected int centerYCoordinate;

    /**
     * Constructor
     * @param centerXCoordinate the centerXCoordinate for the patroling
     * @param centerYCoordinate the centerYCoordinate for the patroling
     */
    public PatrolLocations(int centerXCoordinate, int centerYCoordinate){
        this.centerXCoordinate = centerXCoordinate;
        this.centerYCoordinate = centerYCoordinate;
    }

    /**
     * Abstract method that generates the ArrayList of Locations.
     * @param map the map that will be patrolled on
     * @return ArrayList of Locations
     */
    public abstract ArrayList<Location> generateLocations(GameMap map);
}

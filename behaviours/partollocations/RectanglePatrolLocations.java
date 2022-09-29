package game.behaviours.partollocations;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;

/**
 * Class for the generating of RectanglePatrolLocations
 * @author Rory Tobin-Underwood
 * @version 1
 */
public class RectanglePatrolLocations extends PatrolLocations{
    /**
     * The distance up and down from the centerYCoordinate
     */
    private int patrolXDistance;
    /**
     * The distance left and right from the centerYCoordinate
     */
    private int patrolYDistance;

    /**
     * Constructor
     * @param centerXCoordinate the center coordinate in the x-axis
     * @param centerYCoordinate the center coordinate in the y-axis
     * @param patrolYDistance the distance of patrol up and down from the y-axis
     * @param patrolXDistance the distance of patrol left and right from the y-axis
     */
    public RectanglePatrolLocations(int centerXCoordinate, int centerYCoordinate, int patrolXDistance, int patrolYDistance) {
        super(centerXCoordinate, centerYCoordinate);
        this.patrolXDistance = patrolXDistance;
        this.patrolYDistance = patrolYDistance;
    }

    /**
     * Generates the locations for a rectangle patrol
     * @param map the map that will be patrolled on
     * @return ArrayList of Locations to patrol
     */
    @Override
    public ArrayList<Location> generateLocations(GameMap map) {
        ArrayList<Location> patrolLocations = new ArrayList<>();
        // Square means 4 corners
        int xCoord1 = centerXCoordinate + patrolXDistance;
        int xCoord2 = centerXCoordinate - patrolXDistance;
        int yCoord1 = centerYCoordinate + patrolYDistance;
        int yCoord2 = centerYCoordinate - patrolYDistance;
        patrolLocations.add(map.at(xCoord1,yCoord1));
        patrolLocations.add(map.at(xCoord1,yCoord2));
        patrolLocations.add(map.at(xCoord2,yCoord2));
        patrolLocations.add(map.at(xCoord2,yCoord1));

        return patrolLocations;
    }
}

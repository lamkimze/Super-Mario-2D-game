package game.behaviours.partollocations;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;

/**
 * Class for the LinePatrolLocations
 * @author Rory Tobin-Underwood
 * @version 1
 */
public class LinePatrolLocations extends PatrolLocations{
    /**
     * The distance up and down from the centerYCoordinate
     */
    private int patrolYDistance;

    /**
     * Constructor
     * @param centerXCoordinate the center coordinate in the x-axis
     * @param centerYCoordinate the center coordinate in the y-axis
     * @param patrolYDistance the distance of patrol up or down from the y-axis
     */
    public LinePatrolLocations(int centerXCoordinate, int centerYCoordinate, int patrolYDistance) {
        super(centerXCoordinate, centerYCoordinate);
        this.patrolYDistance = patrolYDistance;
    }

    /**
     * Generates the locations for a line patrol
     * @param map the map that will be patrolled on
     * @return ArrayList of Locations to patrol
     */
    @Override
    public ArrayList<Location> generateLocations(GameMap map) {
        ArrayList<Location> patrolLocations = new ArrayList<>();
        // Line means 2 locations
        int xCoord1 = centerXCoordinate;
        int xCoord2 = centerXCoordinate;
        int yCoord1 = centerYCoordinate + patrolYDistance;
        int yCoord2 = centerYCoordinate - patrolYDistance;

        patrolLocations.add(map.at(xCoord1,yCoord1));
        patrolLocations.add(map.at(xCoord2,yCoord2));

        return patrolLocations;
    }
}

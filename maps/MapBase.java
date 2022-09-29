package game.maps;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.BonePile;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Lava;
import game.grounds.highgrounds.Wall;
import game.grounds.highgrounds.WarpPipe;
import game.grounds.highgrounds.trees.Mature;
import game.grounds.highgrounds.trees.Sapling;
import game.grounds.highgrounds.trees.Sprout;

import java.util.List;

/**
 * a base class of game map contains all the similar attributes
 * @author Lam Kim Ze, 31860346
 * @version 2.0
 */
public abstract class MapBase extends GameMap {
    /**
     * Private attribute of FancyGroundFactory which contains all the different Grounds, currently need to add any new
     * Grounds to add them to the Map.
     */
    private static final FancyGroundFactory fancyGroundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
            new Sprout(), new Sapling(), new Mature(), new Lava(), new BonePile());

    /**
     * class constant
     */
    private String mapName;

    /**
     * constructor
     * @param lines the game map
     * @param mapName the name of the map
     */
    public MapBase(List<String> lines, String mapName){
        super(fancyGroundFactory, lines);
        this.mapName = mapName;
    }

    /**
     * abstract method to force child class to add actors
     */
    public abstract void addActors();

    /**
     * abstract method to force child class to add grounds
     */
    public abstract void addGrounds();

    /**
     * return the name of the map
     * @return the name of the map
     */
    @Override
    public String toString() {
        return this.mapName;
    }
}

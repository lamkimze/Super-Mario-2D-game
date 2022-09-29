package game.maps;

import edu.monash.fit2099.engine.positions.World;
import game.actors.player.Player;
import game.grounds.highgrounds.WarpPipe;

import java.util.ArrayList;

/**
 * the class that initialize all the maps in the world
 * @author Lam Kim Ze, 31860346
 * @version 2.0
 */
public class MapsInitialiser {
    /**
     * class constant
     */
    private World world;
    private ArrayList<MapBase> maps = new ArrayList<>();

    /**
     * constructor
     * @param world the world that contains all the maps
     */
    public MapsInitialiser(World world) {
        this.world = world;
        //Add any maps here
        StartingMap startingMap = new StartingMap();
        maps.add(startingMap);
        LavaMap lavaMap = new LavaMap();
        maps.add(lavaMap);

        // Add all maps to world and add all actors and ground onto the maps
        this.addAllMapsToWorld();
        this.addPlayerInstance();
        this.addAllActors();
        this.addAllSpecialGrounds();

        //connect warp pipes first creates Warp Pipe in Lava map then connects all the other pipes to that pipe
        WarpPipe warpPipe = lavaMap.createPipe(lavaMap.at(0, 0));
        startingMap.placeWarpPipes(lavaMap.at(0, 0), warpPipe);
    }

    /**
     * adding all the maps available to the world
     */
    private void addAllMapsToWorld() {
        for (MapBase mapBase : maps) {
            world.addGameMap(mapBase);
        }
    }

    /**
     * Adds the Player instance to the first map in the maps ArrayList
     */
    private void addPlayerInstance() {
        world.addPlayer(Player.getInstance(), maps.get(0).at(42, 10));
    }

    /**
     * adding actors into the maps that they need to be in
     */
    private void addAllActors() {
        for (MapBase mapBase : maps) {
            mapBase.addActors();
        }
    }

    /**
     * adding all special type of ground into the correct maps
     */
    private void addAllSpecialGrounds(){
        for (MapBase mapBase : maps) {
            mapBase.addGrounds();
        }
    }

}
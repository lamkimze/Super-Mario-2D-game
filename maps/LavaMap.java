package game.maps;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.allies.PrincessPeach;
import game.actors.enemies.Bowser;
import game.actors.enemies.koopa.HammerBro;
import game.behaviours.partollocations.LinePatrolLocations;
import game.grounds.BonePile;
import game.grounds.Lava;
import game.grounds.fountains.HealthFountain;
import game.grounds.highgrounds.WarpPipe;

import java.util.Arrays;
import java.util.List;

/**
 * a class that represent another maps called Lava Maps
 * @author Lam Kim Ze, 3186346
 * @version 3.0
 */
public class LavaMap extends MapBase {

    /**
     * A class constant List that store Lava Map
     */
    private static final List<String> LAVA_MAP_BASE = Arrays.asList(
            "_____..................................LLLLLLLLLLLLLLLLL",
            "_____..................................LLLLLLLLLLLLLLLLL",
            "_____..................................LLLLLLLLLLLLLL###",
            "#####................b..........b......LLLLLLLLLL#######",
            "....###....b...........................LLLLLLL##########",
            "......##.................................########.......",
            ".......##............b..................................",
            "........##..............................................",
            ".........##....................b........................",
            "..........##............................................",
            "...........####......b..................................",
            ".............###.........................########.......",
            ".....b.........##......................LLLLLLL##########",
            "................##.............b.......LLLLLLLLLL#######",
            "................##.....................LLLLLLLLLLLLLL###",
            "................##.....................LLLLLLLLLLLLLLLLL",
            ".................##....................LLLLLLLLLLLLLLLLL");

    /**
     * constructor
     */
    public LavaMap() {
        super(LAVA_MAP_BASE, "Lava Zone");
        this.spawnRandomLava();
    }

    /**
     * spawn the Lava randomly on the Lava Map (the Lava on the map will not be more than 200
     */
    public void spawnRandomLava(){
        int counter = 0;

        for (int x = widths.min(); x < widths.max()-17; x++){
            for (int y : heights) {
                Location location = this.at(x, y);
                if (location.getGround().hasCapability(Status.LAVA_REPLACABLE)){
                    int random = (int) Math.floor(Math.random() * 1000);
                    if(random < 80 && counter < 200){
                        location.setGround(new Lava());
                        counter ++;
                    }
                }
            }
        }
    }

    /**
     * create the WarpPipe on the Lava Map to enable player to teleport between Lava Map and initial Map
     * @param location the location to put the WarpPipe
     * @return warpPipe
     */
    public WarpPipe createPipe(Location location){
        WarpPipe warpPipe = new WarpPipe();
        location.setGround(warpPipe);
        return warpPipe;
    }

    /**
     * adding actors to the Lava Map
     */
    @Override
    public void addActors() {
        this.at(44,10).addActor(new HammerBro(new LinePatrolLocations(44, 8,2).generateLocations(this)));
        this.at(42,8).addActor(new HammerBro(new LinePatrolLocations(42,8,2).generateLocations(this)));
        this.at(40,6).addActor(new HammerBro(new LinePatrolLocations(40,8,2).generateLocations(this)));
        this.at(53,8).addActor(new Bowser(this.at(52,8)));
        this.at(54,8).addActor(new PrincessPeach());
    }

    /**
     * adding a healt fountain
     */
    @Override
    public void addGrounds() {
        this.at(55, 16).setGround(new HealthFountain());
    }
}

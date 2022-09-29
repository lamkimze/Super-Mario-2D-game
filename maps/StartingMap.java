package game.maps;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.allies.Toad;
import game.actors.allies.Yoshi;
import game.grounds.fountains.HealthFountain;
import game.grounds.fountains.PowerFountain;
import game.grounds.highgrounds.WarpPipe;

import java.util.Arrays;
import java.util.List;

/**
 * the class that contains the initial maps that the player is in
 * @author Lam Kim Ze, 31860346
 * @version 2.1
 */
public class StartingMap extends MapBase {
    /**
     * a class constant list that store the starting map
     */
    private static final List<String> STARTING_MAP_BASE = Arrays.asList(
            ".........................................###..........+....................#.##.",
            "............+............+...............#__#.............................#.#..#",
            ".........................................#__#..............................#####",
            ".............................................##......................+......##..",
            "...............................................#...........................#....",
            "................................................#...............................",
            ".................+................................#.............................",
            ".................................................##.............................",
            "................................................##..............................",
            ".........+..............................+#____####.................+............",
            ".......................................+#_____###++.............................",
            ".......................................+#______###..............................",
            "........................................+#_____###..............................",
            "........................+........................##.............+...............",
            "...................................................#............................",
            "........#__#........................................#...........................",
            ".......##__##......+.................................#..........................",
            ".......##___##........................................#.........................",
            "........#####..........................................##.......................");

    /**
     * constructor
     */
    public StartingMap() {
        super(STARTING_MAP_BASE, "Mushroom Kingdom");
    }

    /**
     * adding actor to the starting map
     */
    public void addActors(){
        this.at(43,10).addActor(new Toad());
        this.at(43,1).addActor(new Yoshi());
    }

    /**
     * adding different type of ground to the starting map
     */
    @Override
    public void addGrounds() {
        this.at(41,8).setGround(new PowerFountain());
        this.at(43,8).setGround(new HealthFountain());
    }

    /**
     * adding WarpPipes to the Starting Map
     * @param outLocation the location where the Player will coming out when teleporting
     * @param warpPipe the destination WarpPipe
     */
    public void placeWarpPipes(Location outLocation, WarpPipe warpPipe){
        this.at(11, 3).setGround(new WarpPipe(outLocation, warpPipe));
        this.at(76, 4).setGround(new WarpPipe(outLocation, warpPipe));
        this.at(3, 15).setGround(new WarpPipe(outLocation, warpPipe));
        this.at(65, 17).setGround(new WarpPipe(outLocation,warpPipe));
    }
}

package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.World;
import game.maps.MapsInitialiser;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	/**
	 * method that create the world and with ground, enemy and player and execute the game
	 * @param args default parameters
	 */
	public static void main(String[] args) {

			World world = new World(new Display());

//			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature());
//
//			List<String> map = Arrays.asList(
//				"..........................................##..........+.........................",
//				"............+............+..................#...................................",
//				"............................................#...................................",
//				".............................................##......................+..........",
//				"...............................................#................................",
//				"................................................#...............................",
//				".................+................................#.............................",
//				".................................................##.............................",
//				"................................................##..............................",
//				".........+..............................+#____####.................+............",
//				".......................................+#_____###++.............................",
//				".......................................+#______###..............................",
//				"........................................+#_____###..............................",
//				"........................+........................##.............+...............",
//				"...................................................#............................",
//				"....................................................#...........................",
//				"...................+.................................#..........................",
//				"......................................................#.........................",
//				".......................................................##.......................");
//
//			GameMap gameMap = new GameMap(groundFactory, map);
//			world.addGameMap(gameMap);
//
//			Actor mario = Player.getInstance();
//			world.addPlayer(mario, gameMap.at(42, 10));
//
//			//Added Toad to the map
//			gameMap.at(43,10).addActor(new Toad());
//			gameMap.at(41,10).addActor(new Yoshi());
//			gameMap.at(43,11).setGround(new HealthFountain());
//
//			gameMap.at(0, 0).setGround(new WarpPipe());
			new MapsInitialiser(world);

			world.run();

	}
}

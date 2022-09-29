package game.actors.enemies.koopa;

import game.items.consumables.SuperMushroom;

/**
 * Enemy Koopa that has a hard shell!
 * @author Clinton Nguyen, 31500145, Lam Kim Ze, 31860346
 * @version 2
 */
public class Koopa extends KoopaFleet {
    private static final int KOOPA_HIT_POINTS = 100;


    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', KOOPA_HIT_POINTS);
        this.addItemToInventory(new SuperMushroom());

    }




}

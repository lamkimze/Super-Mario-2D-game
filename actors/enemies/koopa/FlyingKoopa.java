package game.actors.enemies.koopa;

import game.Status;
import game.items.consumables.SuperMushroom;

/**
 * Flying Koopa class that indicate an enemy flying koopa
 * @author Lam Kim Ze, 31860346
 * @version 1
 */
public class FlyingKoopa extends KoopaFleet {

    /**
     * class constants
     */
    private static final int FLYING_KOOPA_HIT_POINT = 150;

    /**
     * constructor
     */
    public FlyingKoopa(){
        super("Flying Koopa", 'F', FLYING_KOOPA_HIT_POINT);
        this.addCapability(Status.FLY);
        this.addItemToInventory(new SuperMushroom());
    }

}

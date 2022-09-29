package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 * @author Clinton Nguyen, Rory-Tobin Underwood, Lam Kim Ze
 * @version 1
 */
public enum Status {
    /**
     * use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
     */
    HOSTILE_TO_ENEMY,
    /**
     * use this status to tell that current instance has "grown".
     */
    TALL,
    /**
     * use this status show that current instance is invincible
     */
    INVINCIBLE,
    /**
     * use this status to show that an actor is dormant.
     */
    DORMANT,
    /**
     * use this status to show if an actor has a shell.
     */
    SHELL,
    /**
     * use to this status to show the instance can be reset in the next tick or playTurn method
     */
    RESET,
    /**
     * use to show the player can use the reset game action
     */
    CAN_RESET,
    /**
     * use to show that players are going to player the player
     */
    AGGROED,
    /**
     * use to show the actor will listen to a speaker
     */
    LISTENER,
    /**
     * use to show the actor can buy items from a seller
     */
    BUYER,
    /**
     * use to show that the item can break a shell
     */
    SHELL_BREAKER,
    /**
     * use to show that actor can jump to high ground
     */
    JUMPER,
    /**
     * use to show the ground is jumpable
     */
    JUMPABLE,
    /**
     * use this status to show that actor cannot enter floor
     */
    FLOOR_RESTRICTED,
    /**
     * use this status to show that the Tree has been converted to dirt
     */
    CONVERTED_TO_DIRT,
    /**
     * use this status to show that an item is a fillable container
     */
    FILLABLE_CONTAINER,
    /**
     *  use this status to show that an actor is revivable
     */
    REVIVABLE,
    /**
     *  use this status to show that an actor cannot walk on lava
     */
    LAVA_RESTRICTED,
    /**
     *  use this status to show that an actor can be teleport
     */
    TELEPORTABLE,
    /**
     * apply this status when player kill Bowser and can rescue princess peach to end the game
     */
    END_KEY_HOLDER,
    /**
     * use this status to makes the item consumable permanently
     */
    PERMANENT,
    /**
     * use this status to show that the actor is guardian of the warp pipe
     */
    TELE_GUARD,
    /**
     * use this status to show that an item has be activated (allows for an item to have multiple activations)
     */
    ACTIVATED,

    /**
     * Use to show that the Trees can grow from a Ground
     */
    FERTILE,
    /**
     * use this status to show that the ground can be replaced by lava
     */
    LAVA_REPLACABLE,
    /**
     * use this status to show that the actor can attack with fire
     */
    FIRE_ATTACK,
    /**
     * use this status to show that the ally is being caught and handcuffed by the enemy
     */
    HANDCUFFED,
    /**
     * use this status to show that the actor will drop a key once it is killed
     */
    SPAWN_KEY,
    /**
     * use this status to show that the actor can fly
     */
    FLY,

    /**
     * Use to show that a Ground can be drunk from
     */
    DRINKABLE,

    /**
     * Use to show that an Actor can drink from Grounds
     */
    DRINKER,
    /**
     * use this status to show that the actor can move on every high ground
     */
    NO_RESTRICTED,

    /**
     * Used for Allies to make them follow the Player
     */
    FOLLOWER,

    /**
     * Use to show that an Actor can gift
     */
    CAN_GIFT;
}

package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Dirt;
import game.items.Coin;

import java.util.HashMap;

/**
 * an action class that allow player to move to high ground with invincible power after consuming power star
 * @author Lam Kim Ze, 31860346
 * @version 3.0
 */

public class InvincibleMoveActorAction extends MoveActorAction {

    private HashMap<String, String> hotkey = new HashMap();

    /**
     *constructor
     * @param moveToLocation the location of high ground that the player want to move to using invincible power
     * @param direction the direction of high ground that the player want to move to using invincible power
     */
    public InvincibleMoveActorAction(Location moveToLocation, String direction){
        super(moveToLocation, direction);
        // Was unsure how to get the hotkeys from the direction
        this.hotkey.put("North", "8");
        this.hotkey.put("North-East", "9");
        this.hotkey.put("East", "6");
        this.hotkey.put("South-East", "3");
        this.hotkey.put("South", "2");
        this.hotkey.put("South-West", "1");
        this.hotkey.put("West", "4");
        this.hotkey.put("North-West", "7");
    }

    /**
     * player moving to the high ground using invincible move action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String describing the action
     */
    public String execute(Actor actor, GameMap map){
        // the high ground become dirt
        moveToLocation.setGround(new Dirt());
        // the high ground drops a Coin ($5)
        moveToLocation.addItem(new Coin(5));
        // actor move to the location
        map.moveActor(actor, moveToLocation);
        return menuDescription(actor);
    }


    /**
     * Returns the hotkey for this action
     * @return String which represents the hotkey.
     */
    @Override
    public String hotkey() {
        return this.hotkey.get(this.direction);
    }
}

package game.actors.allies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Yoshi's special friend !
 * @author Clinton Nguyen
 * @version 1
 */
public class Birdo extends Ally {
    /**
     * class constants which store the hp of Birdo
     */
    public static int BIRDO_HIT_POINTS = 200;

    /**
     * Constructor.
     * (We were using special box characters but the JavaDocs would not generate with them so we had to change it)
     */
    public Birdo() {
        super("Birdo", 'I', BIRDO_HIT_POINTS);
    }

    /**
     * Birdo does nothing.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return does nothing action.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}

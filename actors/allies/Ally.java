package game.actors.allies;

import game.actors.NonPlayableActor;

/**
 * An Abstract Class which all Actors which are friendly to the Player will extend (all Allies)
 * @author Rory-Tobin Underwood
 * @version 1.0
 */
public abstract class Ally extends NonPlayableActor {

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Ally(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
}

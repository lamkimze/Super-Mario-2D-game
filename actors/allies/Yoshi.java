package game.actors.allies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.CommunicationAction;
import game.actors.actortypes.FollowingActor;
import game.actors.actortypes.SpeakableActor;
import game.actors.enemies.DryBones;
import game.actors.player.Player;
import game.items.Egg;
import game.items.consumables.PowerStar;
import game.items.consumables.ReturnScroll;
import game.items.consumables.SuperMushroom;
import game.quest.*;
import game.quest.questtypes.EliminationQuest;
import game.quest.questtypes.EscortQuest;
import game.quest.questtypes.RetrievalQuest;

/**
 * Yoshi - an anthropomorphic dinosaur from Mario!
 * @author Clinton Nguyen, Rory-Tobin Underwood
 * @version 1
 */

public class Yoshi extends Ally implements SpeakableActor, QuestGiver, FollowingActor {
    /**
     * class constants that store the HP of Yoshi
     */
    public static int YOSHI_HIT_POINTS = 150;

    /**
     * Constructor.
     */
    public Yoshi() {
        super("Yoshi", 'S', YOSHI_HIT_POINTS);
        questGivingManager.addQuest(new EliminationQuest(new SuperMushroom(), new DryBones(),this, 36, 3));
        questGivingManager.addQuest(new RetrievalQuest(new PowerStar(), new Egg(), Player.getInstance(), this, 77, 1));
        questGivingManager.addQuest(new EscortQuest(new ReturnScroll(), new Birdo(),this, this, 10, 17));
        this.registerAsFollower(super.behaviours);

    }

    /**
     * Determines what a Yoshi should do next.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return returns action that Yoshi should do.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.FOLLOWER)){
            return super.getActionFromBehaviour(map);
        }
        return new DoNothingAction();
    }


    /**
     * A method where an actor speaks to a target actor/player.
     * @param actor the target which Toad is speaking too.
     * @return a String which is the random speech option
     */
    @Override
    public String speakTo(Actor actor) {
        this.speechLines.addOption("Sorry no quests");
        String speechLine = this.speechLines.getRandomOption();
        return "Yoshi: \"" + speechLine + "\"";
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an ActionList which contains all the Actions the otherActor can do the current Actor.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actionsToYoshi = new ActionList();

        if (otherActor.hasCapability(Status.LISTENER)) {
            actionsToYoshi = questGivingManager.questActionLogic(map);
            if (actionsToYoshi == null){
                actionsToYoshi = new ActionList();
                actionsToYoshi.add(new CommunicationAction(this));
            }
        }
        return actionsToYoshi;
    }

}

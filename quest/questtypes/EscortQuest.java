package game.quest.questtypes;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.actortypes.WanderableActor;
import game.actors.allies.Ally;
import game.actors.allies.Birdo;
import game.maps.LavaMap;
import game.quest.QuestGiver;
import game.quest.QuestState;

/**
 * Escort type of quest - bring the escort target to a certain ally and get rewarded!
 * @author Clinton Nguyen
 * @version 1
 */
public class EscortQuest extends Quest{
    private Ally target;
    private Ally escortTarget;

    /**
     * Getter method for target.
     * @return returns target.
     */
    public Ally getTarget(){
        return target;
    }

    /**
     * Constructor.
     * @param reward The reward to be given for completing a quest.
     * @param target The target to be delivered to.
     * @param questGiver The questGiver.
     * @param escortTarget the target being escorted.
     * @param xCoordinate The x Coordinate of where target will be added.
     * @param yCoordinate The y Coordinate of where targer will be added.
     */
    public EscortQuest(Item reward, Ally target, QuestGiver questGiver,  Ally escortTarget, Integer xCoordinate, Integer yCoordinate) {
        super(reward, questGiver, xCoordinate, yCoordinate);
        this.target = target;
        this.escortTarget = escortTarget;
    }

    /**
     * Method to check if quest is finished.
     * @param map map of the game.
     */
    @Override
    public void checkIfFinished(GameMap map) {
        if (map.contains(target) && super.questState == QuestState.ACCEPTED){
            for (Exit exit: map.locationOf(target).getExits()){
                Location destination = exit.getDestination();
                if (destination == map.locationOf(escortTarget)){
                    super.questState = QuestState.FINISHED;
                }
            }
        }
    }

    /**
     * Method to begin the quest when it is accepted. (Adds target to the map)
     * @param map map of the game.
     */
    @Override
    public void beginQuest(GameMap map) {
        escortTarget.addCapability(Status.FOLLOWER);
        map.at(xCoordinate,yCoordinate).addActor(target);

    }

    /**
     * Finish quest method. Makes escort target stop following you.
     */
    @Override
    public void finishQuest() {
        super.finishQuest();
        escortTarget.removeCapability(Status.FOLLOWER);
    }

    /**
     * String method.
     * @return returns the quest description to be printed in the menu.
     */
    @Override
    public String getQuestDescription() {
        return "Please bring me to my 'special' friend Birdo.";
    }
}

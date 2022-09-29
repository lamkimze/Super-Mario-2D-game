package game.quest.questtypes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.quest.QuestGiver;
import game.quest.QuestState;


/**
 * Retrieval type of quest - bring something to the questGiver and he will reward you!
 * @author Clinton Nguyen
 * @version 1
 */
public class RetrievalQuest extends Quest{

    private Item target;
    private Actor carrier;

    /**
     * Constructor.
     * @param reward The reward to be given for completing a quest.
     * @param target The item to picked up.
     * @param carrier the actor carrying the item.
     * @param questGiver The questGiver.
     * @param xCoordinate The x Coordinate of where enemy will be added.
     * @param yCoordinate The y Coordinate of where enemy will be added.
     */
    public RetrievalQuest(Item reward, Item target, Actor carrier, QuestGiver questGiver, Integer xCoordinate, Integer yCoordinate) {
        super(reward, questGiver, xCoordinate, yCoordinate);
        this.target = target;
        this.carrier = carrier;
    }
    /**
     * Method to check if quest is finished.
     * @param map map of the game.
     */
    @Override
    public void checkIfFinished(GameMap map) {
        if (carrier.getInventory().contains(target) && super.questState == QuestState.ACCEPTED){
            super.questState = QuestState.FINISHED;
        }
    }

    /**
     * Method to begin the quest when it is accepted. (Adds item to the map)
     * @param map map of the game.
     */
    @Override
    public void beginQuest(GameMap map) {
        map.at(xCoordinate,yCoordinate).addItem(target);
    }

    /**
     * Finish quest method. Removes item from inventory once handed in.
     */
    @Override
    public void finishQuest(){
        super.finishQuest();
        carrier.removeItemFromInventory(target);
    }

    /**
     * String method.
     * @return returns the quest description to be printed in the menu.
     */
    @Override
    public String getQuestDescription() {
        return "Please bring my precious " + target + " back to me";
    }
}

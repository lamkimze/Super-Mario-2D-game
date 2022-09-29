package game.quest.questtypes;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.quest.QuestGiver;
import game.quest.QuestState;


/**
 * Abstract Quest class containing useful quest methods.
 * @author Clinton Nguyen
 * @version 1
 */
public abstract class Quest {
    /**
     * the item giving to the player as rewards
     */
    protected Item reward;
    /**
     * the availability of quest
     */
    protected QuestState questState = QuestState.AVAILABLE;
    /**
     * the questGiver who gives the quest.
     */
    protected QuestGiver questGiver;
    /**
     * the x Coordinate of a specific thing.
     */
    protected Integer xCoordinate;
    /**
     * the y Coordinate of a specific thing.
     */
    protected Integer yCoordinate;

    /**
     * Constructor.
     * @param reward reward to be given.
     * @param questGiver the questGiver who gives the quest.
     * @param xCoordinate the x Coordinate of a specific thing.
     * @param yCoordinate the y Coordinate of a specific thing.
     */
    public Quest(Item reward, QuestGiver questGiver, Integer xCoordinate, Integer yCoordinate){
        this.reward = reward;
        this.questGiver = questGiver;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Checks if quest is finished.
     * @param map map of the game.
     */
    public abstract void checkIfFinished(GameMap map);

    /**
     * Begins the quest.
     * @param map map of the game.
     */
    public abstract void beginQuest(GameMap map);

    /**
     * Finishes the quest by setting state to handed in.
     */
    public void finishQuest(){
        this.setQuestState(QuestState.HANDED_IN);
    }

    /**
     * Getter method for the quest state.
     * @return returns state of the quest.
     */
    public QuestState getQuestState(){
        return questState;
    }

    /**
     * Getting method for the quest giver.
     * @return returns the quest giver.
     */
    public QuestGiver getQuestGiver(){
        return questGiver;
    }

    /**
     * Quest description method.
     * @return String of what quest description should be displayed.
     */
    public abstract String getQuestDescription();

    /**
     * Getter for reward.
     * @return reward.
     */
    public Item getReward(){
        return reward;
    }

    /**
     * Setter for quest state.
     * @param questState state of the quest.
     */
    public void setQuestState(QuestState questState){
        this.questState = questState;
    }

    /**
     * To string method of the quest state essentially (if it is completed or not).
     * @return the completeness of the quest
     */
    @Override
    public String toString() {
        if (questState == QuestState.ACCEPTED){
            return "Incomplete : " + getQuestDescription();
        }
        return "Complete : " + getQuestDescription();
    }
}

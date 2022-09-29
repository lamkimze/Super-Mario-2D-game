package game.quest.questtypes;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.Enemy;
import game.quest.QuestGiver;
import game.quest.QuestState;


/**
 * Elimination type of quest - kill an enemy and get rewarded!
 * @author Clinton Nguyen
 * @version 1
 */
public class EliminationQuest extends Quest {

    private Enemy target;

    /**
     * Getter method for a target.
     * @return returns target.
     */
    public Enemy getTarget() {
        return target;
    }

    /**
     * Constructor.
     * @param reward The reward to be given for completing a quest.
     * @param target The target to be killed.
     * @param questGiver The questGiver.
     * @param xCoordinate The x Coordinate of where enemy will be added.
     * @param yCoordinate The y Coordinate of where enemy will be added.
     */
    public EliminationQuest(Item reward, Enemy target, QuestGiver questGiver, Integer xCoordinate, Integer yCoordinate) {
        super(reward, questGiver, xCoordinate, yCoordinate);
        this.target = target;
    }

    /**
     * Method to check if quest is finished.
     * @param map map of the game.
     */
    @Override
    public void checkIfFinished(GameMap map) {
        if (!map.contains(target) && super.questState == QuestState.ACCEPTED){
            super.questState = QuestState.FINISHED;
        }
    }

    /**
     * Method to begin the quest when it is accepted. (Adds Enemy target to the map)
     * @param map map of the game.
     */
    @Override
    public void beginQuest(GameMap map) {
        map.at(xCoordinate, yCoordinate).addActor(this.target);
    }

    /**
     * String method.
     * @return returns the quest description to be printed in the menu.
     */
    @Override
    public String getQuestDescription() {
        return "Please kill this " + target;
    }

}

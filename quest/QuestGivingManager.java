package game.quest;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.GameMap;
import game.quest.questactions.GiveQuestAction;
import game.quest.questactions.HandInQuestAction;
import game.quest.questtypes.Quest;

import java.util.ArrayList;

/**
 * QuestGivingManager class - used to manage quests that a questGiver has.
 * @author Clinton Nguyen
 * @version 1
 */
public class QuestGivingManager {
    private ArrayList<Quest> questArrayList;
    private Quest currentQuest = null;

    /**
     * constructor
     */
    public QuestGivingManager(){
        this.questArrayList = new ArrayList<>();
    }

    /**
     * Add quest method.
     * @param quest a quest.
     */
    public void addQuest(Quest quest){
        this.questArrayList.add(quest);
    }

    /**
     * Method to regulate the quest logic (whether it exists, if its done, etc.)
     * @param map map of the game.
     * @return the actionList with what needs to be done in relation to quests.
     */
    public ActionList questActionLogic(GameMap map){
        ActionList actionList = new ActionList();

        if (this.currentQuest == null){
            Quest quest = this.getNextQuest();
            if (quest == null){
                return null;
            }
            else{
                actionList.add(new GiveQuestAction(quest, this));
            }
        }
        else{
            currentQuest.checkIfFinished(map);
            if (this.currentQuest.getQuestState() == QuestState.FINISHED) {
                actionList.add(new HandInQuestAction(this.currentQuest, this));
            }
        }
        return actionList;
    }


    /**
     * Gets the next quest.
     * @return returns the next quest if there is an available quest.
     */
    public Quest getNextQuest(){
        for(Quest quest: questArrayList){
            if (quest.getQuestState() == QuestState.AVAILABLE){
                return quest;
            }
        }
        return null;
    }

    /**
     * Setter method for current quest.
     * @param quest a quest.
     */
    public void setCurrentQuest(Quest quest){
        currentQuest = quest;
    }
}

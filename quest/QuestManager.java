package game.quest;

import edu.monash.fit2099.engine.positions.GameMap;
import game.quest.questtypes.Quest;

import java.util.ArrayList;

/**
 * QuestManager class used to check the quests that the Quester has.
 * @author Clinton Nguyen
 * @version 1
 */
public class QuestManager {
    private ArrayList<Quest> questArrayList;

    /**
     * constructor
     */
    public QuestManager(){
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
     * Display method of quests to console.
     * @param map a map.
     */
    public void displayQuestsToConsole(GameMap map){
        System.out.println("Current Accepted and Finished Quests");
        this.checkAllQuest(map);
        this.displayAllQuests();
    }

    /**
     * Checks all the quests to be finished.
     * @param map a map.
     */
    public void checkAllQuest(GameMap map){
        for(Quest quest: questArrayList){
            quest.checkIfFinished(map);
        }
    }

    /**
     * Displays method of all quests.
     */
    public void displayAllQuests(){
        for(Quest quest: questArrayList){
            if (quest.getQuestState() == QuestState.ACCEPTED || quest.getQuestState() == QuestState.FINISHED){
                System.out.println(quest);
            }
        }
    }
}

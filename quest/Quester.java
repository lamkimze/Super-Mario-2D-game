package game.quest;

import game.quest.QuestManager;
import game.quest.questtypes.Quest;

/**
 * Interface class Quester.
 * @author Clinton Nguyen
 * @version 1
 */
public interface Quester {
    /**
     * New instance of QuestManager is made here.
     */
    QuestManager questManager = new QuestManager();

    /**
     * Adding quest method for the quester.
     * @param quest a quest.
     */
    default void addQuest(Quest quest){
        questManager.addQuest(quest);
    }
}

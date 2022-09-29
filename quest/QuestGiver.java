package game.quest;

import game.quest.questtypes.Quest;

/**
 * Interface class QuestGiver.
 * @author Clinton Nguyen
 * @version 1
 */
public interface QuestGiver {

    /**
     * New instance of QuestGivingManager is made here.
     */
    QuestGivingManager questGivingManager = new QuestGivingManager();

    /**
     * Add quest method.
     * @param quest a quest.
     */
    default void addQuest(Quest quest){
        questGivingManager.addQuest(quest);
    }
}

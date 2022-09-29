package game.quest.questactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.quest.QuestGivingManager;
import game.quest.questtypes.Quest;

/**
 * Class to hand in quest.
 * @author Clinton Nguyen
 * @version 1
 */
public class HandInQuestAction extends Action {

    private Quest quest;
    private QuestGivingManager questGivingManager;

    /**
     * Constructor.
     * @param quest a quest.
     * @param questGivingManager Manager to hand the quest in.
     */
    public HandInQuestAction(Quest quest, QuestGivingManager questGivingManager){
        this.quest = quest;
        this.questGivingManager = questGivingManager;
    }

    /**
     * Execute method to hand in the quest.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns a string of when the quest is handed in.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(quest.getReward());
        quest.finishQuest();
        this.questGivingManager.setCurrentQuest(null);
        return "Thanks " + actor + ", here is your reward " + this.quest.getReward();

    }

    /**
     * Menu description.
     * @param actor The actor performing the action.
     * @return returns what is to be printed on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Hand in quest to " + quest.getQuestGiver();
    }
}

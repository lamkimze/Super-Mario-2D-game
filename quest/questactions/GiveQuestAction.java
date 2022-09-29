package game.quest.questactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.quest.QuestGivingManager;
import game.quest.QuestState;
import game.quest.Quester;
import game.quest.questtypes.Quest;

/**
 * Class to give a quest.
 *  @author Clinton Nguyen
 *  @version 1
 */
public class GiveQuestAction extends Action {

    private Quest quest;
    private QuestGivingManager questGivingManager;

    /**
     * Constructor.
     * @param quest a quest.
     * @param questGivingManager gives the quest out.
     */
    public GiveQuestAction(Quest quest, QuestGivingManager questGivingManager){
        this.quest = quest;
        this.questGivingManager = questGivingManager;
    }

    /**
     * execute method to give out quest.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns the quest description as a string if the actor is considered a quester. Otherwise returns nothing.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Quester){
            ((Quester) actor).addQuest(this.quest);
            this.quest.beginQuest(map);
            this.quest.setQuestState(QuestState.ACCEPTED);
            this.questGivingManager.setCurrentQuest(quest);
            return quest.getQuestDescription();
        }
        return null;
    }

    /**
     * Menu-description method.
     * @param actor The actor performing the action.
     * @return returns the string to be printed to menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Talk to " + quest.getQuestGiver() + " for an exciting quest";
    }
}

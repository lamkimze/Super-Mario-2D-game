package game.quest;

/**
 * Status class for quests.
 * @author Clinton Nguyen
 * @version 1
 */
public enum QuestState {
    /**
     * Quest that has not been accepted yet
     */
    AVAILABLE,
    /**
     * Quest that has been accepted
     */
    ACCEPTED,
    /**
     * Quest that has been finished
     */
    FINISHED,
    /**
     * Quest that has been handed in
     */
    HANDED_IN
}

package repairjava.entity.cfs.guider.action;

public class RefactorAction {
    public enum ActionType{
        INSERT, MOVE
    }
    ActionType type;

    public RefactorAction(ActionType type){
        this.type = type;
    }

}

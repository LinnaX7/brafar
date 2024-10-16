package repairjava.utils;

import java.util.ArrayList;

public class ControlStructureResult {
    private String id;
    private String type;
    private ArrayList<ControlStructureResult> children;
    private boolean controlStructure;
    private boolean insert;
    private boolean move;
    private String matchId;

    public ControlStructureResult(String id, String type, boolean controlStructure, boolean insert, boolean move, String matchId){
        this.id = id;
        this.type = type;
        this.controlStructure = controlStructure;
        this.insert = insert;
        this.move = move;
        this.matchId = matchId;
        this.children = new ArrayList<>();
    }

    public void addChild(ControlStructureResult child){
        children.add(child);
    }

    public String getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public ArrayList<ControlStructureResult> getChildren(){
        return children;
    }

    public boolean isControlStructure(){
        return controlStructure;
    }

    public boolean isInsert(){
        return insert;
    }

    public boolean isMove(){
        return move;
    }

    public String getMatchId(){
        return matchId;
    }
}

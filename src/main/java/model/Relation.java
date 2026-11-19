package model;

import enums.RelationTypeEnum;

public class Relation {
    private int id;
    private RelationTypeEnum type;

    //TODO подумать над названиями полей

    private int mainId;
    private int subordinateId;

    public Relation(RelationTypeEnum type, int mainId, int subordinateId) {
        this.type = type;
        this.mainId = mainId;
        this.subordinateId = subordinateId;
    }

    public Relation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public RelationTypeEnum getType() {
        return type;
    }

    public int getMainId() {
        return mainId;
    }

    public int getSubordinateId() {
        return subordinateId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public void setSubordinateId(int subordinateId) {
        this.subordinateId = subordinateId;
    }
}

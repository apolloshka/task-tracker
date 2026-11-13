package model;

import enums.RelationType;

import java.util.List;

public class Relation {
    private RelationType type;

    //TODO подумать над названиями полей

    private int mainId;
    private int subordinateId;

    public Relation(RelationType type, int mainId, int subordinateId) {
        this.type = type;
        this.mainId = mainId;
        this.subordinateId = subordinateId;
    }

    public RelationType getType() {
        return type;
    }

    public int getMainId() {
        return mainId;
    }

    public int getSubordinateId() {
        return subordinateId;
    }

    //TODO можем ли мы менять связь? от этого зависит нужны ли setterы
}

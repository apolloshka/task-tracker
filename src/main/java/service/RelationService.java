package service;

import enums.RelationType;
import model.Relation;

import java.util.ArrayList;
import java.util.List;

public class RelationService {

    public final List<Relation> relations;

    public RelationService() {
        relations = new ArrayList<>();
    }

    public List<RelationType> getRelationType() {
        List<RelationType> types = new ArrayList<>();

        for (RelationType type : RelationType.values()) {
            types.add(type);
        }
        return types;
    }

    public void addRelation(Relation newRelation) {
        relations.add(newRelation);
    }
}

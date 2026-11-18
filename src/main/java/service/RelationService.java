package service;

import enums.RelationTypeEnum;
import model.Relation;
import storage.JsonRelationStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RelationService {

    private int id = 1;

    public final List<Relation> relations;

    private JsonRelationStorage jsonRelationStorage;

    public RelationService(JsonRelationStorage jsonRelationStorage) {
        relations = new ArrayList<>();
        this.jsonRelationStorage = jsonRelationStorage;
    }

    public List<RelationTypeEnum> getRelationType() {
        List<RelationTypeEnum> types = new ArrayList<>();

        for (RelationTypeEnum type : RelationTypeEnum.values()) {
            types.add(type);
        }
        return types;
    }

    public List<Relation> getRelations() {
        return new ArrayList<>(relations);
    }


    public void addRelation(Relation newRelation) {

        newRelation.setId(id);
        id++;
        relations.add(newRelation);
    }

    public void saveRelationToJson(List<Relation> relations) throws IOException {
        jsonRelationStorage.saveRelations(relations);
    }

    public void getRelationFromJson(List<Relation> loadFeatures) throws IOException {
        id = 1;

        for (Relation relation : loadFeatures) {
            relations.add(relation);

            if (relation.getId() >= id) {
                id = relation.getId() + 1;
            }
        }
    }
}

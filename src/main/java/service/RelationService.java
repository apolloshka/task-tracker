package service;

import enums.ModelEnum;
import enums.RelationTypeEnum;
import exception.InvalidElementException;
import exception.RelationNotFoundException;
import model.*;
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

    public void deleteRelation(int id) {
        Relation relation = findRelationById(id);
        relations.remove(relation);
    }

    public Relation findRelationById(int id) {
        for (Relation relation : relations) {
            if (relation.getId() == id) {
                return relation;
            }
        }
        throw new RelationNotFoundException(id);
    }

    public void updateRelation(
            int id,
            int mainId,
            int subordinateId) {
        Relation relation = findRelationById(id);
        if (mainId == 0 && subordinateId == 0) {
            throw new InvalidElementException("Не указаны данные для изменения");
        }
        if (mainId != 0) {
            relation.setMainId(mainId);
        }
        if (subordinateId != 0) {
            relation.setSubordinateId(subordinateId);
        }
    }


    public List<Relation> findFeatureRelations(Feature feature) {

        List<Relation> foundRelations = new ArrayList<>();

        for (Relation relation : relations) {

            if (relation.getType() == RelationTypeEnum.FEATURE_STORY
                    && relation.getMainId() == feature.getId()) {

                foundRelations.add(relation);
            }
        }
        return foundRelations;
    }

    public List<Relation> findStoryRelations(Story story) {

        List<Relation> foundRelations = new ArrayList<>();

        for (Relation relation : relations) {

            if (relation.getType() == RelationTypeEnum.FEATURE_STORY && relation.getSubordinateId() == story.getId()) {

                foundRelations.add(relation);
            }
        }
        return foundRelations;
    }

    public List<Relation> findTaskRelations(Task task) {

        List<Relation> foundRelations = new ArrayList<>();

        for (Relation relation : relations) {

//            if (relation.getType() == RelationTypeEnum.RELATED_TO && relation.getMainId() == task.getId()) {
//
//                foundRelations.add(relation);
//            }

            if (relation.getType() == RelationTypeEnum.BLOCKS && relation.getMainId() == task.getId() || relation.getSubordinateId() == task.getId()) {

                foundRelations.add(relation);
            }

//            if (relation.getType() == RelationTypeEnum.PARENT_TASK && relation.getMainId() == task.getId()) {
//
//                foundRelations.add(relation);
//            }
        }
        return foundRelations;
    }


    public void restoreFeatureStoryRelations(FeatureService featureService, StoryService storyService) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.FEATURE_STORY) {
                Feature feature = featureService.findFeatureById(relation.getMainId());
                Story story = storyService.findStoryById(relation.getSubordinateId());
                feature.addStoryToRelation(story);
                story.addFeatureToRelation(feature);

            }
        }
    }

    public void restoreStoryTaskRelations(StoryService storyService, TaskService taskService) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.STORY_TASK) {
                Story story = storyService.findStoryById(relation.getMainId());
                Task task = taskService.findTaskById(relation.getSubordinateId());
                story.addTaskToRelation(task);
                task.addStoryToRelation(story);
            }
        }
    }

    public void restoreStoryDefectRelations(StoryService storyService, DefectService defectService) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.STORY_DEFECT) {
                Story story = storyService.findStoryById(relation.getMainId());
                Defect defect = defectService.findDefectById(relation.getSubordinateId());
                story.addDefectToRelation(defect);
                defect.addStoryToRelation(story);
            }
        }
    }

    public void restoreParentTaskRelations(TaskService taskServiceMain, TaskService taskServiceSub) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.PARENT_TASK) {
                Task taskMain = taskServiceMain.findTaskById(relation.getMainId());
                Task taskSub = taskServiceSub.findTaskById(relation.getSubordinateId());
                taskMain.addTaskToRelation(taskSub);
                taskSub.addTaskToRelation(taskMain);
            }
        }
    }

    public void restoreBlockTaskRelations(TaskService taskServiceMain, TaskService taskServiceSub) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.BLOCKS) {
                Task taskMain = taskServiceMain.findTaskById(relation.getMainId());
                Task taskSub = taskServiceSub.findTaskById(relation.getSubordinateId());
                taskMain.addTaskToRelation(taskSub);
                taskSub.addTaskToRelation(taskMain);
            }
        }
    }

    public void restoreRelatedTaskRelations(TaskService taskServiceMain, TaskService taskServiceSub) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.RELATED_TO) {
                Task taskMain = taskServiceMain.findTaskById(relation.getMainId());
                Task taskSub = taskServiceSub.findTaskById(relation.getSubordinateId());
                taskMain.addTaskToRelation(taskSub);
                taskSub.addTaskToRelation(taskMain);
            }
        }
    }

    public void restoreRelatedTaskRelations(TaskService taskService, DefectService defectService) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.DEFECT_FOUND) {
                Task task = taskService.findTaskById(relation.getMainId());
                Defect defect = defectService.findDefectById(relation.getSubordinateId());
                task.addDefectToRelation(defect);
                defect.addTaskToRelation(task);
            }
        }
    }

    public void restoreFixesTaskRelations(TaskService taskService, DefectService defectService) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.FIXES_DEFECT) {
                Task task = taskService.findTaskById(relation.getMainId());
                Defect defect = defectService.findDefectById(relation.getSubordinateId());
                task.addDefectToRelation(defect);
                defect.addTaskToRelation(task);
            }
        }
    }

    public void saveRelationToJson(List<Relation> relations) throws IOException {
        jsonRelationStorage.saveRelations(relations);
    }

    public void getRelationFromJson(List<Relation> loadRelations) throws IOException {
        id = 1;
        for (Relation relation : loadRelations) {
            relations.add(relation);
            if (relation.getId() >= id) {
                id = relation.getId() + 1;
            }
        }
    }
}

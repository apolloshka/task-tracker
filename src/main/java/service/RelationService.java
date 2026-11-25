package service;

import enums.RelationTypeEnum;
import exception.InvalidElementException;
import exception.RelationNotFoundException;
import model.*;
import storage.JsonRelationStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RelationService {
    private int id = 1;
    public final List<Relation> relations;
    private final JsonRelationStorage jsonRelationStorage;

    public RelationService(JsonRelationStorage jsonRelationStorage) {
        relations = new ArrayList<>();
        this.jsonRelationStorage = jsonRelationStorage;
    }

    public List<RelationTypeEnum> getRelationType() {
        return Arrays.asList(RelationTypeEnum.values());
    }

    public List<Relation> getRelations() {
        return new ArrayList<>(relations);
    }

    public void addRelation(Relation newRelation) {
        for (Relation relation : relations) {
            if (relation.getType() == newRelation.getType()
                    && relation.getMainId() == newRelation.getMainId()
                    && relation.getSubordinateId() == newRelation.getSubordinateId()) {
                throw new InvalidElementException("Связь уже существует");
            }
        }
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

    //TODO объединить методы поиска связей
    public List<Relation> findFeatureRelations(Feature feature) {
        List<Relation> foundRelations = new ArrayList<>();
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.FEATURE_STORY && relation.getMainId() == feature.getId()) {
                foundRelations.add(relation);
            }
        }
        return foundRelations;
    }

    public List<Relation> findStoryRelations(Story story) {
        List<Relation> foundRelations = new ArrayList<>();
        for (Relation relation : relations) {
            if ((relation.getType() == RelationTypeEnum.FEATURE_STORY
                    && relation.getSubordinateId() == story.getId())
                    || ((relation.getType() == RelationTypeEnum.STORY_TASK
                    || relation.getType() == RelationTypeEnum.STORY_DEFECT)
                    && relation.getMainId() == story.getId())) {
                foundRelations.add(relation);
            }
        }
        return foundRelations;
    }

    public List<Relation> findTaskRelations(Task task) {
        List<Relation> foundRelations = new ArrayList<>();
        for (Relation relation : relations) {
            if ((relation.getType() == RelationTypeEnum.STORY_TASK
                    && relation.getSubordinateId() == task.getId())
                    || ((relation.getType() == RelationTypeEnum.PARENT_TASK
                    || relation.getType() == RelationTypeEnum.BLOCKS
                    || relation.getType() == RelationTypeEnum.RELATED_TO)
                    && (relation.getMainId() == task.getId()
                    || relation.getSubordinateId() == task.getId()))
                    || ((relation.getType() == RelationTypeEnum.DEFECT_FOUND
                    || relation.getType() == RelationTypeEnum.FIXES_DEFECT)
                    && relation.getMainId() == task.getId())) {
                foundRelations.add(relation);
            }
        }
        return foundRelations;
    }

    public List<Relation> findDefectRelations(Defect defect) {
        List<Relation> foundRelations = new ArrayList<>();
        for (Relation relation : relations) {
            if ((relation.getType() == RelationTypeEnum.STORY_DEFECT
                    || relation.getType() == RelationTypeEnum.DEFECT_FOUND
                    || relation.getType() == RelationTypeEnum.FIXES_DEFECT)
                    && relation.getSubordinateId() == defect.getId()) {
                foundRelations.add(relation);
            }
        }
        return foundRelations;
    }

    //TODO объединить методы рестора связей
    public void restoreFeatureStoryRelations(UniversalModelService<Feature> featureService, UniversalModelService<Story> storyService) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.FEATURE_STORY) {
                Feature feature = featureService.findById(relation.getMainId());
                Story story = storyService.findById(relation.getSubordinateId());
                feature.addRelatedElement(feature.getRelatedStories(), story);
                story.addRelatedElement(story.getRelatedFeatures(), feature);
            }
        }
    }

    public void restoreStoryTaskRelations(UniversalModelService<Story> storyService, UniversalModelService<Task> taskService) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.STORY_TASK) {
                Story story = storyService.findById(relation.getMainId());
                Task task = taskService.findById(relation.getSubordinateId());
                story.addRelatedElement(story.getRelatedTasks(), task);
                task.addRelatedElement(task.getRelatedStories(), story);
            }
        }
    }

    public void restoreStoryDefectRelations(UniversalModelService<Story> storyService, UniversalModelService<Defect> defectService) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.STORY_DEFECT) {
                Story story = storyService.findById(relation.getMainId());
                Defect defect = defectService.findById(relation.getSubordinateId());
                story.addRelatedElement(story.getRelatedDefects(), defect);
                defect.addRelatedElement(defect.getRelatedStories(), story);
            }
        }
    }

    public void restoreParentTaskRelations(UniversalModelService<Task> taskServiceMain, UniversalModelService<Task> taskServiceSub) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.PARENT_TASK) {
                Task taskMain = taskServiceMain.findById(relation.getMainId());
                Task taskSub = taskServiceSub.findById(relation.getSubordinateId());
                taskMain.addRelatedElement(taskMain.getRelatedTasks(), taskSub);
                taskSub.addRelatedElement(taskSub.getRelatedTasks(), taskMain);
            }
        }
    }

    public void restoreBlockTaskRelations(UniversalModelService<Task> taskServiceMain, UniversalModelService<Task> taskServiceSub) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.BLOCKS) {
                Task taskMain = taskServiceMain.findById(relation.getMainId());
                Task taskSub = taskServiceSub.findById(relation.getSubordinateId());
                taskMain.addRelatedElement(taskMain.getRelatedTasks(), taskSub);
                taskSub.addRelatedElement(taskSub.getRelatedTasks(), taskMain);
            }
        }
    }

    public void restoreRelatedToTaskRelations(UniversalModelService<Task> taskServiceMain, UniversalModelService<Task> taskServiceSub) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.RELATED_TO) {
                Task taskMain = taskServiceMain.findById(relation.getMainId());
                Task taskSub = taskServiceSub.findById(relation.getSubordinateId());
                taskMain.addRelatedElement(taskMain.getRelatedTasks(), taskSub);
                taskSub.addRelatedElement(taskSub.getRelatedTasks(), taskMain);
            }
        }
    }

    public void restoreRelatedTaskRelations(UniversalModelService<Task> taskService, UniversalModelService<Defect> defectService) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.DEFECT_FOUND) {
                Task task = taskService.findById(relation.getMainId());
                Defect defect = defectService.findById(relation.getSubordinateId());
                task.addRelatedElement(task.getRelatedDefects(), defect);
                defect.addRelatedElement(defect.getRelatedTasks(), task);
            }
        }
    }

    public void restoreFixesTaskRelations(UniversalModelService<Task> taskService, UniversalModelService<Defect> defectService) {
        for (Relation relation : relations) {
            if (relation.getType() == RelationTypeEnum.FIXES_DEFECT) {
                Task task = taskService.findById(relation.getMainId());
                Defect defect = defectService.findById(relation.getSubordinateId());
                task.addRelatedElement(task.getRelatedDefects(), defect);
                defect.addRelatedElement(defect.getRelatedTasks(), task);
            }
        }
    }

    public void saveRelationToJson(List<Relation> relations) throws IOException {
        jsonRelationStorage.saveRelations(relations);
    }

    public void load(List<Relation> loadRelations) {
        id = 1;
        for (Relation relation : loadRelations) {
            relations.add(relation);
            if (relation.getId() >= id) {
                id = relation.getId() + 1;
            }
        }
    }
}

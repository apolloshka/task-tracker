package service;

import enums.RelationTypeEnum;
import model.*;

import java.util.List;

import static constants.ElementName.*;
import static constants.UpdateElement.*;

public class PrintService {
    private final RelationService relationService;

    public PrintService(RelationService relationService) {
        this.relationService = relationService;
    }

    //TODO как объединить меню? Падежи разные. Отдельный файл?
    public void printMenu() {
        System.out.println("0 - Выход из приложения");
        System.out.println("1 - Работа с задачами");
        System.out.println("2 - Работа с историями");
        System.out.println("3 - Работа с дефектами");
        System.out.println("4 - Работа с фичами");
        System.out.println("5 - Работа со связями");
        System.out.print("Введите команду: ");
    }

    public void printTaskMenu() {
        System.out.println("0 - Вернуться к главному меню");
        System.out.println("1 - Создать задачу");
        System.out.println("2 - Показать все задачи");
        System.out.println("3 - Удалить задачу");
        System.out.println("4 - Изменить задачу");
        System.out.println("5 - Найти задачу по Id");
        System.out.println("6 - Найти задачу по названию");
        System.out.print("Введите команду: ");
    }

    public void printStoryMenu() {
        System.out.println("0 - Вернуться к главному меню");
        System.out.println("1 - Создать историю");
        System.out.println("2 - Показать все истории");
        System.out.println("3 - Удалить историю");
        System.out.println("4 - Изменить историю");
        System.out.println("5 - Найти историю по Id");
        System.out.println("6 - Найти историю по названию");
        System.out.print("Введите команду: ");
    }

    public void printDefectMenu() {
        System.out.println("0 - Вернуться к главному меню");
        System.out.println("1 - Создать дефект");
        System.out.println("2 - Показать все дефекты");
        System.out.println("3 - Удалить дефект");
        System.out.println("4 - Изменить дефект");
        System.out.println("5 - Найти дефект по Id");
        System.out.println("6 - Найти дефект по названию");
        System.out.print("Введите команду: ");
    }

    public void printFeatureMenu() {
        System.out.println("0 - Вернуться к главному меню");
        System.out.println("1 - Создать фичу");
        System.out.println("2 - Показать все фичи");
        System.out.println("3 - Удалить фичу");
        System.out.println("4 - Изменить фичу");
        System.out.println("5 - Найти фичу по Id");
        System.out.println("6 - Найти фичу по названию");
        System.out.print("Введите команду: ");
    }

    public void printRelationMenu() {
        System.out.println("0 - Вернуться к главному меню");
        System.out.println("1 - Создать связь");
        System.out.println("2 - Показать все связи");
        System.out.println("3 - Удалить связь");
        System.out.println("4 - Изменить связь");
        System.out.print("Введите команду: ");
    }

    public void printEnterTitle() {
        System.out.println("Если хотите отменить создание элемента введите /cancel: ");
        System.out.print("Введите название: ");
    }

    public void printEnterDescription() {
        System.out.println("Если хотите отменить создание элемента введите /cancel: ");
        System.out.print("Введите описание: ");
    }

    public void printUpdateMenu() {
        System.out.println("Что изменить?");
        System.out.println(TITLE + " - название");
        System.out.println(DESCRIPTION + " - описание");
    }

    public void printUpdateRelationMenu() {
        System.out.println("Что изменить?");
        System.out.println(MAIN + " - id главного элемента");
        System.out.println(SUB + " - id зависимого элемента");
    }

    public void printRelatedElement(Element element, String type, Relation relation) {
        System.out.println(
                "Id " + type + ": " + element.getId()
                        + " Название: " + element.getTitle()
                        + " Тип связи: " + relation.getType());
    }

    public void printFeature(Feature feature, List<Relation> relations) {
        System.out.println("Id фичи: " + feature.getId()
                + " Название: " + feature.getTitle());
        printRelationType(STORY);
        for (Relation relation : relations) {
            for (Story story : feature.getRelatedStories()) {
                if (story.getId() == relation.getSubordinateId()) {
                    printRelatedElement(story, STORY, relation);
                }
            }
        }
    }

    public void printTask(Task task, List<Relation> relations) {
        System.out.println("Id задачи: " + task.getId()
                + " Название: " + task.getTitle());
        printRelationType(TASK);
        for (Relation relation : relations) {
            for (Task relatedTask : task.getRelatedTasks()) {
                if ((relation.getType() == RelationTypeEnum.PARENT_TASK
                        || relation.getType() == RelationTypeEnum.BLOCKS
                        || relation.getType() == RelationTypeEnum.RELATED_TO)
                        && ((relation.getMainId() == task.getId()
                        && relation.getSubordinateId() == relatedTask.getId())
                        || (relation.getSubordinateId() == task.getId()
                        && relation.getMainId() == relatedTask.getId()))) {
                    printRelatedElement(relatedTask, TASK, relation);
                }
            }
        }
        printRelationType(STORY);
        for (Relation relation : relations) {
            for (Story story : task.getRelatedStories()) {
                if (relation.getType() == RelationTypeEnum.STORY_TASK
                        && relation.getMainId() == story.getId()
                        && relation.getSubordinateId() == task.getId()) {
                    printRelatedElement(story, STORY, relation);
                }
            }
        }
        printRelationType(DEFECTS);
        for (Relation relation : relations) {
            for (Defect defect : task.getRelatedDefects()) {
                if ((relation.getType() == RelationTypeEnum.DEFECT_FOUND
                        || relation.getType() == RelationTypeEnum.FIXES_DEFECT)
                        && relation.getMainId() == task.getId()
                        && relation.getSubordinateId() == defect.getId()) {
                    printRelatedElement(defect, DEFECT, relation);
                }
            }
        }
    }

    public void printStory(Story story, List<Relation> relations) {
        System.out.println("Id истории: " + story.getId()
                + " Название: " + story.getTitle()
                + " Описание: " + story.getDescription());
        printRelationType(FEATURE);
        for (Relation relation : relations) {
            for (Feature feature : story.getRelatedFeatures()) {
                if (relation.getType() == RelationTypeEnum.FEATURE_STORY
                        && relation.getMainId() == feature.getId()
                        && relation.getSubordinateId() == story.getId()) {
                    printRelatedElement(feature, FEATURE, relation);
                }
            }
        }
        printRelationType(TASK);
        for (Relation relation : relations) {
            for (Task task : story.getRelatedTasks()) {
                if (relation.getType() == RelationTypeEnum.STORY_TASK
                        && relation.getMainId() == story.getId()
                        && relation.getSubordinateId() == task.getId()) {
                    printRelatedElement(task, TASK, relation);
                }
            }
        }
        //TODO можно ли помимо констант создать файл-словарь для склонений?
        printRelationType(DEFECTS);
        for (Relation relation : relations) {
            for (Defect defect : story.getRelatedDefects()) {
                if (relation.getType() == RelationTypeEnum.STORY_DEFECT
                        && relation.getMainId() == story.getId()
                        && relation.getSubordinateId() == defect.getId()) {
                    printRelatedElement(defect, DEFECT, relation);
                }
            }
        }
    }

    public void printDefect(Defect defect, List<Relation> relations) {
        System.out.println("Id дефекта: " + defect.getId()
                + " Название: " + defect.getTitle());
        printRelationType(TASK);
        for (Relation relation : relations) {
            for (Task task : defect.getRelatedTasks()) {
                if ((relation.getType() == RelationTypeEnum.DEFECT_FOUND
                        || relation.getType() == RelationTypeEnum.FIXES_DEFECT)
                        && relation.getMainId() == task.getId()
                        && relation.getSubordinateId() == defect.getId()) {
                    printRelatedElement(task, TASK, relation);
                }
            }
        }
        printRelationType(STORY);
        for (Relation relation : relations) {
            for (Story story : defect.getRelatedStories()) {
                if (relation.getType() == RelationTypeEnum.STORY_DEFECT
                        && relation.getMainId() == story.getId()
                        && relation.getSubordinateId() == defect.getId()) {
                    printRelatedElement(story, STORY, relation);
                }
            }
        }
    }

    private void printRelationType(String type) {
        System.out.println("Связанные " + type + ":");
    }

    public void printRelationTypes(List<RelationTypeEnum> types) {
        System.out.println("Выберите один из типов связи: " + types);
    }

    public void printEnterRelation() {
        System.out.print("Если хотите отменить создание элемента введите /cancel: ");
        System.out.println("Тип связи: ");
    }

    public void printRelatedElement(Relation relation) {
        System.out.println("Id связи: " + relation.getId()
                + " Тип связи: " + relation.getType()
                + " id основного элемента: " + relation.getMainId()
                + " id связываемого элемента: " + relation.getSubordinateId()
        );
    }

    public void printAllTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст");
            return;
        }
        System.out.println("Найденные задачи:");
        for (Task task : tasks) {
            printTask(task, relationService.findTaskRelations(task));
        }
    }

    public void printAllStories(List<Story> stories) {
        if (stories.isEmpty()) {
            System.out.println("Список историй пуст");
            return;
        }
        System.out.println("Найденные истории:");
        for (Story story : stories) {
            printStory(story, relationService.findStoryRelations(story));
        }
    }

    public void printAllDefects(List<Defect> defects) {
        if (defects.isEmpty()) {
            System.out.println("Список дефектов пуст");
            return;
        }
        System.out.println("Найденные дефекты:");
        for (Defect defect : defects) {
            printDefect(defect, relationService.findDefectRelations(defect));
        }
    }

    public void printAllFeatures(List<Feature> features) {
        if (features.isEmpty()) {
            System.out.println("Список фичей пуст");
            return;
        }
        System.out.println("Найденные фичи:");
        for (Feature feature : features) {
            printFeature(feature, relationService.findFeatureRelations(feature));
        }
    }

    public void printAllRelations(List<Relation> relations) {
        if (relations.isEmpty()) {
            System.out.println("Список связей пуст");
            return;
        }
        System.out.println("Найденные связи:");
        for (Relation relation : relations) {
            printRelatedElement(relation);
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}

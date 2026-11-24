package service;

import enums.RelationTypeEnum;
import model.*;

import java.util.List;

import static constants.UpdateElement.*;

//TODO излишне много методов принта одной модели можно удалить часть
public class PrintService {
    private final RelationService relationService;

    public PrintService(RelationService relationService, StoryService storyService, TaskService taskService) {
        this.relationService = relationService;
    }

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

    public void printTask(Task task) {
        System.out.println("Id задачи: " + task.getId()
                + " Название: " + task.getTitle()
                + " Описание задачи: " + task.getDescription()
        );
        System.out.println("Связанные дефекты:");
        for (Defect defect : task.getRelatedDefects()) {
            printDefectShort(defect);
        }
        System.out.println("Связанные задачи:");
        for (Task tasks : task.getRelatedTasks()) {
            printTaskShort(tasks);
        }
        System.out.println("Связанные истории:");
        for (Story story : task.getRelatedStories()) {
            printStoryShort(story);
        }
    }

    private void printTaskShort(Task task) {
        System.out.println(
                "Id задачи: " + task.getId()
                        + " Название: " + task.getTitle()
        );
    }

    public void printStory(Story story) {
        System.out.println("Id истории: " + story.getId()
                + " Название: " + story.getTitle()
                + " Описание истории: " + story.getDescription()
        );
        System.out.println("Связанные дефекты:");
        for (Defect defect : story.getRelatedDefects()) {
            printDefectShort(defect);
        }
        System.out.println("Связанные задачи:");
        for (Task task : story.getRelatedTasks()) {
            printTaskShort(task);
        }
        System.out.println("Связанные фичи:");
        for (Feature feature : story.getRelatedFeatures()) {
            printFeatureShort(feature);
        }
    }

    private void printStoryShort(Story story) {
        System.out.println("Id истории: " + story.getId()
                + " Название: " + story.getTitle()
        );
    }

    public void printDefect(Defect defect) {
        System.out.println("Id дефекта: " + defect.getId()
                + " Название: " + defect.getTitle()
                + " Описание дефекта: " + defect.getDescription()
        );
        System.out.println("Связанные задачи:");
        for (Task task : defect.getRelatedTasks().getAll()) {
            printTaskShort(task);
        }
        System.out.println("Связанные истории:");
        for (Story story : defect.getRelatedStories().getAll()) {
            printStoryShort(story);
        }
    }

    private void printDefectShort(Defect defect) {
        System.out.println(
                "Id дефекта: " + defect.getId()
                        + " Название: " + defect.getTitle()
        );
    }

    public void printFeature(Feature feature) {
        System.out.println("Id фичи: " + feature.getId()
                + " Название: " + feature.getTitle()
                + " Описание фичи: " + feature.getDescription()
        );
        System.out.println("Связанные истории:");
        for (Story story : feature.getRelatedStories()) {
            printStoryShort(story);
        }
    }

    public void printFeatureWithRelations(Feature feature, List<Relation> relations) {
        System.out.println("Id фичи: " + feature.getId()
                + " Название: " + feature.getTitle());
        System.out.println("Связанные истории:");
        for (Relation relation : relations) {
            for (Story story : feature.getRelatedStories()) {
                if (story.getId() == relation.getSubordinateId()) {
                    System.out.println(
                            "Id истории: " + story.getId()
                                    + " Название: " + story.getTitle()
                                    + " Тип связи: " + relation.getType()
                    );
                }
            }
        }
    }

    public void printTaskWithRelations(Task task, List<Relation> relations) {
        System.out.println("Id задачи: " + task.getId()
                + " Название: " + task.getTitle());
        System.out.println("Связанные задачи:");
        for (Relation relation : relations) {
            for (Task relatedTask : task.getRelatedTasks()) {
                if ((relation.getType() == RelationTypeEnum.PARENT_TASK
                        || relation.getType() == RelationTypeEnum.BLOCKS
                        || relation.getType() == RelationTypeEnum.RELATED_TO)
                        && ((relation.getMainId() == task.getId()
                        && relation.getSubordinateId() == relatedTask.getId())
                        || (relation.getSubordinateId() == task.getId()
                        && relation.getMainId() == relatedTask.getId()))) {
                    System.out.println(
                            "Id задачи: " + relatedTask.getId()
                                    + " Название: " + relatedTask.getTitle()
                                    + " Тип связи: " + relation.getType()
                    );
                }
            }
        }
        System.out.println("Связанные истории:");
        for (Relation relation : relations) {
            for (Story story : task.getRelatedStories()) {
                if (relation.getType() == RelationTypeEnum.STORY_TASK
                        && relation.getMainId() == story.getId()
                        && relation.getSubordinateId() == task.getId()) {
                    System.out.println(
                            "Id истории: " + story.getId()
                                    + " Название: " + story.getTitle()
                                    + " Тип связи: " + relation.getType()
                    );
                }
            }
        }
        System.out.println("Связанные дефекты:");
        for (Relation relation : relations) {
            for (Defect defect : task.getRelatedDefects()) {
                if ((relation.getType() == RelationTypeEnum.DEFECT_FOUND
                        || relation.getType() == RelationTypeEnum.FIXES_DEFECT)
                        && relation.getMainId() == task.getId()
                        && relation.getSubordinateId() == defect.getId()) {
                    System.out.println(
                            "Id дефекта: " + defect.getId()
                                    + " Название: " + defect.getTitle()
                                    + " Тип связи: " + relation.getType()
                    );
                }
            }
        }
    }

    public void printStoryWithRelations(Story story, List<Relation> relations) {
        System.out.println("Id истории: " + story.getId()
                + " Название: " + story.getTitle()
                + " Описание: " + story.getDescription());
        System.out.println("Связанные фичи:");
        for (Relation relation : relations) {
            for (Feature feature : story.getRelatedFeatures()) {
                if (relation.getType() == RelationTypeEnum.FEATURE_STORY
                        && relation.getMainId() == feature.getId()
                        && relation.getSubordinateId() == story.getId()) {
                    System.out.println("Id фичи: " + feature.getId()
                            + " Название: " + feature.getTitle()
                            + " Тип связи: " + relation.getType());
                }
            }
        }
        System.out.println("Связанные задачи:");
        for (Relation relation : relations) {
            for (Task task : story.getRelatedTasks()) {
                if (relation.getType() == RelationTypeEnum.STORY_TASK
                        && relation.getMainId() == story.getId()
                        && relation.getSubordinateId() == task.getId()) {
                    System.out.println("Id задачи: " + task.getId()
                            + " Название: " + task.getTitle()
                            + " Тип связи: " + relation.getType());
                }
            }
        }
        System.out.println("Связанные дефекты:");
        for (Relation relation : relations) {
            for (Defect defect : story.getRelatedDefects()) {
                if (relation.getType() == RelationTypeEnum.STORY_DEFECT
                        && relation.getMainId() == story.getId()
                        && relation.getSubordinateId() == defect.getId()) {
                    System.out.println("Id дефекта: " + defect.getId()
                            + " Название: " + defect.getTitle()
                            + " Тип связи: " + relation.getType());
                }
            }
        }
    }

    public void printDefectWithRelations(
            Defect defect,
            List<Relation> relations) {
        System.out.println("Id дефекта: " + defect.getId()
                + " Название: " + defect.getTitle());
        System.out.println("Связанные задачи:");
        for (Relation relation : relations) {
            for (Task task : defect.getRelatedTasks().getAll()) {
                if ((relation.getType() == RelationTypeEnum.DEFECT_FOUND
                        || relation.getType() == RelationTypeEnum.FIXES_DEFECT)
                        && relation.getMainId() == task.getId()
                        && relation.getSubordinateId() == defect.getId()) {
                    System.out.println(
                            "Id задачи: " + task.getId()
                                    + " Название: " + task.getTitle()
                                    + " Тип связи: " + relation.getType()
                    );
                }
            }
        }
        System.out.println("Связанные истории:");
        for (Relation relation : relations) {
            for (Story story : defect.getRelatedStories().getAll()) {
                if (relation.getType() == RelationTypeEnum.STORY_DEFECT
                        && relation.getMainId() == story.getId()
                        && relation.getSubordinateId() == defect.getId()) {
                    System.out.println(
                            "Id истории: " + story.getId()
                                    + " Название: " + story.getTitle()
                                    + " Тип связи: " + relation.getType()
                    );
                }
            }
        }
    }

    private void printFeatureShort(Feature feature) {
        System.out.println(
                "Id фичи: " + feature.getId()
                        + " Название: " + feature.getTitle()
        );
    }

    public void printRelationTypes(List<RelationTypeEnum> types) {
        System.out.println("Выберите один из типов связи: " + types);
    }

    public void printEnterRelation() {
        System.out.print("Если хотите отменить создание элемента введите /cancel: ");
        System.out.println("Тип связи: ");
    }

    public void printRelation(Relation relation) {
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
            printTaskWithRelations(task, relationService.findTaskRelations(task));
        }
    }

    public void printAllStories(List<Story> stories) {
        if (stories.isEmpty()) {
            System.out.println("Список историй пуст");
            return;
        }
        System.out.println("Найденные истории:");
        for (Story story : stories) {
            printStoryWithRelations(story, relationService.findStoryRelations(story));
        }
    }

    public void printAllDefects(List<Defect> defects) {
        if (defects.isEmpty()) {
            System.out.println("Список дефектов пуст");
            return;
        }
        System.out.println("Найденные дефекты:");
        for (Defect defect : defects) {
            printDefectWithRelations(defect, relationService.findDefectRelations(defect));
        }
    }

    public void printAllFeatures(List<Feature> features) {
        if (features.isEmpty()) {
            System.out.println("Список фичей пуст");
            return;
        }
        System.out.println("Найденные фичи:");
        for (Feature feature : features) {
            printFeatureWithRelations(feature, relationService.findFeatureRelations(feature));
        }
    }

    public void printAllRelations(List<Relation> relations) {
        if (relations.isEmpty()) {
            System.out.println("Список связей пуст");
            return;
        }
        System.out.println("Найденные связи:");
        for (Relation relation : relations) {
            printRelation(relation);
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}

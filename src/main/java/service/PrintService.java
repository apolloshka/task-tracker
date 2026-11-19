
package service;

import enums.RelationTypeEnum;
import model.*;


import java.util.List;

import static constants.UpdateElements.*;

public class PrintService {

    public void printMenu() {
        System.out.println();
        System.out.println("1 - Создать задачу");
        System.out.println("2 - Показать все задачи");
        System.out.println("3 - Удалить задачу");
        System.out.println("4 - Изменить задачу");
        System.out.println("5 - Найти задачу по Id");
        System.out.println("6 - Найти задачу по названию");
        System.out.println("0 - Выход");
        System.out.println("7 - Создать историю");
        System.out.println("8 - Создать связь");
        System.out.println("9 - Изменить историю");
        System.out.println("10 - Удалить историю");
        System.out.println("11 - Найти историю по Id");
        System.out.println("12 - Найти историю по названию");
        System.out.println("13 - Показать все истории");
        System.out.println("14 - Создать дефект");
        System.out.println("15 - Показать все дефекты");
        System.out.println("16 - Удалить дефект");
        System.out.println("17 - Изменить дефект");
        System.out.println("18 - Найти дефект по Id");
        System.out.println("19 - Найти дефект по названию");
        System.out.println("20 - Создать фичу");
        System.out.println("21 - Показать все фичи");
        System.out.println("22 - Удалить фичу");
        System.out.println("23 - Изменить фичу");
        System.out.println("24 - Найти фичу по Id");
        System.out.println("25 - Найти фичу по названию");
        System.out.println("26 - Удалить связь");
        System.out.println("27 - Показать все связи");
        System.out.println("28 - Изменить связь");


        System.out.print("Введите команду: ");
    }

    public void printEnterTitle() {
        System.out.print("Введите название: ");
    }

    public void printEnterDescription() {
        System.out.print("Введите описание: ");
    }


    //TODO как-то объеденить передавая тип элемента на основании которого будет выводиться задача/истрия/дефект в SOP вместо "элемента"
    public void printEnterIdToDelete() {
        System.out.print(
                "Введите Id элемента, который хотите удалить: "
        );
    }

    public void printEnterIdToUpdate() {
        System.out.print(
                "Введите Id элемента, который хотите изменить: "
        );
    }

    public void printEnterIdToSearch() {
        System.out.print(
                "Введите Id элемента, который хотите найти: "
        );
    }

    public void printEnterTitleToSearch() {
        System.out.print(
                "Введите название элемента, который хотите найти: "
        );
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

        for (Task task : defect.getRelatedTasks()) {
            printTaskShort(task);
        }

        System.out.println("Связанные истории:");

        for (Story story : defect.getRelatedStories()) {
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
            printTask(task);
        }
    }

    public void printAllStories(List<Story> stories) {
        if (stories.isEmpty()) {
            System.out.println("Список историй пуст");
            return;
        }

        System.out.println("Найденные истории:");

        for (Story story : stories) {
            printStory(story);
        }
    }

    public void printAllDefects(List<Defect> defects) {
        if (defects.isEmpty()) {
            System.out.println("Список дефектов пуст");
            return;
        }

        System.out.println("Найденные дефекты:");

        for (Defect defect : defects) {
            printDefect(defect);
        }
    }

    public void printAllFeatures(List<Feature> features) {
        if (features.isEmpty()) {
            System.out.println("Список фичей пуст");
            return;
        }

        System.out.println("Найденные фичи:");

        for (Feature feature : features) {
            printFeature(feature);
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

package service;

import enums.RelationTypeEnum;
import exception.*;
import model.*;

import java.io.IOException;
import java.util.List;

import static constants.UpdateElements.*;

public class ConsoleService {
    private final InputService inputService;
    private final PrintService printService;
    private final TaskService taskService;
    private final RelationService relationService;
    private final StoryService storyService;
    private final DefectService defectService;
    private final FeatureService featureService;
    private final ModelService modelService;


    public ConsoleService(InputService inputService,
                          PrintService printService,
                          TaskService taskService,
                          RelationService relationService,
                          StoryService storyService,
                          DefectService defectService,
                          FeatureService featureService,
                          ModelService modelService) {
        this.inputService = inputService;
        this.printService = printService;
        this.taskService = taskService;
        this.relationService = relationService;
        this.storyService = storyService;
        this.defectService = defectService;
        this.featureService = featureService;
        this.modelService = modelService;
    }

    public void run() {
        boolean running = true;

        while (running) {
            printService.printMenu();

            int command = inputService.readInt();

            switch (command) {

                case 1: {
                    createTask();
                    break;
                }

                case 2: {
                    showAllTasks();
                    break;
                }

                case 3: {
                    deleteTask();
                    break;
                }

                case 4: {
                    updateTask();
                    break;
                }

                case 5: {
                    findTaskById();
                    break;
                }

                case 6: {
                    findTasksByTitle();
                    break;
                }

                case 7: {
                    createStory();
                    break;
                }

                case 8: {
                    createRelation();
                    break;
                }

                case 9: {
                    updateStory();
                    break;
                }

                case 10: {
                    deleteStory();
                    break;
                }

                case 11: {
                    findStoryById();
                    break;
                }

                case 12: {
                    findStoryByTitle();
                    break;
                }

                case 13: {
                    showAllStories();
                    break;
                }

                case 14: {
                    createDefect();
                    break;
                }

                case 15: {
                    showAllDefects();
                    break;
                }

                case 16: {
                    deleteDefect();
                    break;
                }

                case 17: {
                    updateDefect();
                    break;
                }

                case 18: {
                    findDefectById();
                    break;
                }

                case 19: {
                    findDefectByTitle();
                    break;
                }

                case 20: {
                    createFeature();
                    break;
                }

                case 21: {
                    showAllFeatures();
                    break;
                }

                case 22: {
                    deleteFeature();
                    break;
                }

                case 23: {
                    updateFeature();
                    break;
                }

                case 24: {
                    findFeatureById();
                    break;
                }

                case 25: {
                    findFeatureByTitle();
                    break;
                }

                case 26: {
                    deleteRelation();
                    break;
                }

                case 27: {
                    showAllRelations();
                    break;
                }

                case 28: {
                    updateRelation();
                    break;
                }

                case 0: {
                    running = false;
                    break;
                }


                default: {
                    printService.printMessage("Неизвестная команда");
                }
            }
        }
    }

    private void showAllTasks() {
        printService.printAllTasks(taskService.getAllTasks());

    }

    private void showAllStories() {
        printService.printAllStories(storyService.getAllStories());
    }

    private void showAllDefects() {
        printService.printAllDefects(defectService.getAllDefects());
    }

    private void showAllFeatures() {
        printService.printAllFeatures(featureService.getAllFeatures());
    }

    private void showAllRelations() {
        printService.printAllRelations(relationService.getRelations());
    }

    private void createTask() {
        printService.printEnterTitle();
        String title = inputService.readString();

        printService.printEnterDescription();
        String description = inputService.readString();

        Task task = new Task(title, description);

        try {
            taskService.addTask(task);
            taskService.saveTaskToJson(taskService.getAllTasks());
            printService.printMessage("Задача добавлена");
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить задачу в файл");
        }
    }

    private void createStory() {
        printService.printEnterTitle();
        String title = inputService.readString();

        printService.printEnterDescription();
        String description = inputService.readString();

        Story story = new Story(title, description);

        try {
            storyService.addStory(story);
            storyService.saveStoryToJson(storyService.getAllStories());
            printService.printMessage("История добавлена");
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить историю в файл");
        }
    }

    private void createDefect() {
        printService.printEnterTitle();
        String title = inputService.readString();

        printService.printEnterDescription();
        String description = inputService.readString();

        Defect defect = new Defect(title, description);

        try {
            defectService.addDefect(defect);
            defectService.saveDefectToJson(defectService.getAllDefects());
            printService.printMessage("Дефект добавлен");
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить дефект в файл");
        }
    }

    private void createFeature() {
        printService.printEnterTitle();
        String title = inputService.readString();

        printService.printEnterDescription();
        String description = inputService.readString();

        Feature feature = new Feature(title, description);

        try {
            featureService.addFeature(feature);
            featureService.saveFeatureToJson(featureService.getAllFeatures());
            printService.printMessage("Фича добавлена");
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить дефект в файл");
        }
    }

    public void createRelation() {
        printService.printRelationTypes(relationService.getRelationType());
        printService.printEnterRelation();
        String type = inputService.readString();

        switch (type) {
            case "FEATURE_STORY": {
                relationFeatureStory(type);
                break;
            }


            case "STORY_TASK": {
                relationStoryTask(type);
                break;
            }

            case "STORY_DEFECT": {
                relationStoryDefect(type);
                break;
            }


            case "PARENT_TASK": {
                relationParentTask(type);
                break;

            }

            case "BLOCKS": {
                relationBlockTask(type);
                break;
            }

            case "RELATED_TO":
                relationRelatedToTask(type);
                break;

            case "DEFECT_FOUND":
                relationDefectTask(type);
                break;


            case "FIXES_DEFECT":
                relationFixesToTask(type);
                break;
        }
    }

    private void deleteTask() {
        List<Task> allTasks = taskService.getAllTasks();

        if (allTasks.isEmpty()) {
            printService.printMessage("Задач для удаления нет");
            return;
        }

        printService.printAllTasks(allTasks);
        printService.printEnterIdToDelete();

        int numId = inputService.readInt();

        try {
            taskService.deleteTask(numId);
            taskService.saveTaskToJson(taskService.getAllTasks());
            printService.printMessage("Задача с Id " + numId + " удалена");
        } catch (TaskNotFoundException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось удалить задачу из файла");
        }
    }

    private void deleteStory() {
        List<Story> allStory = storyService.getAllStories();

        if (allStory.isEmpty()) {
            printService.printMessage("Историй для удаления нет");
            return;
        }

        printService.printAllStories(allStory);
        printService.printEnterIdToDelete();

        int numId = inputService.readInt();

        try {
            storyService.deleteStory(numId);
            storyService.saveStoryToJson(storyService.getAllStories());
            printService.printMessage("История с Id " + numId + " удалена");
        } catch (StoryNotFoundException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось удалить историю из файла");
        }
    }

    private void deleteFeature() {
        List<Feature> allFeature = featureService.getAllFeatures();

        if (allFeature.isEmpty()) {
            printService.printMessage("Дефектов для удаления нет");
            return;
        }

        printService.printAllFeatures(allFeature);
        printService.printEnterIdToDelete();

        int numId = inputService.readInt();

        try {
            featureService.deleteFeature(numId);
            featureService.saveFeatureToJson(featureService.getAllFeatures());
            printService.printMessage("Фича с Id " + numId + " удалена");
        } catch (FeatureNotFoundException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось удалить фичу из файла");
        }
    }

    private void deleteDefect() {
        List<Defect> allDefect = defectService.getAllDefects();

        if (allDefect.isEmpty()) {
            printService.printMessage("Дефектов для удаления нет");
            return;
        }

        printService.printAllDefects(allDefect);
        printService.printEnterIdToDelete();

        int numId = inputService.readInt();

        try {
            defectService.deleteDefect(numId);
            defectService.saveDefectToJson(defectService.getAllDefects());
            printService.printMessage("История с Id " + numId + " удалена");
        } catch (DefectNotFoundException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось удалить дефект из файла");
        }
    }

    private void deleteRelation() {
        List<Relation> allRelations = relationService.getRelations();

        if (allRelations.isEmpty()) {
            printService.printMessage("Связей для удаления нет");
            return;
        }

        printService.printAllRelations(allRelations);
        printService.printEnterIdToDelete();

        int numId = inputService.readInt();

        try {
            relationService.deleteRelation(numId);
            relationService.saveRelationToJson(relationService.getRelations());
            printService.printMessage("Связь с Id " + numId + " удалена");
        } catch (RelationNotFoundException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось удалить связь из файла");
        }
    }

    private void findTaskById() {
        printService.printEnterIdToSearch();

        int id = inputService.readInt();

        try {
            Task task = taskService.findTaskById(id);
            printService.printTask(task);
        } catch (TaskNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findStoryById() {
        printService.printEnterIdToSearch();

        int id = inputService.readInt();

        try {
            Story story = storyService.findStoryById(id);
            printService.printStory(story);
        } catch (StoryNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findFeatureById() {
        printService.printEnterIdToSearch();

        int id = inputService.readInt();

        try {
            Feature feature = featureService.findFeatureById(id);
            printService.printFeature(feature);
        } catch (FeatureNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findDefectById() {
        printService.printEnterIdToSearch();

        int id = inputService.readInt();

        try {
            Defect defect = defectService.findDefectById(id);
            printService.printDefect(defect);
        } catch (DefectNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }


    private void findTasksByTitle() {
        printService.printEnterTitleToSearch();

        String title = inputService.readString();

        try {
            List<Task> foundTasks = taskService.findTasksByTitle(title);

            if (foundTasks.isEmpty()) {
                printService.printMessage("Задачи с таким названием не найдены");
            } else {
                printService.printAllTasks(foundTasks);
            }
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findStoryByTitle() {
        printService.printEnterTitleToSearch();

        String title = inputService.readString();

        try {
            List<Story> foundStories = storyService.findStoryByTitle(title);

            if (foundStories.isEmpty()) {
                printService.printMessage("Истории с таким названием не найдены");
            } else {
                printService.printAllStories(foundStories);
            }
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findFeatureByTitle() {
        printService.printEnterTitleToSearch();

        String title = inputService.readString();

        try {
            List<Feature> foundFeatures = featureService.findFeatureByTitle(title);

            if (foundFeatures.isEmpty()) {
                printService.printMessage("Фичи с таким названием не найдены");
            } else {
                printService.printAllFeatures(foundFeatures);
            }
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findDefectByTitle() {
        printService.printEnterTitleToSearch();

        String title = inputService.readString();

        try {
            List<Defect> foundDefects = defectService.findDefectByTitle(title);

            if (foundDefects.isEmpty()) {
                printService.printMessage("Дефекты с таким названием не найдены");
            } else {
                printService.printAllDefects(foundDefects);
            }
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void updateTask() {
        List<Task> allTasks = taskService.getAllTasks();

        if (allTasks.isEmpty()) {
            printService.printMessage("Задач для изменения нет");
            return;
        }

        printService.printAllTasks(allTasks);
        printService.printEnterIdToUpdate();

        int numId = inputService.readInt();
        printService.printUpdateMenu();
        int num = inputService.readInt();

        if (num != TITLE && num != DESCRIPTION) {
            printService.printMessage("Неизвестный вариант");
            return;
        }

        String value = readUpdateValue(num);

        String newTitle = null;
        String newDescription = null;

        if (num == TITLE) {
            newTitle = value;

        } else {
            newDescription = value;
        }

        try {
            taskService.updateTask(numId, newTitle, newDescription);
            taskService.saveTaskToJson(taskService.getAllTasks());
            printService.printMessage("Задача изменена");
        } catch (TaskNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось обновить задачу в файле");
        }
    }

    private void updateStory() {
        List<Story> allStories = storyService.getAllStories();

        if (allStories.isEmpty()) {
            printService.printMessage("Историй для изменения нет");
            return;
        }

        printService.printAllStories(allStories);
        printService.printEnterIdToUpdate();

        int numId = inputService.readInt();
        printService.printUpdateMenu();
        int num = inputService.readInt();

        if (num != TITLE && num != DESCRIPTION) {
            printService.printMessage("Неизвестный вариант");
            return;
        }

        String value = readUpdateValue(num);

        String newTitle = null;
        String newDescription = null;

        if (num == TITLE) {
            newTitle = value;

        } else {
            newDescription = value;
        }

        try {
            storyService.updateStory(numId, newTitle, newDescription);
            storyService.saveStoryToJson(storyService.getAllStories());
            printService.printMessage("Задача изменена");
        } catch (StoryNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось обнвоить историю в файле");
        }
    }

    private void updateFeature() {
        List<Feature> allFeatures = featureService.getAllFeatures();

        if (allFeatures.isEmpty()) {
            printService.printMessage("Фич для изменения нет");
            return;
        }

        printService.printAllFeatures(allFeatures);
        printService.printEnterIdToUpdate();

        int numId = inputService.readInt();
        printService.printUpdateMenu();
        int num = inputService.readInt();

        if (num != TITLE && num != DESCRIPTION) {
            printService.printMessage("Неизвестный вариант");
            return;
        }

        String value = readUpdateValue(num);

        String newTitle = null;
        String newDescription = null;

        if (num == TITLE) {
            newTitle = value;

        } else {
            newDescription = value;
        }

        try {
            featureService.updateFeature(numId, newTitle, newDescription);
            featureService.saveFeatureToJson(featureService.getAllFeatures());
            printService.printMessage("Фича изменена");
        } catch (FeatureNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось обновить фичу в файле");
        }
    }

    private void updateDefect() {
        List<Defect> allDefects = defectService.getAllDefects();

        if (allDefects.isEmpty()) {
            printService.printMessage("Дефектов для изменения нет");
            return;
        }

        printService.printAllDefects(allDefects);
        printService.printEnterIdToUpdate();

        int numId = inputService.readInt();
        printService.printUpdateMenu();
        int num = inputService.readInt();

        if (num != TITLE && num != DESCRIPTION) {
            printService.printMessage("Неизвестный вариант");
            return;
        }

        String value = readUpdateValue(num);

        String newTitle = null;
        String newDescription = null;

        if (num == TITLE) {
            newTitle = value;

        } else {
            newDescription = value;
        }

        try {
            defectService.updateDefect(numId, newTitle, newDescription);
            defectService.saveDefectToJson(defectService.getAllDefects());
            printService.printMessage("Дефект изменён");
        } catch (DefectNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось обновить дефект в файле");
        }
    }

    private void updateRelation() {
        List<Relation> allRelations = relationService.getRelations();

        if (allRelations.isEmpty()) {
            printService.printMessage("Связей для изменения нет");
            return;
        }

        printService.printAllRelations(allRelations);
        printService.printEnterIdToUpdate();

        int numId = inputService.readInt();
        printService.printUpdateRelationMenu();
        int num = inputService.readInt();

        if (num != MAIN && num != SUB) {
            printService.printMessage("Неизвестный вариант");
            return;
        }

        int value = readUpdateIdValue(num);

        int newMAIN = 0;
        int newSUB = 0;

        if (num == MAIN) {
            newMAIN = value;

        } else {
            newSUB = value;
        }

        try {
            relationService.updateRelation(numId, newMAIN, newSUB);
            relationService.saveRelationToJson(relationService.getRelations());
            printService.printMessage("Связь изменена");
        } catch (RelationNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось обновить связь в файле");
        }
    }

    private String readUpdateValue(int num) {
        if (num == TITLE) {
            printService.printMessage("Введите новое название:");
            return inputService.readString();

        } else if (num == DESCRIPTION) {
            printService.printMessage("Введите новое описание:");
            return inputService.readString();
        }
        return null;
    }

    private int readUpdateIdValue(int num) {
        if (num == MAIN) {
            printService.printMessage("Введите новый id главного элемента:");
            return inputService.readInt();

        } else if (num == SUB) {
            printService.printMessage("Введите новый id зависимого элемента:");
            return inputService.readInt();
        }
        return 0;
    }

    private void relationFeatureStory(String type) {
        printService.printAllStories(storyService.getAllStories());
        System.out.println("Выберите id истории, которую хотите свзять с фичёй: ");
        int id_story = inputService.readInt();

        printService.printAllFeatures(featureService.getAllFeatures());
        System.out.println("Выберите id фичи, которую хотите свзять с историей id = " + id_story);
        int id_feature = inputService.readInt();

        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_feature, id_story);

        Feature feature = featureService.findFeatureById(id_feature);
        Story story = storyService.findStoryById(id_story);
        story.addFeatureToRelation(feature);
        feature.addStoryToRelation(story);

        try {
            relationService.addRelation(relation);
//            storyService.saveStoryToJson(storyService.getAllStories());
            printService.printRelation(relation);
            relationService.saveRelationToJson(relationService.getRelations());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить связь в файл");
        }
    }

    private void relationStoryTask(String type) {
        printService.printAllStories(storyService.getAllStories());
        System.out.println("Выберите id истории, которую хотите свзять с задачей: ");
        int id_story = inputService.readInt();

        printService.printAllTasks(taskService.getAllTasks());
        System.out.println("Выберите id задачи, которую хотите свзять с историей id = " + id_story);
        int id_task = inputService.readInt();

        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_story, id_task);

        Story story = storyService.findStoryById(id_story);
        Task task = taskService.findTaskById(id_task);
        story.addTaskToRelation(task);
        task.addStoryToRelation(story);

        try {
            relationService.addRelation(relation);
            printService.printRelation(relation);
            relationService.saveRelationToJson(relationService.getRelations());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить связь в файл");
        }
    }

    private void relationStoryDefect(String type) {
        printService.printAllStories(storyService.getAllStories());
        System.out.println("Выберите id истории, которую хотите свзять с дефектом: ");
        int id_story = inputService.readInt();

        printService.printAllDefects(defectService.getAllDefects());
        System.out.println("Выберите id дефекта, который хотите свзять с историей id = " + id_story);
        int id_defect = inputService.readInt();

        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_story, id_defect);

        Story story = storyService.findStoryById(id_story);
        Defect defect = defectService.findDefectById(id_defect);
        story.addDefectToRelation(defect);
        defect.addStoryToRelation(story);

        try {
            relationService.addRelation(relation);
            printService.printRelation(relation);
            relationService.saveRelationToJson(relationService.getRelations());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить связь в файл");
        }
    }

    private void relationParentTask(String type) {
        printService.printAllTasks(taskService.getAllTasks());
        System.out.println("Выберите id задачи, которую хотите свзять с подзадачей: ");
        int id_taskMain = inputService.readInt();

        printService.printAllTasks(taskService.getAllTasks());
        System.out.println("Выберите id подзадачи, которую хотите свзять с задачей id = " + id_taskMain);
        int id_taskSub = inputService.readInt();

        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_taskMain, id_taskSub);

        Task taskMain = taskService.findTaskById(id_taskMain);
        Task taskSub = taskService.findTaskById(id_taskSub);
        taskMain.addTaskToRelation(taskSub);
        taskSub.addTaskToRelation(taskMain);

        try {
            relationService.addRelation(relation);
            printService.printRelation(relation);
            relationService.saveRelationToJson(relationService.getRelations());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить связь в файл");
        }

    }

    private void relationBlockTask(String type) {
        printService.printAllTasks(taskService.getAllTasks());
        System.out.println("Выберите id блокирующей задачи: ");
        int id_taskMain = inputService.readInt();

        printService.printAllTasks(taskService.getAllTasks());
        System.out.println("Выберите id задачи, блокируемой задачей id = " + id_taskMain);
        int id_taskSub = inputService.readInt();

        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_taskMain, id_taskSub);

        Task taskMain = taskService.findTaskById(id_taskMain);
        Task taskSub = taskService.findTaskById(id_taskSub);
        taskMain.addTaskToRelation(taskSub);
        taskSub.addTaskToRelation(taskMain);

        try {
            relationService.addRelation(relation);
            printService.printRelation(relation);
            relationService.saveRelationToJson(relationService.getRelations());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить связь в файл");
        }

    }

    private void relationRelatedToTask(String type) {
        printService.printAllTasks(taskService.getAllTasks());
        System.out.println("Выберите id связываемой задачи: ");
        int id_taskMain = inputService.readInt();

        printService.printAllTasks(taskService.getAllTasks());
        System.out.println("Выберите id задачи, связанной с задачей id = " + id_taskMain);
        int id_taskSub = inputService.readInt();

        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_taskMain, id_taskSub);

        Task taskMain = taskService.findTaskById(id_taskMain);
        Task taskSub = taskService.findTaskById(id_taskSub);
        taskMain.addTaskToRelation(taskSub);
        taskSub.addTaskToRelation(taskMain);

        try {
            relationService.addRelation(relation);
            printService.printRelation(relation);
            relationService.saveRelationToJson(relationService.getRelations());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить связь в файл");
        }
    }

    private void relationDefectTask(String type) {
        printService.printAllTasks(taskService.getAllTasks());
        System.out.println("Выберите id задачи, по которой обнаружен дефект: ");
        int id_task = inputService.readInt();

        printService.printAllDefects(defectService.getAllDefects());
        System.out.println("Выберите id дефекта, который описывает задачу с id = " + id_task);
        int id_defect = inputService.readInt();

        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_task, id_defect);

        Task task = taskService.findTaskById(id_task);
        Defect defect = defectService.findDefectById(id_defect);
        task.addDefectToRelation(defect);
        defect.addTaskToRelation(task);

        try {
            relationService.addRelation(relation);
            printService.printRelation(relation);
            relationService.saveRelationToJson(relationService.getRelations());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить связь в файл");
        }
    }

    private void relationFixesToTask(String type) {
        printService.printAllTasks(taskService.getAllTasks());
        System.out.println("Выберите id задачи, по которой необходимо исправить дефект: ");
        int id_task = inputService.readInt();

        printService.printAllDefects(defectService.getAllDefects());
        System.out.println("Выберите id дефекта, который описывает задачу с id = " + id_task);
        int id_defect = inputService.readInt();

        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_task, id_defect);

        Task task = taskService.findTaskById(id_task);
        Defect defect = defectService.findDefectById(id_defect);
        task.addDefectToRelation(defect);
        defect.addTaskToRelation(task);

        try {
            relationService.addRelation(relation);
            printService.printRelation(relation);
            relationService.saveRelationToJson(relationService.getRelations());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить связь в файл");
        }
    }
}



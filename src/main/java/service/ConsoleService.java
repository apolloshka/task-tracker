package service;

import enums.RelationType;
import exception.*;
import model.*;

import static CONSTANTS.UpdateConstants.TITLE;
import static CONSTANTS.UpdateConstants.DESCRIPTION;

import java.io.IOException;
import java.util.List;

public class ConsoleService {
    private final InputService inputService;
    private final PrintService printService;
    private final TaskService taskService;
    private final FileService fileService;
    //private final RelationService relationService;
    private final StoryService storyService;
    private final DefectService defectService;
    private final FeatureService featureService;
    //private final ModelService modelService;


    public ConsoleService(InputService inputService,
                          PrintService printService,
                          TaskService taskService,
                          FileService fileService,
                          RelationService relationService,
                          StoryService storyService,
                          DefectService defectService,
                          FeatureService featureService,
                          ModelService modelService) {
        this.inputService = inputService;
        this.printService = printService;
        this.taskService = taskService;
        this.fileService = fileService;
        //this.relationService = relationService;
        this.storyService = storyService;
        this.defectService = defectService;
        this.featureService = featureService;
        //this.modelService = modelService;
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
                    //createRelation();
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

    private void createTask() {
        printService.printEnterTitle();
        String title = inputService.readString();

        printService.printEnterDescription();
        String description = inputService.readString();

        Task task = new Task(title, description);

        try {
            taskService.addTask(task);
            fileService.saveTasks(taskService.getAllTasks());
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

        storyService.addStory(story);
    }

    private void createDefect() {
        printService.printEnterTitle();
        String title = inputService.readString();

        printService.printEnterDescription();
        String description = inputService.readString();

        Defect defect = new Defect(title, description);

        defectService.addDefect(defect);
    }

    private void createFeature() {
        printService.printEnterTitle();
        String title = inputService.readString();

        printService.printEnterDescription();
        String description = inputService.readString();

        Feature feature = new Feature(title, description);

        featureService.addFeature(feature);
    }

//    в процессе обдумывания реализации
//    public void createRelation() {
//        printService.printRelationTypes(relationService.getRelationType());
//        printService.printEnterRelation();
//        String type = inputService.readString();
//
//        printService.printModelsTypes(modelService.getModels());
//        printService.printEnterModel();
//        String model = inputService.readString();
//
//        if (model == "FEATURE") {
//            printService.printAllFeatures(featureService.getAllFeatures());
//        }
//
//        printService.printEnterMainElement();
//        int mainId = inputService.readInt();
//
//        printService.printEnterSubordinateElement();
//        int subordinateId = inputService.readInt();
//
//        Relation relation = new Relation(RelationType.valueOf(type), mainId, subordinateId);
//        printService.printRelation(relation);
//
//        relationService.addRelation(relation);
//    }

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
            fileService.saveTasks(taskService.getAllTasks());
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
            printService.printMessage("История с Id " + numId + " удалена");
        } catch (StoryNotFoundException e) {
            printService.printMessage(e.getMessage());
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
            printService.printMessage("Фича с Id " + numId + " удалена");
        } catch (FeatureNotFoundException e) {
            printService.printMessage(e.getMessage());
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
            printService.printMessage("История с Id " + numId + " удалена");
        } catch (DefectNotFoundException e) {
            printService.printMessage(e.getMessage());
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
            fileService.saveTasks(taskService.getAllTasks());
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
            printService.printMessage("Задача изменена");
        } catch (StoryNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
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
            printService.printMessage("Фича изменена");
        } catch (FeatureNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
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
            printService.printMessage("Дефект изменён");
        } catch (DefectNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
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
}



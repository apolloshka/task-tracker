package service;

import enums.RelationTypeEnum;
import exception.*;
import model.*;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import static constants.UpdateElement.*;

public class ConsoleService {
    private final InputService inputService;
    private final PrintService printService;
    private final TaskService taskService;
    private final RelationService relationService;
    private final StoryService storyService;
    private final DefectService defectService;
    private final UniversalModelService<Feature> featureService;
    private static final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public ConsoleService(InputService inputService,
                          PrintService printService,
                          TaskService taskService,
                          RelationService relationService,
                          StoryService storyService,
                          DefectService defectService,
                          UniversalModelService<Feature> featureService) {
        this.inputService = inputService;
        this.printService = printService;
        this.taskService = taskService;
        this.relationService = relationService;
        this.storyService = storyService;
        this.defectService = defectService;
        this.featureService = featureService;
    }

    public void run() {
        boolean running = true;
        while (running) {
            printService.printMenu();
            int command = inputService.readInt();
            switch (command) {
                case 1: {
                    printService.printTaskMenu();
                    int commandElement = inputService.readInt();
                    switch (commandElement) {
                        case 0:
                            break;
                        case 1:
                            createTask();
                            break;
                        case 2:
                            showAllTasks();
                            break;
                        case 3:
                            deleteTask();
                            break;
                        case 4:
                            updateTask();
                            break;
                        case 5:
                            findTaskById();
                            break;
                        case 6:
                            findTasksByTitle();
                            break;
                        default:
                            printService.printMessage("Неизвестная команда");
                    }
                    break;
                }
                case 2: {
                    printService.printStoryMenu();
                    int commandElement = inputService.readInt();
                    switch (commandElement) {
                        case 0:
                            break;
                        case 1:
                            createStory();
                            break;
                        case 2:
                            showAllStories();
                            break;
                        case 3:
                            deleteStory();
                            break;
                        case 4:
                            updateStory();
                            break;
                        case 5:
                            findStoryById();
                            break;
                        case 6:
                            findStoriesByTitle();
                            break;
                        default:
                            printService.printMessage("Неизвестная команда");
                    }
                    break;
                }
                case 3: {
                    printService.printDefectMenu();
                    int commandElement = inputService.readInt();
                    switch (commandElement) {
                        case 0:
                            break;
                        case 1:
                            createDefect();
                            break;
                        case 2:
                            showAllDefects();
                            break;
                        case 3:
                            deleteDefect();
                            break;
                        case 4:
                            updateDefect();
                            break;
                        case 5:
                            findDefectById();
                            break;
                        case 6:
                            findDefectsByTitle();
                            break;
                        default:
                            printService.printMessage("Неизвестная команда");
                    }
                    break;
                }
                case 4: {
                    printService.printFeatureMenu();
                    int commandElement = inputService.readInt();
                    switch (commandElement) {
                        case 0:
                            break;
                        case 1:
                            createFeature();
                            break;
                        case 2:
                            showAllFeatures();
                            break;
                        case 3:
                            deleteFeature();
                            break;
                        case 4:
                            updateFeature();
                            break;
                        case 5:
                            findFeatureById();
                            break;
                        case 6:
                            findFeaturesByTitle();
                            break;
                        default:
                            printService.printMessage("Неизвестная команда");
                    }
                    break;
                }
                case 5: {
                    printService.printRelationMenu();
                    int commandElement = inputService.readInt();
                    switch (commandElement) {
                        case 0:
                            break;
                        case 1:
                            createRelation();
                            break;
                        case 2:
                            showAllRelations();
                            break;
                        case 3:
                            deleteRelation();
                            break;
                        case 4:
                            updateRelation();
                            break;
                        default:
                            printService.printMessage("Неизвестная команда");
                    }
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
        printService.printAllTasks(taskService.getAll());
    }

    private void showAllStories() {
        printService.printAllStories(storyService.getAll());
    }

    private void showAllDefects() {
        printService.printAllDefects(defectService.getAll());
    }

    private void showAllFeatures() {
        printService.printAllFeatures(featureService.getAll());
    }

    private void showAllRelations() {
        printService.printAllRelations(relationService.getRelations());
    }

    private void createTask() {
        printService.printEnterTitle();
        String title = inputService.readString();
        if (title.equals("/cancel")) {
            printService.printMessage("Создание задачи отменено");
            return;
        }
        printService.printEnterDescription();
        String description = inputService.readString();
        if (description.equals("/cancel")) {
            printService.printMessage("Создание задачи отменено");
            return;
        }
        Task task = new Task(title, description);
        try {
            taskService.add(task);
            taskService.save();
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
        if (title.equals("/cancel")) {
            printService.printMessage("Создание истории отменено");
            return;
        }
        printService.printEnterDescription();
        String description = inputService.readString();
        if (description.equals("/cancel")) {
            printService.printMessage("Создание истории отменено");
            return;
        }
        Story story = new Story(title, description);
        try {
            storyService.add(story);
            storyService.save();
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
        if (title.equals("/cancel")) {
            printService.printMessage("Создание дефекта отменено");
            return;
        }
        printService.printEnterDescription();
        String description = inputService.readString();
        if (description.equals("/cancel")) {
            printService.printMessage("Создание дефекта отменено");
            return;
        }
        Defect defect = new Defect(title, description);
        try {
            defectService.addElement(defect);
            defectService.save();
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
        if (title.equals("/cancel")) {
            printService.printMessage("Создание фичи отменено");
            return;
        }
        printService.printEnterDescription();
        String description = inputService.readString();
        if (description.equals("/cancel")) {
            printService.printMessage("Создание фичи отменено");
            return;
        }
        Feature feature = new Feature(title, description);
        try {
            featureService.add(feature);
            featureService.save();
            printService.printMessage("Фича добавлена");
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить фичу в файл");
        }
    }

    public void createRelation() {
        printService.printRelationTypes(relationService.getRelationType());
        printService.printEnterRelation();
        String type = inputService.readString();
        if (type.equals("/cancel")) {
            printService.printMessage("Создание связи отменено");
            return;
        }
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

    private <T extends Element> void deleteElement(DeleteService<T> service, String elementName) {
        printService.printMessage(messages.getString("element.id.delete"));
        int numId = inputService.readInt();
        try {
            service.delete(numId);
            service.save();
            printService.printMessage(elementName + " с id " + numId + " удален(-а)");
        } catch (RuntimeException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage(messages.getString("no.element.to.delete"));
        }
    }

    private void deleteTask() {
        deleteElement(taskService, "Задача");
    }

    private void deleteStory() {
        deleteElement(storyService, "История");
    }

    private void deleteFeature() {
        deleteElement(featureService, "Фича");
    }

    private void deleteDefect() {
        deleteElement(defectService, "Дефект");
    }

    private void deleteRelation() {
        List<Relation> allRelations = relationService.getRelations();
        if (allRelations.isEmpty()) {
            printService.printMessage("Связей для удаления нет");
            return;
        }
        printService.printAllRelations(allRelations);
        printService.printMessage(messages.getString("element.id.delete"));
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
        printService.printMessage(messages.getString("element.id.search"));
        int id = inputService.readInt();
        try {
            Task task = taskService.findById(id);
            printService.printTask(task);
        } catch (TaskNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findStoryById() {
        printService.printMessage(messages.getString("element.id.search"));
        int id = inputService.readInt();
        try {
            Story story = storyService.findById(id);
            printService.printStory(story);
        } catch (StoryNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findFeatureById() {
        printService.printMessage(messages.getString("element.id.search"));
        int id = inputService.readInt();
        try {
            Feature feature = featureService.findById(id);
            printService.printFeature(feature);
        } catch (FeatureNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findDefectById() {
        printService.printMessage(messages.getString("element.id.search"));
        int id = inputService.readInt();
        try {
            Defect defect = defectService.findById(id);
            printService.printDefect(defect);
        } catch (DefectNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findTasksByTitle() {
        printService.printMessage(messages.getString("element.title.search"));
        String title = inputService.readString();
        try {
            List<Task> foundTasks = taskService.findByTitle(title);
            if (foundTasks.isEmpty()) {
                printService.printMessage("Задачи с таким названием не найдены");
            } else {
                printService.printAllTasks(foundTasks);
            }
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findStoriesByTitle() {
        printService.printMessage(messages.getString("element.title.search"));
        String title = inputService.readString();
        try {
            List<Story> foundStories = storyService.findByTitle(title);
            if (foundStories.isEmpty()) {
                printService.printMessage("Истории с таким названием не найдены");
            } else {
                printService.printAllStories(foundStories);
            }
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findFeaturesByTitle() {
        printService.printMessage(messages.getString("element.title.search"));
        String title = inputService.readString();
        try {
            List<Feature> foundFeatures = featureService.findByTitle(title);
            if (foundFeatures.isEmpty()) {
                printService.printMessage("Фичи с таким названием не найдены");
            } else {
                printService.printAllFeatures(foundFeatures);
            }
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findDefectsByTitle() {
        printService.printMessage(messages.getString("element.title.search"));
        String title = inputService.readString();
        try {
            List<Defect> foundDefects = defectService.findByTitle(title);
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
        List<Task> allTasks = taskService.getAll();
        if (allTasks.isEmpty()) {
            printService.printMessage(messages.getString("no.task.to.change"));
            return;
        }
        printService.printAllTasks(allTasks);
        printService.printMessage(messages.getString("element.id.update"));
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
            taskService.update(numId, newTitle, newDescription);
            taskService.save();
            printService.printMessage("Задача изменена");
        } catch (TaskNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось обновить задачу в файле");
        }
    }

    private void updateStory() {
        List<Story> allStories = storyService.getAll();
        if (allStories.isEmpty()) {
            printService.printMessage(messages.getString("no.story.to.change"));
            return;
        }
        printService.printAllStories(allStories);
        printService.printMessage(messages.getString("element.id.update"));
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
            storyService.update(numId, newTitle, newDescription);
            storyService.save();
            printService.printMessage("Задача изменена");
        } catch (StoryNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось обнвоить историю в файле");
        }
    }

    private void updateFeature() {
        List<Feature> allFeatures = featureService.getAll();
        if (allFeatures.isEmpty()) {
            printService.printMessage(messages.getString("no.feature.to.change"));
            return;
        }
        printService.printAllFeatures(allFeatures);
        printService.printMessage(messages.getString("element.id.update"));
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
            featureService.update(numId, newTitle, newDescription);
            featureService.save();
            printService.printMessage("Фича изменена");
        } catch (FeatureNotFoundException | InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось обновить фичу в файле");
        }
    }

    private void updateDefect() {
        List<Defect> allDefects = defectService.getAll();
        if (allDefects.isEmpty()) {
            printService.printMessage(messages.getString("no.defect.to.change"));
            return;
        }
        printService.printAllDefects(allDefects);
        printService.printMessage(messages.getString("element.id.update"));
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
            defectService.updateElement(numId, newTitle, newDescription);
            defectService.save();
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
            printService.printMessage(messages.getString("no.relation.to.change"));
            return;
        }
        printService.printAllRelations(allRelations);
        printService.printMessage(messages.getString("element.id.update"));
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
        printService.printAllStories(storyService.getAll());
        System.out.println("Выберите id истории, которую хотите свзять с фичёй: ");
        int id_story = inputService.readInt();
        printService.printAllFeatures(featureService.getAll());
        System.out.println("Выберите id фичи, которую хотите свзять с историей id = " + id_story);
        int id_feature = inputService.readInt();
        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_feature, id_story);
        Feature feature = featureService.findById(id_feature);
        Story story = storyService.findById(id_story);
        story.addRelatedElement(story.getRelatedFeatures(), feature);
        feature.addRelatedElement(story);
        saveRelation(relation);
    }

    private void relationStoryTask(String type) {
        printService.printAllStories(storyService.getAll());
        System.out.println("Выберите id истории, которую хотите свзять с задачей: ");
        int id_story = inputService.readInt();
        printService.printAllTasks(taskService.getAll());
        System.out.println("Выберите id задачи, которую хотите свзять с историей id = " + id_story);
        int id_task = inputService.readInt();
        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_story, id_task);
        Story story = storyService.findById(id_story);
        Task task = taskService.findById(id_task);
        story.addRelatedElement(story.getRelatedTasks(), task);
        task.addStoryToRelation(story);
        saveRelation(relation);
    }

    private void relationStoryDefect(String type) {
        printService.printAllStories(storyService.getAll());
        System.out.println("Выберите id истории, которую хотите свзять с дефектом: ");
        int id_story = inputService.readInt();
        printService.printAllDefects(defectService.getAll());
        System.out.println("Выберите id дефекта, который хотите свзять с историей id = " + id_story);
        int id_defect = inputService.readInt();
        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_story, id_defect);
        Story story = storyService.findById(id_story);
        Defect defect = defectService.findById(id_defect);
        story.addRelatedElement(story.getRelatedDefects(), defect);
        defect.getRelatedStories().add(story);
        saveRelation(relation);
    }

    private void relationParentTask(String type) {
        printService.printAllTasks(taskService.getAll());
        System.out.println("Выберите id задачи, которую хотите свзять с подзадачей: ");
        int id_taskMain = inputService.readInt();
        printService.printAllTasks(taskService.getAll());
        System.out.println("Выберите id подзадачи, которую хотите свзять с задачей id = " + id_taskMain);
        int id_taskSub = inputService.readInt();
        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_taskMain, id_taskSub);
        Task taskMain = taskService.findById(id_taskMain);
        Task taskSub = taskService.findById(id_taskSub);
        taskMain.addTaskToRelation(taskSub);
        taskSub.addTaskToRelation(taskMain);
        saveRelation(relation);
    }

    private void relationBlockTask(String type) {
        printService.printAllTasks(taskService.getAll());
        System.out.println("Выберите id блокирующей задачи: ");
        int id_taskMain = inputService.readInt();
        printService.printAllTasks(taskService.getAll());
        System.out.println("Выберите id задачи, блокируемой задачей id = " + id_taskMain);
        int id_taskSub = inputService.readInt();
        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_taskMain, id_taskSub);
        Task taskMain = taskService.findById(id_taskMain);
        Task taskSub = taskService.findById(id_taskSub);
        taskMain.addTaskToRelation(taskSub);
        taskSub.addTaskToRelation(taskMain);
        saveRelation(relation);
    }

    private void relationRelatedToTask(String type) {
        printService.printAllTasks(taskService.getAll());
        System.out.println("Выберите id связываемой задачи: ");
        int id_taskMain = inputService.readInt();
        printService.printAllTasks(taskService.getAll());
        System.out.println("Выберите id задачи, связанной с задачей id = " + id_taskMain);
        int id_taskSub = inputService.readInt();
        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_taskMain, id_taskSub);
        Task taskMain = taskService.findById(id_taskMain);
        Task taskSub = taskService.findById(id_taskSub);
        taskMain.addTaskToRelation(taskSub);
        taskSub.addTaskToRelation(taskMain);
        saveRelation(relation);
    }

    private void relationDefectTask(String type) {
        printService.printAllTasks(taskService.getAll());
        System.out.println("Выберите id задачи, по которой обнаружен дефект: ");
        int id_task = inputService.readInt();
        printService.printAllDefects(defectService.getAll());
        System.out.println("Выберите id дефекта, который описывает задачу с id = " + id_task);
        int id_defect = inputService.readInt();
        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_task, id_defect);
        Task task = taskService.findById(id_task);
        Defect defect = defectService.findById(id_defect);
        task.addDefectToRelation(defect);
        defect.getRelatedTasks().add(task);
        saveRelation(relation);
    }

    private void relationFixesToTask(String type) {
        printService.printAllTasks(taskService.getAll());
        System.out.println("Выберите id задачи, по которой необходимо исправить дефект: ");
        int id_task = inputService.readInt();
        printService.printAllDefects(defectService.getAll());
        System.out.println("Выберите id дефекта, который описывает задачу с id = " + id_task);
        int id_defect = inputService.readInt();
        Relation relation = new Relation(RelationTypeEnum.valueOf(type), id_task, id_defect);
        Task task = taskService.findById(id_task);
        Defect defect = defectService.findById(id_defect);
        task.addDefectToRelation(defect);
        defect.getRelatedTasks().add(task);
        saveRelation(relation);
    }

    private void saveRelation(Relation relation) {
        try {
            relationService.addRelation(relation);
            printService.printRelation(relation);
            relationService.saveRelationToJson(relationService.getRelations());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить связь в файл");
        }
    }
}



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
    private final UniversalModelService<Task> taskService;
    private final RelationService relationService;
    private final UniversalModelService<Story> storyService;
    private final UniversalModelService<Defect> defectService;
    private final UniversalModelService<Feature> featureService;
    private static final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public ConsoleService(InputService inputService,
                          PrintService printService,
                          UniversalModelService<Task> taskService,
                          RelationService relationService,
                          UniversalModelService<Story> storyService,
                          UniversalModelService<Defect> defectService,
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
                            printService.printMessage(messages.getString("unknown.command"));
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
                            printService.printMessage(messages.getString("unknown.command"));
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
                            printService.printMessage(messages.getString("unknown.command"));
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
                            printService.printMessage(messages.getString("unknown.command"));
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
                            printService.printMessage(messages.getString("unknown.command"));
                    }
                    break;
                }
                case 0: {
                    running = false;
                    break;
                }
                default: {
                    printService.printMessage(messages.getString("unknown.command"));
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

    private <T extends Element> void createElement(OperationsService<T> service, T element, String elementName) {
        printService.printEnterTitle();
        String title = inputService.readString();
        if (title.equals("/cancel")) {
            printService.printMessage("Создание отменено");
            return;
        }
        printService.printEnterDescription();
        String description = inputService.readString();
        if (description.equals("/cancel")) {
            printService.printMessage("Создание отменено");
            return;
        }
        element.setTitle(title);
        element.setDescription(description);
        try {
            service.add(element);
            service.save();
            printService.printMessage(elementName + " добавлен(-а)");
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить элемент");
        }
    }

    private void createTask() {
        createElement(taskService, new Task(), "Задача");
    }

    private void createStory() {
        createElement(storyService, new Story(), "История");
    }

    private void createDefect() {
        createElement(defectService, new Defect(), "Дефект");
    }

    private void createFeature() {
        createElement(featureService, new Feature(), "Фича");
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

    private <T extends Element> void deleteElement(OperationsService<T> service, String elementName) {
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

    private <T extends Element> void updateElement(OperationsService<T> service) {
        printService.printMessage(messages.getString("element.id.update"));
        int id = inputService.readInt();
        printService.printUpdateMenu();
        int command = inputService.readInt();
        if (command != TITLE && command != DESCRIPTION) {
            printService.printMessage(messages.getString("unknown.command"));
            return;
        }
        String value = readUpdateValue(command);
        String newTitle = null;
        String newDescription = null;
        if (command == TITLE) {
            newTitle = value;
        } else {
            newDescription = value;
        }
        try {
            service.update(id, newTitle, newDescription);
            service.save();
            printService.printMessage(messages.getString("update.element"));
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить изменения");
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
            printService.printTask(task, relationService.findTaskRelations(task));
        } catch (TaskNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findStoryById() {
        printService.printMessage(messages.getString("element.id.search"));
        int id = inputService.readInt();
        try {
            Story story = storyService.findById(id);
            printService.printStory(story, relationService.findStoryRelations(story));
        } catch (StoryNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findFeatureById() {
        printService.printMessage(messages.getString("element.id.search"));
        int id = inputService.readInt();
        try {
            Feature feature = featureService.findById(id);
            printService.printFeature(feature, relationService.findFeatureRelations(feature));
        } catch (FeatureNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void findDefectById() {
        printService.printMessage(messages.getString("element.id.search"));
        int id = inputService.readInt();
        try {
            Defect defect = defectService.findById(id);
            printService.printDefect(defect, relationService.findDefectRelations(defect));
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
                printService.printMessage(messages.getString("tasks.this.title.not.found"));
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
                printService.printMessage(messages.getString("stories.this.title.not.found"));
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
                printService.printMessage(messages.getString("features.this.title.not.found"));
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
                printService.printMessage(messages.getString("defects.this.title.not.found"));
            } else {
                printService.printAllDefects(foundDefects);
            }
        } catch (InvalidElementException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void updateTask() {
        if (taskService.getAll().isEmpty()) {
            printService.printMessage(messages.getString("no.task.to.change"));
            return;
        }
        printService.printAllTasks(taskService.getAll());
        updateElement(taskService);
    }

    private void updateStory() {
        if (storyService.getAll().isEmpty()) {
            printService.printMessage(messages.getString("no.story.to.change"));
            return;
        }
        printService.printAllStories(storyService.getAll());
        updateElement(storyService);
    }

    private void updateFeature() {
        if (featureService.getAll().isEmpty()) {
            printService.printMessage(messages.getString("no.feature.to.change"));
            return;
        }
        printService.printAllFeatures(featureService.getAll());
        updateElement(featureService);
    }

    private void updateDefect() {
        if (defectService.getAll().isEmpty()) {
            printService.printMessage(messages.getString("no.defect.to.change"));
            return;
        }
        printService.printAllDefects(defectService.getAll());
        updateElement(defectService);
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
        feature.addRelatedElement(feature.getRelatedStories(), story);
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
        task.addRelatedElement(task.getRelatedStories(), story);
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
        defect.addRelatedElement(defect.getRelatedStories(), story);
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
        taskMain.addRelatedElement(taskMain.getRelatedTasks(), taskSub);
        taskSub.addRelatedElement(taskSub.getRelatedTasks(), taskMain);
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
        taskMain.addRelatedElement(taskMain.getRelatedTasks(), taskSub);
        taskSub.addRelatedElement(taskSub.getRelatedTasks(), taskMain);
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
        taskMain.addRelatedElement(taskMain.getRelatedTasks(), taskSub);
        taskSub.addRelatedElement(taskSub.getRelatedTasks(), taskMain);
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
        task.addRelatedElement(task.getRelatedDefects(), defect);
        defect.addRelatedElement(defect.getRelatedTasks(), task);
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
        task.addRelatedElement(task.getRelatedDefects(), defect);
        defect.addRelatedElement(defect.getRelatedTasks(), task);
        saveRelation(relation);
    }

    private void saveRelation(Relation relation) {
        try {
            relationService.addRelation(relation);
            printService.printRelatedElement(relation);
            relationService.saveRelationToJson(relationService.getRelations());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить связь в файл");
        }
    }
}



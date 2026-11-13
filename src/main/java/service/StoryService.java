package service;

import exception.InvalidElementException;
import exception.StoryNotFoundException;
import model.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryService {
    private final List<Story> stories;
    private int id = 1;

    public StoryService() {
        stories = new ArrayList<>();
    }

    public void addStory(Story newStory) {
        if (newStory.getTitle() == null || newStory.getTitle().isBlank()) {
            throw new InvalidElementException("Название истории не может быть пустым");
        }

        newStory.setId(id);
        id++;
        stories.add(newStory);
    }

    public void deleteStory(int id) {
        Story story = findStoryById(id);
        stories.remove(story);
    }


    public void updateStory(
            int id,
            String newTitle,
            String newDescription) {

        Story story = findStoryById(id);

        if (newTitle == null && newDescription == null) {
            throw new InvalidElementException("Не указаны данные для изменения");
        }

        if (newTitle != null && newTitle.isBlank()) {
            throw new InvalidElementException("Название истории не может быть пустым");
        }

        if (newDescription != null && newDescription.isBlank()) {
            throw new InvalidElementException("Описание истории не может быть пустым");
        }

        if (newTitle != null) {
            story.setTitle(newTitle);
        }

        if (newDescription != null) {
            story.setDescription(newDescription);
        }
    }

    public Story findStoryById(int id) {
        for (Story story : stories) {
            if (story.getId() == id) {
                return story;
            }
        }
        throw new StoryNotFoundException(id);
    }

    public List<Story> findStoryByTitle(String title) {

        if (title == null || title.isBlank()) {
            throw new InvalidElementException("Название для поиска не может быть пустым");
        }

        List<Story> foundStories = new ArrayList<>();

        for (Story story : stories) {

            if (story.getTitle().contains(title)) {
                foundStories.add(story);
            }
        }
        return foundStories;
    }

    public List<Story> getAllStories() {
        return new ArrayList<>(stories);
    }
}

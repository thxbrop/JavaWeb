package com.example.oems.entity;

public class Task {
    private int id;
    private String description;
    private Selection selections;
    private Integer correct;

    public Task(int id, String description, Selection selections, Integer correct) {
        this.id = id;
        this.description = description;
        this.selections = selections;
        this.correct = correct;
    }

    public Task(int id, String description, Selection selections) {
        this.id = id;
        this.description = description;
        this.selections = selections;
    }

    public Task(String description, Selection selections, Integer correct) {
        this.description = description;
        this.selections = selections;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Selection getSelections() {
        return selections;
    }

    public void setSelections(Selection selections) {
        this.selections = selections;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", selections=" + selections +
                ", correct=" + correct +
                '}';
    }
}

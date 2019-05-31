package com.example.mad_demo19.model;

public class ToDo {

    private long id;
    private String name;
    private String description;

    public ToDo(String name){
        this.setName(name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "<" + id + ", " + name + ", " + description + '>';
    }
}

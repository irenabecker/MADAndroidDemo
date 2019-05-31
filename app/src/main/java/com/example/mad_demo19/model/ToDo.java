package com.example.mad_demo19.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Diese Klasse beschreibt Daten, die in einer relationalen Datenbank gespeichert werden sollen
@Entity
public class ToDo implements Serializable {

    @PrimaryKey(autoGenerate = true)
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

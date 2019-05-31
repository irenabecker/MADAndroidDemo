package com.example.mad_demo19.model.impl.room;
import com.example.mad_demo19.model.ToDo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoDatabase extends RoomDatabase {

    public abstract  ToDoDao getDao();
}

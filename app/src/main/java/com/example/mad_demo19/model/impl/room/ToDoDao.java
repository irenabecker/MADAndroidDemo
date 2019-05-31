package com.example.mad_demo19.model.impl.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.mad_demo19.model.ToDo;
import java.util.List;

@Dao
public interface ToDoDao {

    @Query("select * from todo")
    public List<ToDo> readAll();

    @Query("select * from todo where id == (:id)")
    public ToDo readById(long id);

    @Insert
    public long create(ToDo item);

    @Delete
    public void delete(ToDo item);
}

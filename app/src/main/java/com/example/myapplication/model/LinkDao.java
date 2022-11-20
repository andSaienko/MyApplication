package com.example.myapplication.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LinkDao {
    @Query("SELECT * FROM links_table")
    LiveData<List<Link>> getAllLinks();

    @Insert
    void insert(Link... links);

    @Update
    void update(Link... links);

    @Delete
    void delete(Link link);

}

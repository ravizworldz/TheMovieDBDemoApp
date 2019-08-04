package com.android.stanley.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.android.stanley.models.MovieDetailsModel;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<MovieDetailsModel> getAll();

  /*  @Query("SELECT * FROM movie where first_name LIKE  :firstName AND last_name LIKE :lastName")
    MovieDetailsModel findByName(String firstName, String lastName);*/

    /*@Query("SELECT COUNT(*) from movie")
    int countUsers();*/

    @Insert
    void insertAll(MovieDetailsModel... movie);

    /*@Delete
    void delete(MovieDetailsModel user);*/
}

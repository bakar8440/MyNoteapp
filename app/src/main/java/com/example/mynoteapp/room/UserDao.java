package com.example.mynoteapp.room;

import androidx.room.Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(Users user);

    @Update
    void update(Users user);

    @Query("delete from users WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM users")
    List<Users> getAllUsers();



}
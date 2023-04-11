package com.lwp.ebook.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("select * from user where fictionId=:fictionId and userId=:userId")
    User get(String fictionId,int userId);
    @Query("select * from user where userId=:userId")
    List<User> getBookList(int userId);
    @Query("update user set position=:position where id=:id")
    void update(int id,int position);
}

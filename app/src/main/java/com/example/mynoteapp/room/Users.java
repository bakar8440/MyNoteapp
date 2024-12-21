package com.example.mynoteapp.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity // Specify the table name
// Specify the table name
public class Users implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // Specify the column name for title
    private String title;

    // Specify the column name for description
    private String description;

    // Corrected Constructor
    public Users(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.example.githubrepos.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "repos")
public class Repo {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "full_name")
    private String fullName;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "html_url")
    private String url;

    @ColumnInfo(name = "forks_count")
    private int forks;

    @ColumnInfo(name = "language")
    private String language;


    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public int getForks() {
        return forks;
    }

    public String getLanguage() {
        return language;
    }
}

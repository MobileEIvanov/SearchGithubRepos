package com.example.githubrepos.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static com.example.githubrepos.data.db.RepoContract.DESCRIPTION;
import static com.example.githubrepos.data.db.RepoContract.FORKS;
import static com.example.githubrepos.data.db.RepoContract.FULL_NAME;
import static com.example.githubrepos.data.db.RepoContract.LANGUAGE;
import static com.example.githubrepos.data.db.RepoContract.STARS;
import static com.example.githubrepos.data.db.RepoContract.URL;
import static com.example.githubrepos.data.db.RepoContract._ID;

@Entity(tableName = "repos")
public class Repo {

    @PrimaryKey
    @ColumnInfo(name = _ID)
    private long id;

    @ColumnInfo(name = FULL_NAME)
    private String name;

    @ColumnInfo(name = DESCRIPTION)
    private String description;

    @ColumnInfo(name = URL)
    private String url;

    @ColumnInfo(name = FORKS)
    private int forks;

    @ColumnInfo(name = LANGUAGE)
    private String language;

    @ColumnInfo(name = STARS)
    private String stars;

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

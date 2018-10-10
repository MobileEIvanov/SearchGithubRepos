package com.example.githubrepos.data.db;


import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.githubrepos.models.Repo;

import java.util.List;

import static com.example.githubrepos.data.db.RepoContract.DESCRIPTION;
import static com.example.githubrepos.data.db.RepoContract.FULL_NAME;
import static com.example.githubrepos.data.db.RepoContract.STARS;
import static com.example.githubrepos.data.db.RepoContract.TABLE;


@Dao
public interface GithubRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Repo> repoList);

    @Query("SELECT * FROM " + TABLE + " WHERE (" + FULL_NAME + " LIKE :queryString) OR (" + DESCRIPTION + " LIKE :queryString) ORDER BY " + STARS + "  DESC," + FULL_NAME + " ASC")
    DataSource.Factory<Integer, Repo> reposByName(String queryString);

}

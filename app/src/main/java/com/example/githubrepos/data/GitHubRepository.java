package com.example.githubrepos.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;

import com.example.githubrepos.Injection;
import com.example.githubrepos.data.api.GithubNetworkDataSource;
import com.example.githubrepos.data.db.GithubLocalDataSource;
import com.example.githubrepos.models.Repo;
import com.example.githubrepos.models.RepoSearchResult;

public class GitHubRepository {

    private static final int DATABASE_PAGE_SIZE = 20;
    GithubLocalDataSource database;
    GithubNetworkDataSource service;

    public GitHubRepository(Context context) {
        this.database = Injection.provideLocalDataSource(context);
        this.service = Injection.provideNetworkDataSource();
    }

    public RepoSearchResult search(String query) {

        DataSource.Factory<Integer, Repo> factory = database.reposByName(query);

        PagedList.BoundaryCallback boundaryCallback = new RepoBoundaryCallback(query, service, database);

        LiveData<PagedList<Repo>> data = new LivePagedListBuilder(factory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build();
        LiveData<String> errors = ((RepoBoundaryCallback) boundaryCallback).getNetworkErrors();

        return new RepoSearchResult(data, errors);
    }


}

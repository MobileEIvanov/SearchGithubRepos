package com.example.githubrepos.data.db;

import android.arch.paging.DataSource;

import com.example.githubrepos.models.Repo;

import java.util.List;
import java.util.concurrent.Executor;

public class GithubLocalDataSource {

    private GithubRepoDao mGithubRepoDao;
    private Executor mIOExecutor;

    public GithubLocalDataSource(GithubRepoDao repoDao, Executor ioExecutor) {
        this.mGithubRepoDao = repoDao;
        this.mIOExecutor = ioExecutor;
    }

    /**
     * Insert a list of repos in the database, on a background thread.
     */
    public void insert(final List<Repo> repos) {
        mIOExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mGithubRepoDao.insert(repos);
                // Possible addition of callback
            }
        });
    }

    public DataSource.Factory<Integer, Repo> reposByName(String name) {

        String query = name.replace(" ", "%");
        return mGithubRepoDao.reposByName(query);

    }
}

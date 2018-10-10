package com.example.githubrepos.models;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

public class RepoSearchResult {
    LiveData<PagedList<Repo>> data;
    LiveData<String> networkErrors;

    public RepoSearchResult(LiveData<PagedList<Repo>> data, LiveData<String> networkErrors) {
        this.data = data;
        this.networkErrors = networkErrors;
    }

    public LiveData<PagedList<Repo>> getData() {
        return data;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }
}

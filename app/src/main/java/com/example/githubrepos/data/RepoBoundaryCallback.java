package com.example.githubrepos.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.githubrepos.data.api.GithubNetworkDataSource;
import com.example.githubrepos.data.api.RepoSearchResponse;
import com.example.githubrepos.data.db.GithubLocalDataSource;
import com.example.githubrepos.models.Repo;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoBoundaryCallback extends PagedList.BoundaryCallback<Repo> {
    private String query;
    private GithubNetworkDataSource service;
    private GithubLocalDataSource database;

    public static final int NETWORK_PAGE_SIZE = 50;
    private int lastRequestedPage = 1;
    private MutableLiveData<String> _networkErrors = new MutableLiveData<>();

    private boolean isRequestInProgress = false;

    public LiveData<String> getNetworkErrors() {
        return _networkErrors;
    }

    public RepoBoundaryCallback(String query, GithubNetworkDataSource service, GithubLocalDataSource database) {
        this.query = query;
        this.service = service;
        this.database = database;
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Repo itemAtEnd) {
        requestAndSaveData(query);
    }

    @Override
    public void onZeroItemsLoaded() {
        requestAndSaveData(query);
    }


    private void requestAndSaveData(String query) {
        if (isRequestInProgress) return;

        isRequestInProgress = true;

        service.searchRepos(query, lastRequestedPage, NETWORK_PAGE_SIZE, new Callback<RepoSearchResponse>() {
            @Override
            public void onResponse(Call<RepoSearchResponse> call, Response<RepoSearchResponse> response) {
                if (response.isSuccessful()) {

                    List<Repo> repos = response.body().getItems() != null ? response.body().getItems() : Collections.<Repo>emptyList();
                    database.insert(repos);
                    lastRequestedPage++;
                    isRequestInProgress = false;

                } else {

                    try {
                        _networkErrors.postValue(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
                    } catch (IOException e) {
                        _networkErrors.postValue("Unknown error");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RepoSearchResponse> call, Throwable t) {
                _networkErrors.postValue(t.getMessage());
            }
        });

    }

}


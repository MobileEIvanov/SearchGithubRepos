package com.example.githubrepos.data.api;

import retrofit2.Callback;

public class GithubNetworkDataSource {

    private GithubService service;

    public GithubNetworkDataSource(GithubService service) {
        this.service = service;
    }

    public void searchRepos(String query, int page, int itemsPerPage, Callback<RepoSearchResponse> callback) {
        service.searchRepos(query, page, itemsPerPage).enqueue(callback);
    }

}

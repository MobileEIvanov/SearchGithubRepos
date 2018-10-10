package com.example.githubrepos.ui;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.example.githubrepos.data.GitHubRepository;
import com.example.githubrepos.models.Repo;
import com.example.githubrepos.models.RepoSearchResult;

class SearchRepositoriesViewModel extends ViewModel {
    GitHubRepository repository;


    private MutableLiveData<String> queryLiveData = new MutableLiveData<>();

    SearchRepositoriesViewModel(GitHubRepository repository) {
        this.repository = repository;
    }

    LiveData<RepoSearchResult> reposResult = Transformations.map(queryLiveData, new Function<String, RepoSearchResult>() {
        @Override
        public RepoSearchResult apply(String input) {
            return repository.search(input);
        }
    });

    LiveData<PagedList<Repo>> reposData = Transformations.switchMap(reposResult, new Function<RepoSearchResult, LiveData<PagedList<Repo>>>() {
        @Override
        public LiveData<PagedList<Repo>> apply(RepoSearchResult input) {
            return input.getData();
        }
    });

    LiveData<String> networkErrors = Transformations.switchMap(reposResult, new Function<RepoSearchResult, LiveData<String>>() {
        @Override
        public LiveData<String> apply(RepoSearchResult input) {
            return input.getNetworkErrors();
        }
    });

    /**
     * Search a repository based on a query string.
     */
    void searchRepo(String queryString) {
        queryLiveData.postValue(queryString);
    }

    /**
     * Get the last query value.
     */
    String lastQueryValue() {
        return queryLiveData.getValue();
    }

}

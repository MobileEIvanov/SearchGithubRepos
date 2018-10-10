package com.example.githubrepos.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.githubrepos.ApplicationGithubRepos;
import com.example.githubrepos.Injection;
import com.example.githubrepos.data.GitHubRepository;

public class SearchRepositoriesViewModelFactory implements ViewModelProvider.Factory {
    GitHubRepository repository;

    public SearchRepositoriesViewModelFactory() {
        this.repository = Injection.provideRepository();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SearchRepositoriesViewModel(repository);
    }
}

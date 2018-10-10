package com.example.githubrepos.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.githubrepos.R;
import com.example.githubrepos.databinding.ActivitySearchReposBinding;
import com.example.githubrepos.models.Repo;

public class ActivitySearchRepositories extends AppCompatActivity {

    private static final String DEFAULT_QUERY = "Android";
    public static final String LATEST_QUERY = "latest_query";
    private ActivitySearchReposBinding mBinding;
    private SearchRepositoriesViewModel mViewModel;
    private ReposAdapter mAdapter = new ReposAdapter(ReposAdapter.REPO_COMPARATOR);
    private String mSearchQuery = DEFAULT_QUERY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_repos);


        SearchRepositoriesViewModelFactory viewModelFactory = new SearchRepositoriesViewModelFactory();
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchRepositoriesViewModel.class);

        initAdapter();

        if (savedInstanceState != null) {
            mSearchQuery = savedInstanceState.getString(LATEST_QUERY);
        }

        mViewModel.searchRepo(mSearchQuery);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(LATEST_QUERY, mViewModel.lastQueryValue());
        super.onSaveInstanceState(outState);
    }

    private void initAdapter() {
        mBinding.list.setAdapter(mAdapter);
        mViewModel.reposData.observe(this, new Observer<PagedList<Repo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Repo> repos) {
                showEmptyList(repos.isEmpty());
                mAdapter.submitList(repos);
            }
        });

        mViewModel.networkErrors.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showErrorMessage(s);
            }
        });
    }

    private void showEmptyList(boolean showEmptyList) {
        mBinding.emptyList.setVisibility(showEmptyList ? View.VISIBLE : View.INVISIBLE);
    }

    private void showLoading() {
    }

    private void hideLoading() {

    }

    private void showErrorMessage(String errorMessage) {
        Snackbar.make(mBinding.getRoot(), errorMessage, Snackbar.LENGTH_LONG).show();
    }
}

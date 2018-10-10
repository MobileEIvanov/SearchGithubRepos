package com.example.githubrepos.ui;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubrepos.R;
import com.example.githubrepos.models.Repo;

import java.util.List;

public class ReposAdapter extends PagedListAdapter<Repo, RepoViewHolder> {

    public ReposAdapter(@NonNull DiffUtil.ItemCallback<Repo> diffCallback) {
        super(diffCallback);

    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.repo_view_item, viewGroup, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            if (payloads.get(0) instanceof Repo) {
                holder.bindData((Repo) payloads.get(0));
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder repoViewHolder, int i) {
        Repo repoItem = getItem(i);
        if (repoItem != null) {
            repoViewHolder.bindData(getItem(i));
        }
    }
    AsyncListUtil<Repo> REPO_ASYNC_COMPARE = new AsyncListUtil<>()

    public static DiffUtil.ItemCallback<Repo> REPO_COMPARATOR = new DiffUtil.ItemCallback<Repo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.getFullName().equals(newItem.getFullName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.equals(newItem);
        }

        @Nullable
        @Override
        public Object getChangePayload(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return newItem;
        }
    };
}

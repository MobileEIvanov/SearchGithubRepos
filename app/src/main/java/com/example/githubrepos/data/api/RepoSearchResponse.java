package com.example.githubrepos.data.api;

import com.example.githubrepos.models.Repo;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class RepoSearchResponse {

    @SerializedName("total_count")
    private int total = 0;

    @SerializedName("items")
    private List<Repo> items = Collections.emptyList();

    private int nextPage = 0;

    public int getTotal() {
        return total;
    }

    public List<Repo> getItems() {
        return items;
    }

    public int getNextPage() {
        return nextPage;
    }
}

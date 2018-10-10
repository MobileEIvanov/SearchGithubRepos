package com.example.githubrepos.data.db;

public interface RepoContract {
    String TABLE = "repos";

    String _ID = "id";
    String FULL_NAME = "full_name";
    String DESCRIPTION = "description";
    String URL = "html_url";
    String FORKS = "forks";
    String LANGUAGE = "language";
    String STARS = "stars";

}

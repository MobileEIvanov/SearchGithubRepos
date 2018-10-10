package com.example.githubrepos;

import android.app.Application;
import android.content.Context;

public class ApplicationGithubRepos extends Application {

    static Context sInstance;


    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public static Context getInstance() {
        return sInstance;
    }
}

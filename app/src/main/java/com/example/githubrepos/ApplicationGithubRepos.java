package com.example.githubrepos;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

public class ApplicationGithubRepos extends Application {

    static Context sInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        sInstance = this;
    }

    public static Context getInstance() {
        return sInstance;
    }
}

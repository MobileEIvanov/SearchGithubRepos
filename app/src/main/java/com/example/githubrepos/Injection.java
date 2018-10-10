package com.example.githubrepos;

import android.content.Context;

import com.example.githubrepos.data.GitHubRepository;
import com.example.githubrepos.data.api.GithubNetworkDataSource;
import com.example.githubrepos.data.api.GithubService;
import com.example.githubrepos.data.db.GithubDatabase;
import com.example.githubrepos.data.db.GithubLocalDataSource;

import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injection {


    public static GitHubRepository provideRepository() {
        return new GitHubRepository(ApplicationGithubRepos.getInstance());
    }

    /**
     * NETWORK RELATED INJECTIONS
     */
    private static final String BASE_URL = "https://api.github.com/";

    private static OkHttpClient providesHttpClient() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder().addInterceptor(logger).build();
    }

    private static Retrofit providesRetrofit() {

        return new Retrofit.Builder().
                baseUrl(BASE_URL)
                .client(providesHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GithubService createGithubServiceApi() {
        return providesRetrofit()
                .create(GithubService.class);
    }

    public static GithubNetworkDataSource provideNetworkDataSource() {
        return new GithubNetworkDataSource(createGithubServiceApi());
    }


    /**
     * DATABASE RELATED INJECTIONS
     */
    private static GithubDatabase getDatabase(Context context) {
        return GithubDatabase.getInstance(context.getApplicationContext());
    }


    public static GithubLocalDataSource provideLocalDataSource(Context context) {

        return new GithubLocalDataSource(getDatabase(context).getGithubRepoDao(), Executors.newSingleThreadExecutor());
    }


}

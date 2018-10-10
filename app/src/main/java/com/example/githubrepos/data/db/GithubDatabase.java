package com.example.githubrepos.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Environment;

import com.example.githubrepos.models.Repo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import static com.example.githubrepos.data.db.GithubDatabase.DATABASE_VERSION;

@Database(version = DATABASE_VERSION,exportSchema = false, entities = {Repo.class})
public abstract class GithubDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static GithubDatabase sInstance;
    public static final String DATABASE_NAME = "github_database";
    public static final int DATABASE_VERSION = 1;

    public static GithubDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room
                        .databaseBuilder(context, GithubDatabase.class, DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }


    public abstract GithubRepoDao getGithubRepoDao();


    public static void exportDB(String packageName) {
        try {
            File sd = Environment.getExternalStorageDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "/data/data/" + packageName + "/databases/" + DATABASE_NAME;
                String backupDBPath = DATABASE_NAME + ".db";
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);
                if (!backupDB.exists()) backupDB.createNewFile();
                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

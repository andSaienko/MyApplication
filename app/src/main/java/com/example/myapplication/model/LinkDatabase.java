package com.example.myapplication.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Link.class}, version = 1)
public abstract class LinkDatabase extends RoomDatabase {

    private static LinkDatabase instance;

    public abstract LinkDao linkDao();

    public static synchronized LinkDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            LinkDatabase.class, "production")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private LinkDao linkDao;

        private PopulateDbAsyncTask(LinkDatabase db) {
            linkDao = db.linkDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            linkDao.insert(new Link("Link"));
            return null;
        }
    }
}

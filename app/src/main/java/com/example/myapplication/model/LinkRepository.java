package com.example.myapplication.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LinkRepository {
    private LinkDao linkDao;
    private LiveData<List<Link>> allLinks;

    public LinkRepository(Application application) {
        LinkDatabase database = LinkDatabase.getInstance(application);
        linkDao = database.linkDao();
        allLinks = linkDao.getAllLinks();
    }

    public void insert(Link link) {
        new InsertLinkAsyncTask(linkDao).execute(link);
    }

    public void update(Link link) {
        new UpdateLinkAsyncTask(linkDao).execute(link);
    }

    public void delete(Link link) {
        new DeleteLinkAsyncTask(linkDao).execute(link);
    }

    public LiveData<List<Link>> getAllLinks() {
        return allLinks;
    }

    private static class InsertLinkAsyncTask extends AsyncTask<Link, Void, Void> {
        private LinkDao linkDao;

        private InsertLinkAsyncTask(LinkDao linkDao) {
            this.linkDao = linkDao;
        }

        @Override
        protected Void doInBackground(Link... links) {
            linkDao.insert(links[0]);
            return null;
        }
    }

    private static class UpdateLinkAsyncTask extends AsyncTask<Link, Void, Void> {
        private LinkDao linkDao;

        private UpdateLinkAsyncTask(LinkDao linkDao) {
            this.linkDao = linkDao;
        }

        @Override
        protected Void doInBackground(Link... links) {
            linkDao.update(links[0]);
            return null;
        }
    }

    private static class DeleteLinkAsyncTask extends AsyncTask<Link, Void, Void> {
        private LinkDao linkDao;

        private DeleteLinkAsyncTask(LinkDao linkDao) {
            this.linkDao = linkDao;
        }

        @Override
        protected Void doInBackground(Link... links) {
            linkDao.delete(links[0]);
            return null;
        }
    }
}

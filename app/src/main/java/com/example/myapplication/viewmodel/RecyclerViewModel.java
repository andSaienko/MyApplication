package com.example.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.model.Link;
import com.example.myapplication.model.LinkRepository;

import java.util.List;

public class RecyclerViewModel extends AndroidViewModel {
    private LinkRepository repository;
    private LiveData<List<Link>> allLinks;

    public RecyclerViewModel(@NonNull Application application) {
        super(application);
        repository = new LinkRepository(application);
        allLinks = repository.getAllLinks();
    }

    public void insert(Link link) {
        repository.insert(link);
    }

    public void update(Link link) {
        repository.update(link);
    }

    public List<Link> delete(Link link) {
        repository.delete(link);
        return null;
    }

    public LiveData<List<Link>> getAllLinks() {
        return allLinks;
    }
}

package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.R;
import com.example.myapplication.model.LinkDatabase;
import com.example.myapplication.model.Link;
import com.example.myapplication.viewmodel.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerAdapter.LinkClickListener {

    private RecyclerViewModel recyclerViewModel;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

        recyclerViewModel = new ViewModelProvider(this).get(RecyclerViewModel.class);
        recyclerViewModel.getAllLinks().observe(this, new Observer<List<Link>>() {
            @Override
            public void onChanged(List<Link> links) {
                adapter.submitList(links);
            }
        });
    }


    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("URL", adapter.getCurrentList().get(position).getLink());
        startActivity(intent);
    }

    @Override
    public void onDelClicked(int position) {
        recyclerViewModel.delete(adapter.getCurrentList().get(position));
    }
}
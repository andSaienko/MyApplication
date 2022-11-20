package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Link;

public class RecyclerAdapter extends ListAdapter <Link, RecyclerAdapter.ViewHolder> {

    LinkClickListener linkClickListener;

    protected RecyclerAdapter(LinkClickListener linkClickListener) {
        super(DIFF_CALLBACK);
        this.linkClickListener = linkClickListener;
    }
    public static final DiffUtil.ItemCallback<Link> DIFF_CALLBACK = new DiffUtil.ItemCallback<Link>() {
        @Override
        public boolean areItemsTheSame(@NonNull Link oldItem, @NonNull Link newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Link oldItem, @NonNull Link newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Link link = getItem(position);
        holder.bind(link);
//        holder.linkLetter.setText();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView linkName, linkLetter;
        CardView cardView;
        ImageView btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linkName = itemView.findViewById(R.id.rvLink);
            linkLetter = itemView.findViewById(R.id.linkLetter);
            btDelete = itemView.findViewById(R.id.btDelete);
            cardView = itemView.findViewById(R.id.main_container);
            cardView.setOnClickListener(view -> linkClickListener.onItemClicked(getAdapterPosition()));
            btDelete.setOnClickListener(view -> linkClickListener.onDelClicked(getAdapterPosition()));
        }
        public void bind (Link link) {
            linkName.setText(link.getLink());
        }
    }
    public interface LinkClickListener {
        void onItemClicked(int position);

        void onDelClicked(int position);
    }
}

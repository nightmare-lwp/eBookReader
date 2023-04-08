package com.lwp.ebook.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.ebook.R;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder>{
    private List<String> contents;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_content;

        public ViewHolder(View view) {
            super(view);
            item_content=view.findViewById(R.id.item_content);
        }
    }
    public ContentAdapter(List<String> contents){this.contents=contents;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_content, parent, false);
        final ContentAdapter.ViewHolder holder=new ContentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s=contents.get(position);
        holder.item_content.setText(s);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}

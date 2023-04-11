package com.lwp.ebook.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.ebook.ContentActivity;
import com.lwp.ebook.R;
import com.lwp.ebook.Utils.HttpUtils;
import com.lwp.ebook.model.Book;
import com.lwp.ebook.model.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder>{
    private List<Chapter> chapters;
    private Activity activity;
    private Book book;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_chapter;

        public ViewHolder(View view) {
            super(view);
            item_chapter=view.findViewById(R.id.item_chapter);
        }
    }
    public ChapterAdapter(Book book,List<Chapter> chapters,Activity activity){
        this.chapters=chapters;
        this.activity=activity;
        this.book=book;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chapter, parent, false);
        final ViewHolder holder=new ChapterAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.ViewHolder holder, int position) {
        String s=chapters.get(position).getTitle();
        holder.item_chapter.setText(s);
        holder.item_chapter.setOnClickListener(v->{
            Chapter chapter=chapters.get(position);
            HttpUtils.fictionContentByChapter(activity,book,chapter,position);
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }
}

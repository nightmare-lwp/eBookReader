package com.lwp.ebook.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.ebook.R;
import com.lwp.ebook.Utils.HttpUtils;
import com.lwp.ebook.model.Book;
import com.lwp.ebook.model.Chapter;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    private List<String> classList;
    private Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_class;

        public ViewHolder(View view) {
            super(view);
            item_class=view.findViewById(R.id.item_class);
        }
    }
    public ClassAdapter(List<String> classList,Activity activity){
        this.classList=classList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_class, parent, false);
        final ClassAdapter.ViewHolder holder=new ClassAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ViewHolder holder, int position) {
        String s=classList.get(position);
        holder.item_class.setText(s);
        holder.item_class.setOnClickListener(v->{
            activity.runOnUiThread(()->{
                TextView title= activity.findViewById(R.id.toolbar_title);
                title.setText(s);
            });
            HttpUtils.fiction(activity,"fictionType",s,3);
        });
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }
}

package com.lwp.ebook.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.lwp.ebook.R;
import com.lwp.ebook.model.FourItem;

import java.util.List;

public class FourAdapter extends RecyclerView.Adapter<FourAdapter.ViewHolder>{
    private List<FourItem> fourItemList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;

        public ViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            title = view.findViewById(R.id.title);
        }
    }

    public FourAdapter(List<FourItem> items) {
        fourItemList= items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_four, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        // 设置点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                FourItem item = fourItemList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle(item.getTitle());
                builder.setMessage("这是【"+item.getTitle()+"】页面");
                builder.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FourItem item = fourItemList.get(position);
        holder.icon.setImageResource(item.getIconId());
        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {return fourItemList.size();}
}

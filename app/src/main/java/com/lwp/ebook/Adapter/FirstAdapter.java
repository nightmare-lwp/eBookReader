package com.lwp.ebook.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lwp.ebook.ContentActivity;
import com.lwp.ebook.FirstPageActivity;
import com.lwp.ebook.R;
import com.lwp.ebook.SearchResultActivity;
import com.lwp.ebook.SecondPageActivity;
import com.lwp.ebook.model.Book;

import java.util.List;
public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolder> {
    private List<Book> bookList;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView book_cover;
        TextView book_title;
        TextView book_author;

        public ViewHolder(View view) {
            super(view);
            book_cover=view.findViewById(R.id.book_cover);
            book_title=view.findViewById(R.id.book_title);
            book_author=view.findViewById(R.id.book_author);
        }
    }

    public FirstAdapter(List<Book> items,Context context) {
        this.bookList= items;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_first, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        // 设置点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Book book = bookList.get(position);
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("fictionId",book.getFictionId());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book item = bookList.get(position);
        holder.book_cover.setImageResource(R.drawable.avatar);
        holder.book_title.setText(item.getTitle());
        holder.book_author.setText(item.getAuthor());
        // 使用网络请求库下载图片
        Glide.with(holder.itemView).load(item.getCover()).into(holder.book_cover);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}

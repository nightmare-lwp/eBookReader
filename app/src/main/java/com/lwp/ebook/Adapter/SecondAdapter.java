package com.lwp.ebook.Adapter;

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
import com.lwp.ebook.R;
import com.lwp.ebook.model.Book;

import org.w3c.dom.Text;

import java.util.List;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder>{
    private List<Book> bookList;
    private Context context;
    private boolean flag;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView book_cover;
        TextView book_title;
        TextView book_author;
        TextView book_descs;

        public ViewHolder(View view) {
            super(view);
            book_cover = view.findViewById(R.id.book_cover);
            book_title = view.findViewById(R.id.book_title);
            book_author = view.findViewById(R.id.book_author);
            book_descs=view.findViewById(R.id.book_descs);
        }
    }

    public SecondAdapter(List<Book> items, boolean flag, Context context) {
        this.bookList = items;
        this.context = context;
        this.flag = flag;
    }

    @NonNull
    @Override
    public SecondAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_second, parent, false);
        final SecondAdapter.ViewHolder holder = new SecondAdapter.ViewHolder(view);
        // 设置点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Book book = bookList.get(position);
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("book", book);
                intent.putExtra("flag", flag);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SecondAdapter.ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.book_title.setText(book.getTitle());
        holder.book_author.setText(book.getAuthor());
        holder.book_descs.setText(book.getDescs());
        // 使用网络请求库下载图片
        Glide.with(holder.itemView).load(book.getCover()).into(holder.book_cover);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}

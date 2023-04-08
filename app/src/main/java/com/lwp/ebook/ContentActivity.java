package com.lwp.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.ebook.Adapter.ContentAdapter;
import com.lwp.ebook.Utils.HttpUtils;
import com.lwp.ebook.databinding.BookContentBinding;
import com.lwp.ebook.model.Chapter;

import java.util.List;

public class ContentActivity extends AppCompatActivity {
    private List<String> contents;
    private List<Chapter> chapters;
    private BookContentBinding binding;
    private Toolbar toolbar;
    private TextView title;
    private ImageView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BookContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //获取小说内容
        Intent intent=getIntent();
        String fictionId=intent.getStringExtra("fictionId");
        chapters=HttpUtils.fictionChapter(fictionId);
        contents= HttpUtils.fictionContent(chapters.get(0).getChapterId());
        RecyclerView recyclerView = findViewById(R.id.book_content);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ContentAdapter adapter=new ContentAdapter(contents);
        recyclerView.setAdapter(adapter);
        //顶部Bar
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SearchView searchView=findViewById(R.id.search_view);
        toolbar.removeView(searchView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title=findViewById(R.id.toolbar_title);
        title.setText(chapters.get(0).getTitle());
        list=findViewById(R.id.list);
        list.setOnClickListener(v->{
            Toast.makeText(this, "这是目录", Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

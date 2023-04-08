package com.lwp.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.ebook.Adapter.FirstAdapter;
import com.lwp.ebook.Utils.HttpUtils;
import com.lwp.ebook.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private List<Book> bookList=new ArrayList<>();
    private Toolbar toolbar;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //顶部Bar
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        title=findViewById(R.id.toolbar_title);
        toolbar.removeView(title);

        //搜索内容访问Api，并更新UI
        Intent intent = getIntent();
        String searchText = intent.getStringExtra("search_text");
        bookList= HttpUtils.fiction("title",searchText);
        RecyclerView recyclerView = findViewById(R.id.search_result);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FirstAdapter adapter=new FirstAdapter(bookList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        bookList= HttpUtils.fiction("title",query);
        RecyclerView recyclerView = findViewById(R.id.search_result);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FirstAdapter adapter=new FirstAdapter(bookList,this);
        recyclerView.setAdapter(adapter);
        return true;
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
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

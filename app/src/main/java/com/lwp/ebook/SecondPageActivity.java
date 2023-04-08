package com.lwp.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.lwp.ebook.databinding.ActivitySecondPageBinding;

public class SecondPageActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private ActivitySecondPageBinding binding;
    private Toolbar toolbar;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //顶部Bar，取消home键，设置搜索栏,去掉title
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        title=findViewById(R.id.toolbar_title);
        toolbar.removeView(title);
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
    public boolean onQueryTextSubmit(String query) {
        // 在这里处理搜索框提交事件
        Intent intent = new Intent(SecondPageActivity.this, SearchResultActivity.class);
        intent.putExtra("search_text", query);
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}

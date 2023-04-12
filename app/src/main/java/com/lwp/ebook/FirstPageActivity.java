package com.lwp.ebook;


import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.ebook.Adapter.FirstAdapter;
import com.lwp.ebook.Utils.HttpUtils;
import com.lwp.ebook.databinding.ActivityFirstPageBinding;
import com.lwp.ebook.model.Book;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FirstPageActivity extends AppCompatActivity{
    private ActivityFirstPageBinding binding;
    private Toolbar toolbar;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //顶部Bar，取消home键，去掉搜索栏,去掉list,设置title居中
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        SearchView searchView=findViewById(R.id.search_view);
        ImageView list=findViewById(R.id.list);
        toolbar.removeView(list);
        toolbar.removeView(searchView);
        title=findViewById(R.id.toolbar_title);
        title.setText("正在加载中...");
        title.setGravity(Gravity.CENTER);
        title.setWidth(1000);
        ImageView plus=findViewById(R.id.plus);
        toolbar.removeView(plus);
        initList();
        RecyclerView recyclerView = findViewById(R.id.first_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FirstAdapter adapter=new FirstAdapter(new ArrayList<>(),true,this);
        recyclerView.setAdapter(adapter);
        //注册事件
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 取消注册EventBus
        EventBus.getDefault().unregister(this);
    }
    public void initList(){
        HttpUtils.fictionList(this,1);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateUIEvent(UpdateUIEvent event) {
        HttpUtils.fictionList(this,1);
    }
}

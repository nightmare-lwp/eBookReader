package com.lwp.ebook;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.ebook.Adapter.ClassAdapter;
import com.lwp.ebook.Adapter.FirstAdapter;
import com.lwp.ebook.Adapter.SecondAdapter;
import com.lwp.ebook.databinding.ActivityFirstPageBinding;
import com.lwp.ebook.databinding.ActivityThirdPageBinding;
import com.lwp.ebook.model.Book;

import java.util.ArrayList;
import java.util.List;

public class ThirdPageActivity extends AppCompatActivity {
    private ActivityThirdPageBinding binding;
    private Toolbar toolbar;
    private TextView title;
    private List<String> classList=new ArrayList<>();
    private List<Book> bookList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdPageBinding.inflate(getLayoutInflater());
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
        title.setText("分类");
        title.setGravity(Gravity.CENTER);
        title.setWidth(1000);
        ImageView plus=findViewById(R.id.plus);
        toolbar.removeView(plus);
        //装载分类的列表
        initList();
        RecyclerView recyclerView1 = findViewById(R.id.class_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager);
        ClassAdapter adapter=new ClassAdapter(classList,this);
        recyclerView1.setAdapter(adapter);

        RecyclerView recyclerView2 = findViewById(R.id.class_content_recyclerview);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView2.setLayoutManager(gridLayoutManager);
        SecondAdapter secondAdapter=new SecondAdapter(bookList,false,this);
        recyclerView2.setAdapter(secondAdapter);
    }
    public void initList(){
        classList.add("玄幻小说");
        classList.add("武侠仙侠");
        classList.add("科幻灵异");
        classList.add("历史军事");
        classList.add("都市小说");
        classList.add("网游小说");
        classList.add("女生小说");
        classList.add("同人小说");
    }
}

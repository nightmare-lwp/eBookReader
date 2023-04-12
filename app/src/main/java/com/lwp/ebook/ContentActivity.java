package com.lwp.ebook;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.lwp.ebook.Adapter.ContentAdapter;
import com.lwp.ebook.Utils.HttpUtils;
import com.lwp.ebook.databinding.BookContentBinding;
import com.lwp.ebook.model.Book;
import com.lwp.ebook.model.Chapter;
import com.lwp.ebook.model.database.AppDatabase;
import com.lwp.ebook.model.database.User;
import com.lwp.ebook.model.database.UserDao;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {
    private List<String> contents=new ArrayList<>();
    private List<Chapter> chapters=new ArrayList<>();
    private BookContentBinding binding;
    private Toolbar toolbar;
    private TextView title;
    private ImageView list;
    private ImageView plus;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BookContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //获取小说内容
        Intent intent=getIntent();
        Book book=intent.getSerializableExtra("book",Book.class);
        boolean flag=intent.getBooleanExtra("flag",false);
        HttpUtils.fictionContentByFiction(this,book);
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
        title.setText("正在加载中...");
        list=findViewById(R.id.list);
        list.setOnClickListener(v->
            HttpUtils.fictionChapter(this,book,chapters)
        );
        plus=findViewById(R.id.plus);
        if(flag){
            toolbar.removeView(plus);
        }else{
            plus.setOnClickListener(v->{
                new Thread(()->{
                    MyApplication app = (MyApplication) this.getApplication();
                    AppDatabase db = app.getDatabase();
                    User user=db.userDao().get(book.getFictionId(),1);
                    if(user==null){
                        db.userDao().insertAll(new User(1,book.getFictionId(), book.getTitle(), book.getAuthor(), book.getFictionType(), book.getDescs(), book.getCover(),book.getUpdateTime(),0,"暂无章节",false));
                        user=db.userDao().get(book.getFictionId(),1);
                    }
                    user.setFlag(true);
                    db.userDao().updateAll(user);
                    runOnUiThread(()->{
                        Toast.makeText(app, "已加入书架", Toast.LENGTH_SHORT).show();
                        toolbar.removeView(plus);
                    });
                    EventBus.getDefault().post(new UpdateUIEvent());
                }).start();
            });
        }
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

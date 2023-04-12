package com.lwp.ebook.Utils;



import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lwp.ebook.Adapter.ChapterAdapter;
import com.lwp.ebook.Adapter.ClassAdapter;
import com.lwp.ebook.Adapter.ContentAdapter;
import com.lwp.ebook.Adapter.FirstAdapter;
import com.lwp.ebook.Adapter.SecondAdapter;
import com.lwp.ebook.MyApplication;
import com.lwp.ebook.R;
import com.lwp.ebook.UpdateUIEvent;
import com.lwp.ebook.model.Book;
import com.lwp.ebook.model.Chapter;
import com.lwp.ebook.model.database.AppDatabase;
import com.lwp.ebook.model.database.User;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {
    private static final String baseUrl="https://api.pingcc.cn";
    public static List<Book> fiction(final Activity activity,String option, String key,Integer adapter){
        List<Book> bookList=new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(baseUrl+"/fiction/search"+"/"+option+"/"+key)
                .get()
                .build();
        //异步请求，回调方法更新UI
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    onFailure(call, new IOException("Unexpected Code: " + response));
                } else {
                    try {
                        String responseBody = response.body().string();
                        // 解析JSON数据
                        try {
                            JSONTokener tokener = new JSONTokener(responseBody);
                            JSONObject json = (JSONObject) tokener.nextValue();
                            JSONArray data = json.getJSONArray("data");
                            Gson gson = new Gson();
                            for (int i = 0; i < data.length(); i++) {
                                Book book = gson.fromJson(data.get(i).toString(), Book.class);
                                bookList.add(book);
                            }
                            if(adapter==1){
                                activity.runOnUiThread(()->{
                                    RecyclerView recyclerView = activity.findViewById(R.id.search_result);
                                    LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
                                    recyclerView.setLayoutManager(layoutManager);
                                    SecondAdapter secondAdapter=new SecondAdapter(bookList,false,activity);
                                    recyclerView.setAdapter(secondAdapter);
                                });
                            }else if(adapter==3){
                                activity.runOnUiThread(()->{
                                    RecyclerView recyclerView = activity.findViewById(R.id.class_content_recyclerview);
                                    LinearLayoutManager layoutManager=new LinearLayoutManager(activity);
                                    recyclerView.setLayoutManager(layoutManager);
                                    SecondAdapter secondAdapter=new SecondAdapter(bookList,false,activity);
                                    recyclerView.setAdapter(secondAdapter);
                                });
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        return bookList;
    }
    public static void fictionContentByFiction(final Activity activity,Book book){
        List<Chapter> chapters=new ArrayList<>();
        OkHttpClient client=new OkHttpClient.Builder().build();
        Request request=new Request.Builder()
                .url(baseUrl+"/fictionChapter/search/"+book.getFictionId())
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    onFailure(call, new IOException("Unexpected Code: " + response));
                } else {
                    try {
                        String responseBody = response.body().string();
                        // 解析JSON数据
                        try {
                            JSONTokener tokener = new JSONTokener(responseBody);
                            JSONObject json = (JSONObject) tokener.nextValue();
                            JSONObject data = json.getJSONObject("data");
                            JSONArray chapterList = data.getJSONArray("chapterList");
                            for (int i = 0; i < chapterList.length(); i++) {
                                Chapter chapter = new Chapter();
                                chapter.setTitle(chapterList.getJSONObject(i).getString("title"));
                                chapter.setChapterId(chapterList.getJSONObject(i).getString("chapterId"));
                                chapters.add(chapter);
                            }
                            //查询用户看到了第几章
                            int position= HttpUtils.getPosition(activity,book.getFictionId(),1);
                            //再根据得到的章节id获取章节内容
                            List<String> contents=new ArrayList<>();
                            OkHttpClient client = new OkHttpClient.Builder().build();
                            Request request = new Request.Builder()
                                    .url(baseUrl+"/fictionContent/search/"+chapters.get(position).getChapterId())
                                    .get()
                                    .build();
                            client.newCall(request).enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    if (!response.isSuccessful()) {
                                        onFailure(call, new IOException("Unexpected Code: " + response));
                                    } else {
                                        try {
                                            String responseBody = response.body().string();
                                            // 解析JSON数据
                                            try {
                                                JSONTokener tokener = new JSONTokener(responseBody);
                                                JSONObject json = (JSONObject) tokener.nextValue();
                                                JSONArray data = json.getJSONArray("data");
                                                for (int i = 0; i < data.length(); i++) {
                                                    contents.add(data.get(i).toString());
                                                }
                                                activity.runOnUiThread(()->{
                                                    RecyclerView recyclerView = activity.findViewById(R.id.book_content);
                                                    LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
                                                    recyclerView.setLayoutManager(layoutManager);
                                                    ContentAdapter adapter=new ContentAdapter(contents);
                                                    recyclerView.setAdapter(adapter);
                                                    TextView title=activity.findViewById(R.id.toolbar_title);
                                                    title.setText(chapters.get(position).getTitle());
                                                });

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public static List<String> fictionContentByChapter(final Activity activity,Book book,Chapter chapter,int position){
        List<String> contents=new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(baseUrl+"/fictionContent/search/"+chapter.getChapterId())
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    onFailure(call, new IOException("Unexpected Code: " + response));
                } else {
                    try {

                        String responseBody = response.body().string();
                        // 解析JSON数据
                        try {
                            JSONTokener tokener = new JSONTokener(responseBody);
                            JSONObject json = (JSONObject) tokener.nextValue();
                            JSONArray data = json.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                contents.add(data.get(i).toString());
                            }
                            setPosition(activity,book,position,chapter.getTitle());
                            activity.runOnUiThread(()->{
                                RecyclerView recyclerView = activity.findViewById(R.id.book_content);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
                                recyclerView.setLayoutManager(layoutManager);
                                ContentAdapter adapter=new ContentAdapter(contents);
                                recyclerView.setAdapter(adapter);
                                TextView title= activity.findViewById(R.id.toolbar_title);
                                title.setText(chapter.getTitle());
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        return contents;
    }
    public static List<Chapter> fictionChapter(final Activity activity,Book book,List<Chapter> chapters){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(baseUrl+"/fictionChapter/search/"+book.getFictionId())
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    onFailure(call, new IOException("Unexpected Code: " + response));
                } else {
                    try {
                        int position=HttpUtils.getPosition(activity,book.getFictionId(),1);
                        String responseBody = response.body().string();
                        // 解析JSON数据
                        try {
                            JSONTokener tokener = new JSONTokener(responseBody);
                            JSONObject json = (JSONObject) tokener.nextValue();
                            JSONObject data = json.getJSONObject("data");
                            JSONArray chapterList = data.getJSONArray("chapterList");
                            for (int i = 0; i < chapterList.length(); i++) {
                                Chapter chapter = new Chapter();
                                chapter.setTitle(chapterList.getJSONObject(i).getString("title"));
                                chapter.setChapterId(chapterList.getJSONObject(i).getString("chapterId"));
                                chapters.add(chapter);
                            }

                            activity.runOnUiThread(()->{
                                HttpUtils.updateChapterUI(activity,book,chapters,position);
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        return chapters;
    }
    public static void fictionList(Activity activity,int userId){
        new Thread(()->{
            MyApplication app = (MyApplication) activity.getApplication();
            AppDatabase db = app.getDatabase();
            List<User> userList=db.userDao().getBookList(1);
            List<Book> bookList=new ArrayList<>();
            for(User user:userList){
                bookList.add(new Book(user.getFictionId(),user.getTitle(),user.getAuthor(),user.getFictionType(),user.getDescs(),user.getCover(),user.getUpdateTime(),user.getReadChapter()));
            }
            activity.runOnUiThread(()->{
                RecyclerView recyclerView = activity.findViewById(R.id.first_recyclerview);
                LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
                recyclerView.setLayoutManager(layoutManager);
                FirstAdapter adapter=new FirstAdapter(bookList,true,activity);
                recyclerView.setAdapter(adapter);
                TextView title=activity.findViewById(R.id.toolbar_title);
                title.setText("我的书架");
                title.setGravity(Gravity.CENTER);
                title.setWidth(1000);
            });
        }).start();
    }
    public static void updateChapterUI(Activity activity,Book book,List<Chapter> chapters,int position){
        PopupWindow popupWindow = new PopupWindow(activity);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View view=View.inflate(activity,R.layout.book_chapter,null);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        View contentView=popupWindow.getContentView();
        RecyclerView recyclerView = contentView.findViewById(R.id.book_chapters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        ChapterAdapter adapter=new ChapterAdapter(book,chapters,activity);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(position);
        popupWindow.showAtLocation(view, Gravity.LEFT, 0, 0);
    }
    public static int getPosition(Activity activity,String fictionId,int userId){
        MyApplication app = (MyApplication) activity.getApplication();
        AppDatabase db = app.getDatabase();
        User user=db.userDao().get(fictionId,userId);
        int position=0;
        if(user!=null){
            position= user.getPosition();
        }
        return position;
    }
    public static void setPosition(Activity activity,Book book,int position,String readChapter){
        MyApplication app = (MyApplication) activity.getApplication();
        AppDatabase db = app.getDatabase();
        User user=db.userDao().get(book.getFictionId(),1);
        //如果没有记录，则新增一条记录
        if(user==null){
            db.userDao().insertAll(new User(1,book.getFictionId(), book.getTitle(), book.getAuthor(), book.getFictionType(), book.getDescs(), book.getCover(),book.getUpdateTime(),position,readChapter,false));
        }else{
            System.out.println("这是打印的数据："+user.getId()+" "+position+" "+readChapter);
            db.userDao().update(user.getId(),position,readChapter);
        }
        EventBus.getDefault().post(new UpdateUIEvent());
    }
}


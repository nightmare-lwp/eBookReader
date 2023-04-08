package com.lwp.ebook.Utils;



import android.util.Log;
import com.google.gson.Gson;
import com.lwp.ebook.model.Book;
import com.lwp.ebook.model.Chapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {
    private static final String baseUrl="https://api.pingcc.cn";
    public static List<Book> fiction(String option, String key){
        List<Book> bookList=new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(baseUrl+"/fiction/search"+"/"+option+"/"+key)
                .get()
                .build();
        Call call = client.newCall(request);
//        同步请求
        Thread thread=new Thread(() -> {
            try {
                Response response = call.execute();
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
                }catch (JSONException e){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //采用阻塞主线程，等子线程执行完成后，在更新主线程上的UI
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    public static List<String> fictionContent(String chapterId){
        List<String> contents=new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(baseUrl+"/fictionContent/search/"+chapterId)
                .get()
                .build();
        Call call = client.newCall(request);
        Thread thread=new Thread(() -> {
            try {
                Response response = call.execute();
                String responseBody = response.body().string();
                // 解析JSON数据
                try {
                    JSONTokener tokener = new JSONTokener(responseBody);
                    JSONObject json = (JSONObject) tokener.nextValue();
                    JSONArray data = json.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        contents.add(data.get(i).toString());
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //采用阻塞主线程，等子线程执行完成后，在更新主线程上的UI
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return contents;
    }
    public static List<Chapter> fictionChapter(String fictionId){
        List<Chapter> chapters=new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(baseUrl+"/fictionChapter/search/"+fictionId)
                .get()
                .build();
        Call call = client.newCall(request);
        Thread thread=new Thread(() -> {
            try {
                Response response = call.execute();
                String responseBody = response.body().string();
                // 解析JSON数据
                try {
                    JSONTokener tokener = new JSONTokener(responseBody);
                    JSONObject json = (JSONObject) tokener.nextValue();
                    JSONObject data = json.getJSONObject("data");
                    JSONArray chapterList=data.getJSONArray("chapterList");
                    for (int i = 0; i<100&&i < data.length(); i++) {
                        Chapter chapter=new Chapter();
                        chapter.setTitle(chapterList.getJSONObject(i).getString("title"));
                        chapter.setChapterId(chapterList.getJSONObject(i).getString("chapterId"));
                        chapters.add(chapter);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //采用阻塞主线程，等子线程执行完成后，在更新主线程上的UI
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return chapters;
    }
}


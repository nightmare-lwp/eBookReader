package com.lwp.ebook;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.ebook.Adapter.FirstAdapter;
import com.lwp.ebook.databinding.ActivityFirstPageBinding;
import com.lwp.ebook.model.Book;

import java.util.ArrayList;
import java.util.List;

public class FirstPageActivity extends AppCompatActivity {
    private ActivityFirstPageBinding binding;
    private List<Book> bookList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initList();
        RecyclerView recyclerView = findViewById(R.id.first_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FirstAdapter adapter=new FirstAdapter(bookList,this);
        recyclerView.setAdapter(adapter);
    }
    public void initList(){
        bookList.add(new Book("05643b77-40a4-34f5-b1bb-a34013c9ac39","全职法师","乱","玄幻奇幻","　　一觉醒来，世界巨变。　　藏匿于西湖下的图腾玄蛇，屹立时如摩天大厦。　　游荡在古都城墙外的亡灵大军，它们只听从皇陵下传出的低语。　　埃及金字塔中的冥王，它和它的部众始终觊觎着东方大地！　　伦敦有着伟大的驯龙世家。　　希腊帕特农圣山上，有神女祈福。　　威尼斯被誉为水系魔法之都。　　奈斯卡巨画从沉睡中苏醒。　　贺兰山风与雨侵蚀出的岩纹，组成一只眼，山脊是眶，数万年来凝视着上苍。1557","http://api.pingcc.cn/fiction/img/全职法师/全职法师.jpg","2020-12-05 00:00:00"));
        bookList.add(new Book("53090331-b8d2-383e-8d37-9219b4fa43b3","三体（全集）","刘慈欣","科幻灵异","文化大革命如火如荼进行的同时，军方探寻外星文明的绝秘计划“红岸工程”取得了突破性进展。但在按下发射键的那一刻，历经劫难的叶文洁没有意识到，她彻底改变了人类的命运。地球文明向宇宙发出的第一声啼鸣，以太阳为中心，以光速向宇宙深处飞驰……四光年外，“三体文明”正苦苦挣扎——三颗无规则运行的太阳主导下的百余次毁灭与重生逼迫他们逃离母星。而恰在此时，他们接收到了地球发来的信息。在运用超技术锁死地球人的基础科学之后，三体人庞大的宇宙舰队开始向地球进发……人类的末日悄然来临。1w0-143340","http://api.pingcc.cn/fiction/img/三体（全集）/三体（全集）.jpg","2018-07-21 00:00:00"));
        bookList.add(new Book("327951ad-ffb9-3467-9467-868bd063a218","娱乐春秋","姬叉","历史军事","架空异界，武道百家。现代人告诉他们，除了修行，还有很多方法可以得到你想要的东西。要做江湖上人人追捧的少侠？嗯，这个简单，只是要看你的诚意……比如让你师妹来喝杯酒？子曰：穿越莫只苦修行，人家土著没你行？天作棋盘星作子，知识就是金手指。又云：穿越一世不推土，不如回家卖红薯。江山百色尽妖娆，何必较劲逆天高。-9-3585","http://api.pingcc.cn/fiction/img/娱乐春秋/娱乐春秋.jpg","2022-07-01 00:00:00"));
    }
}

package com.lwp.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.ebook.Adapter.SecondAdapter;
import com.lwp.ebook.databinding.ActivitySecondPageBinding;
import com.lwp.ebook.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SecondPageActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private List<Book> bookList1 = new ArrayList<>();
    private List<Book> bookList2 = new ArrayList<>();
    private ActivitySecondPageBinding binding;
    private Toolbar toolbar;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //顶部Bar，取消home键，设置搜索栏,去掉title
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        title = findViewById(R.id.toolbar_title);
        toolbar.removeView(title);
        ImageView list = findViewById(R.id.list);
        toolbar.removeView(list);
        ImageView plus = findViewById(R.id.plus);
        toolbar.removeView(plus);
        //装填热门小说列表
        initList();
        RecyclerView recyclerView1 = findViewById(R.id.second_recyclerview1);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 2);
        recyclerView1.setLayoutManager(gridLayoutManager1);
        SecondAdapter adapter1 = new SecondAdapter(bookList1, false, this);
        recyclerView1.setAdapter(adapter1);

        RecyclerView recyclerView2 = findViewById(R.id.second_recyclerview2);
        GridLayoutManager gridLayoutManager2=new GridLayoutManager(this,2);
        recyclerView2.setLayoutManager(gridLayoutManager2);
        SecondAdapter adapter2 = new SecondAdapter(bookList2, false, this);
        recyclerView2.setAdapter(adapter2);
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

    public void initList() {
        bookList1.add(new Book("c9dd3342-899a-3142-9acd-925a35667ba2",
                "剑来",
                "烽火戏诸侯",
                "武侠仙侠",
                "大千世界，无奇不有。我陈平安，唯有一剑，可搬山，倒海，降妖，镇魔，敕神，摘星，断江，摧城，开天！我叫陈平安，平平安安的平安。我是一名剑客。21000",
                "http://api.pingcc.cn/fiction/img/剑来/剑来.jpg",
                "2023-04-09 00:00:00","暂无章节"));
        bookList1.add(new Book("5b7c44af-3a86-331e-af6c-cbf008d4395d",
                "神秘复苏",
                "佛前献花",
                "修真小说",
                "五浊恶世，地狱已空，厉鬼复苏，人间如狱。这个世界鬼出现了......那么神又在哪里？求神救世，可世上已无神，只有鬼。——————群：623363217欢迎加入",
                "http://api.pingcc.cn/fiction/img/神秘复苏/神秘复苏.jpg",
                "2023-04-12 00:00:00","暂无章节"));
        bookList1.add(new Book("8cf9eb04-4cd1-39db-a979-fb2c06e0bdc0",
                "万相之王",
                "天蚕土豆",
                "玄幻小说",
                "天蚕土豆新书",
                "http://api.pingcc.cn/fiction/img/万相之王/万相之王.jpg",
                "2023-04-11 00:00:00","暂无章节"));
        bookList1.add(new Book("81cf0f7e-ad65-3c6e-9dd7-ec1eac5b6764",
                "宠魅",
                "鱼的天空",
                "玄幻小说",
                "楚暮的第一主宠：邪恶魂宠，靠吞噬主人的魂力成长，一旦主人的魂力无法供养，便会吃掉主人的灵魂，就像个寄生魔鬼，一直追逐着……楚幕的第二主宠：连续异变魂宠，从最低种族等级的月光狐，朝着更强大的九尾、炎君、紫帝不断的异变。楚暮的第三主宠：一只能够轻易击倒，但却永远无法击败的魂宠，越挫越勇，它总是在嘲笑与讽刺之后，震撼全场！(本站郑重提醒：本故事纯属虚构，如有雷同，纯属巧合，切勿模仿。)",
                "http://api.pingcc.cn/fiction/img/宠魅/宠魅.jpg",
                "2013-09-30 00:00:00","暂无章节"));
        bookList2.add(new Book(
                "05643b77-40a4-34f5-b1bb-a34013c9ac39",
                "全职法师",
                "乱",
                "玄幻奇幻",
                "　　一觉醒来，世界巨变。　　藏匿于西湖下的图腾玄蛇，屹立时如摩天大厦。　　游荡在古都城墙外的亡灵大军，它们只听从皇陵下传出的低语。　　埃及金字塔中的冥王，它和它的部众始终觊觎着东方大地！　　伦敦有着伟大的驯龙世家。　　希腊帕特农圣山上，有神女祈福。　　威尼斯被誉为水系魔法之都。　　奈斯卡巨画从沉睡中苏醒。　　贺兰山风与雨侵蚀出的岩纹，组成一只眼，山脊是眶，数万年来凝视着上苍。1557",
                "http://api.pingcc.cn/fiction/img/全职法师/全职法师.jpg",
                "2020-12-05 00:00:00","暂无章节"));
        bookList2.add(new Book("53090331-b8d2-383e-8d37-9219b4fa43b3",
                "三体（全集）",
                "刘慈欣",
                "科幻灵异",
                "文化大革命如火如荼进行的同时，军方探寻外星文明的绝秘计划“红岸工程”取得了突破性进展。但在按下发射键的那一刻，历经劫难的叶文洁没有意识到，她彻底改变了人类的命运。地球文明向宇宙发出的第一声啼鸣，以太阳为中心，以光速向宇宙深处飞驰……四光年外，“三体文明”正苦苦挣扎——三颗无规则运行的太阳主导下的百余次毁灭与重生逼迫他们逃离母星。而恰在此时，他们接收到了地球发来的信息。在运用超技术锁死地球人的基础科学之后，三体人庞大的宇宙舰队开始向地球进发……人类的末日悄然来临。1w0-143340",
                "http://api.pingcc.cn/fiction/img/三体（全集）/三体（全集）.jpg",
                "2018-07-21 00:00:00","暂无章节"));
        bookList2.add(new Book(
                "327951ad-ffb9-3467-9467-868bd063a218",
                "娱乐春秋",
                "姬叉",
                "历史军事",
                "架空异界，武道百家。现代人告诉他们，除了修行，还有很多方法可以得到你想要的东西。要做江湖上人人追捧的少侠？嗯，这个简单，只是要看你的诚意……比如让你师妹来喝杯酒？子曰：穿越莫只苦修行，人家土著没你行？天作棋盘星作子，知识就是金手指。又云：穿越一世不推土，不如回家卖红薯。江山百色尽妖娆，何必较劲逆天高。-9-3585", "http://api.pingcc.cn/fiction/img/娱乐春秋/娱乐春秋.jpg",
                "2022-07-01 00:00:00","暂无章节"));
        bookList2.add(new Book("453e8e2f-b7fe-354c-90c6-b31ab7aeb3cd",
                "龙族",
                "江南",
                "玄幻奇幻",
                "《龙族》讲述了衰小孩路明非，受到腰细腿长的御姐召唤，由小城市走向大洋彼岸，化身为日益成长的屠龙勇士，平凡普通的生活突然变得精彩传奇的故事。这就是《龙族》。1w16224-43943",
                "http://api.pingcc.cn/fiction/img/龙族/龙族.jpg",
                "2021-10-29 00:00:00","暂无章节"));
    }
}

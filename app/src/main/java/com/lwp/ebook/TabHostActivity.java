package com.lwp.ebook;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

import com.lwp.ebook.databinding.ActivityTabHostBinding;

public class TabHostActivity extends TabActivity implements View.OnClickListener{

    private ActivityTabHostBinding binding;
    private TabHost tabHost;
    private String TAG1 = "first";
    private String TAG2 = "second";
    private String TAG3 = "third";
    private String TAG4 = "four";
    @Override
    public void onClick(View v) {
        setTabView(v);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTabHostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.firstLinear.setOnClickListener(this);
        binding.secondLinear.setOnClickListener(this);
        binding.thirdLinear.setOnClickListener(this);
        binding.fourLinear.setOnClickListener(this);
        tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec(TAG1).setIndicator("书架", getResources().getDrawable(R.drawable.first_tab_selector)).setContent(new Intent(this,FirstPageActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAG2).setIndicator("书城", getResources().getDrawable(R.drawable.second_tab_selector)).setContent(new Intent(this,SecondPageActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAG3).setIndicator("分类", getResources().getDrawable(R.drawable.third_tab_selector)).setContent(new Intent(this,ThirdPageActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAG4).setIndicator("我的", getResources().getDrawable(R.drawable.four_tab_selector)).setContent(new Intent(this,FourPageActivity.class)));
        setTabView(binding.firstLinear);
    }
    private void setTabView(View v) {
        binding.firstLinear.setSelected(false);
        binding.secondLinear.setSelected(false);
        binding.thirdLinear.setSelected(false);
        binding.fourLinear.setSelected(false);
        v.setSelected(true);
        if (v == binding.firstLinear) {
            tabHost.setCurrentTabByTag(TAG1);
        } else if (v == binding.secondLinear) {
            tabHost.setCurrentTabByTag(TAG2);
        } else if (v == binding.thirdLinear) {
            tabHost.setCurrentTabByTag(TAG3);
        } else if (v == binding.fourLinear) {
            tabHost.setCurrentTabByTag(TAG4);
        }
    }
}

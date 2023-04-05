package com.lwp.ebook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lwp.ebook.databinding.ActivityFirstPageBinding;
import com.lwp.ebook.databinding.ActivityThirdPageBinding;

public class ThirdPageActivity extends AppCompatActivity {
    private ActivityThirdPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.thirdTextView.setText("这是【分类】页面");
    }
}

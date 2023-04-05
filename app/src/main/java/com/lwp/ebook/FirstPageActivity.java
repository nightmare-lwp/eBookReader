package com.lwp.ebook;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lwp.ebook.databinding.ActivityFirstPageBinding;

public class FirstPageActivity extends AppCompatActivity {
    private ActivityFirstPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.firstTextView.setText("这是【书架】的页面");
    }
}

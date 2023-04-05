package com.lwp.ebook;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lwp.ebook.databinding.ActivitySecondPageBinding;

public class SecondPageActivity extends AppCompatActivity {
    private ActivitySecondPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.secondTextView.setText("这是【书城】页面");
    }
}

package com.juniormargalho.projeto2020.hubsenhas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.juniormargalho.projeto2020.hubsenhas.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }
}
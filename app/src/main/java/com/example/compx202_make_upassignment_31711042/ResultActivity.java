package com.example.compx202_make_upassignment_31711042;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView tvResult = findViewById(R.id.tv_result);
        boolean success = getIntent().getBooleanExtra("success",false);
        if (success){
            tvResult.setText("you win");
        }else{
            tvResult.setText("you lost");
        }
    }
}

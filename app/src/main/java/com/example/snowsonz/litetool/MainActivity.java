package com.example.snowsonz.litetool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.snowsonz.litetool.express.ExpressActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mQueryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        startActivity(new Intent(this, ExpressActivity.class));
    }

    public void initView() {
        mQueryBtn = findViewById(R.id.btn_query);
        mQueryBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ExpressActivity.class));
    }
}

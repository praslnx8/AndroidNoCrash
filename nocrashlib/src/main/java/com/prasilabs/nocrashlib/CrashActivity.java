package com.prasilabs.nocrashlib;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CrashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);

        Button restartBtn = (Button) findViewById(R.id.restart_btn);
        Button closeBtn = (Button) findViewById(R.id.restart_btn);

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                NoCrashHandler.restartApplicationWithIntent(CrashActivity.this, new Intent());

            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoCrashHandler.closeApplication(CrashActivity.this);
            }
        });

    }
}

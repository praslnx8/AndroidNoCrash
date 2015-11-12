package com.prasilabs.nocrash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.prasilabs.nocrashlib.NoCrashHandler;

public class CustomCrashActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_crash);

        findViewById(R.id.restart_btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                NoCrashHandler.restartApplicationWithIntent(CustomCrashActivity.this, getIntent());
            }
        });

        findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                NoCrashHandler.closeApplication(CustomCrashActivity.this);
            }
        });

    }

}

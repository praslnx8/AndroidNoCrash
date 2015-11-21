package com.prasilabs.nocrash;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.prasilabs.nocrashlib.CrashReporter;
import com.prasilabs.nocrashlib.NoCrashHandler;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText emailEditText;
    private TextView emailText;
    private Button setEmailBtn;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = null;
                try {
                    msg.toString();
                } catch (NullPointerException e) {
                    NoCrashHandler.sendExceptionReportReport(e);
                }

                Snackbar.make(view, "Opps Handled Null pointer exception.. sending report..", Snackbar.LENGTH_LONG)
                        .setAction("Sent", null).show();
            }
        });

        Button button = (Button) findViewById(R.id.crash_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("Boom");
            }
        });


        emailEditText = (EditText) findViewById(R.id.edit_email_text);
        emailText = (TextView) findViewById(R.id.email_text);
        setEmailBtn = (Button) findViewById(R.id.email_button);

        setEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(emailEditText.getVisibility() == View.VISIBLE)
                {
                    if (validEmailId(emailEditText.getText().toString()))
                    {
                        emailText.setText(emailEditText.getText().toString());
                        emailEditText.setVisibility(View.GONE);
                        emailText.setVisibility(View.VISIBLE);
                        NoCrashHandler.setEmail(emailEditText.getText().toString());
                    }
                }
                else
                {
                    emailEditText.setVisibility(View.VISIBLE);
                    emailText.setVisibility(View.GONE);
                }
            }
        });

    }

    private static boolean validEmailId(String Email) {
        return Email.matches(EMAIL_PATTERN);
    }

}

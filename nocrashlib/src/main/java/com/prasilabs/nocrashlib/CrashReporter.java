package com.prasilabs.nocrashlib;

import android.os.AsyncTask;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by prasi on 7/11/15.
 */
public class CrashReporter extends AsyncTask<String, String, String>
{
    private static final String TAG = CrashReporter.class.getSimpleName();
    private static final String webUrl = "http://nocrash.netai.net/FrontEnd/crash_report.php";

    private String crashReport;
    public static String email;

    private CrashReportResultReciever crashReportResultReciever;
    private InputStream is = null;

    public static void reportCrash(String stackTrace, CrashReportResultReciever crashReportResultReciever)
    {
        Log.d(TAG, "report crash called: ");
        CrashReporter crashReporter = new CrashReporter(stackTrace, crashReportResultReciever);
        crashReporter.execute();
    }

    private CrashReporter(String crash, CrashReportResultReciever crashReportResultReciever)
    {
        this.crashReport = crash;
        this.crashReportResultReciever = crashReportResultReciever;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params)
    {
        try
        {
            URL url = new URL(webUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            Log.d(TAG, "sending: ");


            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);

            ArrayMap mParams = new ArrayMap();
            if(TextUtils.isEmpty(email))
            {
                mParams.put("email", "praslnx&gmail.com");
            }
            mParams.put("email", email);
            mParams.put("crash_report", crashReport);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(mParams));
            writer.flush();
            writer.close();
            os.close();

            Log.d(TAG, "outputstream closed: ");


            // Starts the query
            conn.connect();
            Log.d(TAG, "connected: ");

            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            Log.d(TAG, "The response message is: " + conn.getResponseMessage());



            is = conn.getInputStream(); //fOR DOWNLOADING


            // Convert the InputStream into a string
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                result.append(line);
            }

            Log.i(TAG, result.toString());
            //return result.toString();

            return null;
        }
        catch (MalformedURLException e)
        {
            Log.d(TAG, "malformed exception: ");
            e.printStackTrace();

        }
        catch (IOException e)
        {
            Log.d(TAG, "IO exception: ");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
    }


    private String getQuery(ArrayMap<String, String> mParams)
    {

        StringBuilder builder = new StringBuilder();

        for (String key : mParams.keySet())
        {
            String value = mParams.get(key);
            if (value != null)
            {
                try
                {
                    value = URLEncoder.encode(String.valueOf(value), "UTF-8");


                    if (builder.length() > 0)
                        builder.append("&");
                    builder.append(key).append("=").append(value);
                } catch (UnsupportedEncodingException e) {
                }
            }
        }

        return builder.toString();
    }

    public interface CrashReportResultReciever
    {
        void result(String response);
    }
}

package com.example.myapplication3.api;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestTask extends AsyncTask<String, Void, Void> {

    private HttpRequestMethod requestMethod;
    private Callback callback;
    private String result;
    private Exception error;

    public HttpRequestTask(HttpRequestMethod requestMethod, Callback callback) {
        this.requestMethod = requestMethod;
        this.callback = callback;
    }

    public interface Callback {
        void onSuccess(String result);

        void onFailed(Exception error);
    }

    @Override
    protected Void doInBackground(String... strings) {
        String stringUrl = strings[0];
        Log.e("HttpRequestTask", "String Url " + stringUrl);

        try {
            HttpURLConnection connection = generateHttpRequest(stringUrl);
            connection.connect();
            result = readFromResponse(connection);
            Log.e("HttpRequestTask", result+ "");

        } catch (Exception e) {
            error = e;
            Log.e("HttpRequestTask", "result error" + e);
        }

        return null;
    }

    private HttpURLConnection generateHttpRequest(String stringUrl) throws Exception {
        URL url = new URL(stringUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod.toString());
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(10000);
        return connection;
    }

    private String readFromResponse(HttpURLConnection connection) throws IOException {
        String inputLine;

        //Create a new InputStreamReader
        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

        //Create a new buffered reader and String Builder
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();

        //Check if the line we are reading is not null
        while ((inputLine = bufferedReader.readLine()) != null){
            stringBuilder.append(inputLine);
        }

        bufferedReader.close();
        inputStreamReader.close();
        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (result != null){
            callback.onSuccess(result);
        }

        if (error != null){
            callback.onFailed(error);
        }
    }
}

package com.example.myapplication3.waitscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication3.signinscreen.SignInActivity;
import com.example.myapplication3.R;

public class WaitScreenActivity extends AppCompatActivity {

    public static final int REQUEST_WRITE_STORAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_wait_screen);
        checkPermission();
        initView();
        setAction();

        registerForContextMenu(title1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint({"ResourceAsColor", "NonConstantResourceId"})
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.line1:
                title1.setTextColor(R.color.blue);
                break;
            case R.id.line2:
                title1.setTextColor(R.color.black);
                break;

            case R.id.line3:
                title1.setTextColor(R.color.orange);
                break;

            case R.id.line4:
                title1.setTextColor(R.color.light_blue);
                break;

            case R.id.line5:
                title1.setTextColor(R.color.hint_blue);
                break;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.line2:
                title1.setText("2");
            case R.id.line3:
                title1.setText("3");
            case R.id.line4:
                title1.setText("4");
            case R.id.line5:
                title1.setText("5");
        }
        return super.onOptionsItemSelected(item);
    }

    private ImageView next;
    private TextView title1;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void setAction() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WaitScreenActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        next = findViewById(R.id.next);
        title1 = findViewById(R.id.title_1);
    }

    private boolean hasStoragePermission() {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void checkPermission() {
        boolean hasStoragePermission = hasStoragePermission();
        if (hasStoragePermission) {
            //To do something
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_STORAGE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //To do something
        }
    }
}
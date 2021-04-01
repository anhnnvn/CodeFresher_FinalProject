package com.example.myapplication3.manager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

public class PermissionManager {
    public static final int WRITE_EXTERNAL_STORAGE = 1;
    public static final int ACCESS_FINE_LOCATION = 2;

    private boolean hasStoragePermission(Activity activity) {
        return (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasLocationPermission(Activity activity) {
        return (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }


}

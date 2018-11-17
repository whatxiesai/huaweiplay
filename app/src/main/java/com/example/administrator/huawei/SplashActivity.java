package com.example.administrator.huawei;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_STORAGE = 1;

    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        sp = getSharedPreferences("appStore", Context.MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (!isFirst) {
            goHome();
        }
        verifyStoragePermission(this);
    }

    @OnClick(R.id.enter_button)
    public void goHome() {
        sp.edit().putBoolean("isFirst", false).commit();
        startActivity(new Intent(this, BaseActivity.class));
        finish();
    }

    private void verifyStoragePermission(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PermissionChecker.PERMISSION_GRANTED) {
            // 没有权限，弹出对话框
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_CODE_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
            // 申请权限成功
            Toast.makeText(this, "授权SD卡权限成功", Toast.LENGTH_SHORT);
        } else {
            // 申请权限失败
            Toast.makeText(this, "授权SD卡权限失败，可能会影响用户的使用", Toast.LENGTH_SHORT);
        }
    }
}

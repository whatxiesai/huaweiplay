package com.example.administrator.huawei;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.view.View;
import android.widget.Button;

import com.example.administrator.huawei.mvp.view.HomeActivity;
import com.example.administrator.huawei.util.ToastUtils;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.enter_button)
    Button btn_go ;

    private SharedPreferences sp = null ;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initView() {

        sp = getSharedPreferences("appStore",Context.MODE_PRIVATE);

        if(!sp.getBoolean("isFirst",true)){
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }


        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putBoolean("isFirst",false).commit() ;
                initPermission();
            }
        });
    }

    private void initPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED){
                startActivity(new Intent(this,HomeActivity.class));
                finish();
            }else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PermissionChecker.PERMISSION_GRANTED){
            ToastUtils.showToast("授权SD卡成功");

        }else {
            ToastUtils.showToast("没有授权SD卡，可能会影响应用的使用");
        }
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }
}

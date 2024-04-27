package com.llw.easylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "len";
    private EditText edit;
    private ProgressBar progr;
    private ProgressBar progr2;
    private NotificationManager manager;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        TextView tv_one = findViewById(R.id.tv_one);
        tv_one.setText("Leo");
        //获取输入框
//        EditText edit=findViewById(R.id.edit);
        //变为全局变量
        edit = findViewById(R.id.edit);
//获取进度条
        progr = findViewById(R.id.progressBar);
        //水平进度条
        progr2 = findViewById(R.id.progressBar2);
        Button tv_button = findViewById(R.id.button);

        //通知
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //判断安卓版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel Channel = new NotificationChannel("leo", "测试通知", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(Channel);
        }

        notification = new NotificationCompat.Builder(this, "leo")
                .setContentTitle("官方通知")
                .setContentText("世界那么棒")
                .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
                .build();
        //点击事件
//        tv_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e(TAG, "onClick: ");
//            }
//        });
        //长按事件
        tv_button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.e(TAG, "onLongClick: ");
                return false;
            }
        });
        //触摸事件
        tv_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e(TAG, "onTouch: " + motionEvent.getAction());
                return false;//如果为true 则点击和长按不执行
            }
        });

    }

    //点击方法
    public void leoclick(View view) {
        String ed = edit.getText().toString();
        Log.e(TAG, "leoclick: " + ed);
        //进度  如果是隐藏就让他显示,否则就隐藏
        if (progr.getVisibility() == View.GONE) {
            progr.setVisibility(View.VISIBLE);
        } else {
            progr.setVisibility(View.GONE);
        }
        //水平进度条
        int pross = progr2.getProgress();
        pross += 10;
        progr2.setProgress(pross);
    }

    //发出通知
    public void leoclicks(View view) {
        manager.notify(1, notification);
    }

    //取消通知
    public void leoclicksq(View view) {
        manager.cancel(1);
    }

    //对话框
    public void myale(View view) {
        View dialogview = getLayoutInflater().inflate(R.layout.dialog_view, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("我是对话框")
                .setMessage("今天天气怎么样啊")
                .setView(dialogview)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e(TAG, "onClick: 确定");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e(TAG, "onClick: 取消");
                    }
                })
                .setNeutralButton("中间", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e(TAG, "onClick: 中间");
                    }
                })
                .create()
                .show();
    }

    public void xiala(View view) {
        View popupview = getLayoutInflater().inflate(R.layout.dialog_view, null);
        //ViewGroup.LayoutParams.WRAP_CONTENT,java 的宽高
        PopupWindow popupWindow = new PopupWindow(popupview, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.showAsDropDown(view);//显示在按钮下方
        popupWindow.showAsDropDown(view,view.getWidth(),100);//显示偏移
    }
}
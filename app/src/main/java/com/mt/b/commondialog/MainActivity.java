package com.mt.b.commondialog;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mt.b.dialog.ui.MessageDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageDialog();
            }
        });
    }

    private void showMessageDialog() {
        MessageDialog.Builder builder = new MessageDialog.Builder(MainActivity.this)
                .setTitle("温馨提示")
                .setTitleColor(R.color.colorAccent)
                .setTitleSize(12)
                .setMessageColor(R.color.colorPrimary)
                .setMessageSize(10)
                .setNegativeButtonTextSize(10)
                .setPositionButtonTextSize(10)
                .setCanceledOnTouchOutside(false)
                .setMessage("除却君身三尺雪，天下谁人配白衣")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        builder.showDialog();
    }
}

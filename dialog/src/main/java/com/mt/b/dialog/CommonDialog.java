package com.mt.b.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.app.AlertDialog;

/**
 * Author : ZSK
 * Date : 2019/1/29
 * Description :  创建者模式
 */
public class CommonDialog {

    public static class Builder {
        private Context mContext;
        private int mDialogType = 1;    //默认消息对护框
        private int mTitleSize = 13;    //标题字体大小
        private String mTitle = "提示";    //标题
        private int titleColor ;    //标题颜色
        private int cannelColor;   //取消按钮颜色
        private int confirmColor;    //确定按钮颜色

        public Builder(Context context) {
            this.mContext = context;
            titleColor = Color.parseColor("#000000");

        }


        //设置对话框类型 消息提示，输入框，选择对话框
        public Builder setDialogType(int dialogType) {
            this.mDialogType = dialogType;
            return this;
        }

        //设置标题
        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        //设置标题大小
        public Builder setTitleSize(int size) {
            this.mTitleSize = size;
            return this;
        }

        public Builder setTitleColor(@ColorRes int color){
            return this;
        }

    }
}

package com.mt.b.dialog.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.mt.b.dialog.CommonDialog;
import com.mt.b.dialog.R;

/**
 * Author : ZSK
 * Date : 2019/1/30
 * Description :  简单信息提示框
 */
public class MessageDialog {

    public static class Builder {

        private Context mContext;
        private Dialog mDialog;
        private View mView;
        private ViewHolder mViewHolder;

        private boolean isHavaCannelListener = false;    //默认没有取消监听
        private boolean isHaveConfirmListener = false;   //默认没有确定监听

        public Builder(Activity context) {
            this.mContext = context;
            initView();
        }

        //设置标题
        public Builder setTitle(CharSequence title) {
            mViewHolder.title.setText(title);
            return this;
        }

        //设置标题颜色
        public Builder setTitleColor(@ColorRes int color) {
            mViewHolder.title.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        //设置标题字体大小
        public Builder setTitleSize(int size) {
            mViewHolder.title.setTextSize(size);
            return this;
        }

        //设置内容
        public Builder setMessage(CharSequence message) {
            mViewHolder.message.setText(message);
            return this;
        }

        //设置内容字体大小
        public Builder setMessageSize(int size) {
            mViewHolder.message.setTextSize(size);
            return this;
        }

        //设置标题内容颜色
        public Builder setMessageColor(@ColorRes int color) {
            mViewHolder.message.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        //设置确定按钮
        public Builder setPositiveButton(CharSequence text, final View.OnClickListener listener) {
            mViewHolder.positiveButton.setText(text);
            if (listener != null) {
                isHaveConfirmListener = true;
                mViewHolder.positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        listener.onClick(v);
                    }
                });
            }
            return this;
        }

        //设置取消按钮
        public Builder setNegativeButton(CharSequence text, final View.OnClickListener listener) {
            mViewHolder.negativeButton.setText(text);
            if (listener != null) {
                isHavaCannelListener = true;
                mViewHolder.negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        listener.onClick(v);
                    }
                });
            }
            return this;
        }

        //消单个消息提示的按钮和监听
        public Builder setPromitButton(CharSequence text, final View.OnClickListener listener) {
            mViewHolder.negativeButtonOne.setText(text);
            mViewHolder.negativeButtonOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mDialog.dismiss();
                        listener.onClick(v);
                    }
                }
            });
            return this;
        }


        //设置确定按钮的字体大小
        public Builder setPositionButtonTextSize(int size) {
            mViewHolder.positiveButton.setTextSize(size);
            return this;
        }

        //设置确定按钮的字体颜色
        public Builder setPositionButtonTextColor(@ColorRes int color) {
            mViewHolder.positiveButton.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        //设置取消按钮字体大小
        public Builder setNegativeButtonTextSize(int size) {
            mViewHolder.negativeButton.setTextSize(size);
            return this;
        }

        //设置取消按钮的字体颜色
        public Builder setNegativeButtonTextColor(@ColorRes int color) {
            mViewHolder.negativeButton.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        //设置单个按钮模式下的字体大小
        public Builder setPromitButtonTextSize(int size) {
            mViewHolder.negativeButtonOne.setTextSize(size);
            return this;
        }

        //设置单个按钮模式下的字体颜色
        public Builder setPromitButtonTextColor(@ColorRes int color) {
            mViewHolder.negativeButtonOne.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        //对话框消失
        public Builder setCancelable(boolean flag) {
            mDialog.setCancelable(flag);
            return this;
        }


        //设置点击外边消失
        public Builder setCanceledOnTouchOutside(boolean flag) {
            mDialog.setCanceledOnTouchOutside(flag);
            return this;
        }

        //显示对话框
        public void showDialog() {
            if (mDialog != null) {
                setDialogStyle();
                mDialog.show();
            }

        }

        private void setDialogStyle() {
            if (isHavaCannelListener && isHaveConfirmListener) {
                mViewHolder.splitVertical.setVisibility(View.VISIBLE);
                mViewHolder.negativeButtonOne.setVisibility(View.GONE);
                mViewHolder.negativeButton.setVisibility(View.VISIBLE);
                mViewHolder.positiveButton.setVisibility(View.VISIBLE);
            } else {
                mViewHolder.splitVertical.setVisibility(View.GONE);
                mViewHolder.negativeButtonOne.setVisibility(View.VISIBLE);
                mViewHolder.negativeButton.setVisibility(View.GONE);
                mViewHolder.positiveButton.setVisibility(View.GONE);
            }
        }


        private void initView() {
            mDialog = new Dialog(mContext, R.style.messageDialogStyle);
            mView = LayoutInflater.from(mContext).inflate(R.layout.layout_message_dialog, null);
            mViewHolder = new ViewHolder(mView);
            mDialog.setContentView(mView);

            DisplayMetrics dm = new DisplayMetrics();   //获取屏幕大小
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);//获取WindowManager
            windowManager.getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
            lp.width = (int) (dm.widthPixels * 0.5);
            mDialog.getWindow().setAttributes(lp);
        }

        class ViewHolder {
            TextView title;    //标题
            TextView message;   //内容
            TextView positiveButton, negativeButton;   //取消、确定按钮
            TextView negativeButtonOne;   //只有一个按钮
            View splitVertical;   //竖直分割线

            public ViewHolder(View view) {
                title = view.findViewById(R.id.dialog_title);
                message = view.findViewById(R.id.dialog_message);
                positiveButton = view.findViewById(R.id.dialog_positive);
                negativeButton = view.findViewById(R.id.dialog_negative);

                negativeButtonOne = view.findViewById(R.id.dialog_positive_one);
                splitVertical = view.findViewById(R.id.split_vertical);
            }
        }
    }
}

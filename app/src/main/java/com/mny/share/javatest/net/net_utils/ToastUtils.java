package com.mny.share.javatest.net.net_utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast;

    /**
     * String类型的
     *
     * @param mContext
     * @param message
     */
    public static void show(Context mContext, String message) {

        if (null == toast) {
            toast = Toast.makeText(mContext.getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * String类型的
     *
     * @param mContext
     * @param id
     */
    public static void show(Context mContext, int id) {
        if (null == toast) {
            toast = Toast.makeText(mContext.getApplicationContext(), mContext.getResources().getString(id), Toast.LENGTH_SHORT);
        } else {
            toast.setText(mContext.getResources().getString(id));
        }
        toast.show();
    }


}
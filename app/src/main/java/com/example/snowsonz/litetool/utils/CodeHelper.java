package com.example.snowsonz.litetool.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.Collection;
import java.util.List;

/**
 * author: SnowsonZ
 * created on: 2018/5/7 22:52
 * description:
 */
public class CodeHelper {
    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void showToast(Context context, String content, int type) {
        if (content == null || context == null) {
            return;
        }
        Toast.makeText(context, content, type).show();
    }

    public static void showToast(Context context, String content) {
        showToast(context, content, Toast.LENGTH_SHORT);
    }
}

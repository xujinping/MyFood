package com.xjp.myapp.utils;

import com.xjp.myapp.activity.CategoryActivity;
import com.xjp.myapp.beans.Category.List;
import com.xjp.myapp.beans.Category.Result;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import java.io.Serializable;

/**
 * Description: 类别对话框
 * User: xjp
 * Date: 2015/3/11
 * Time: 16:17
 */
public class ShowDialog {

    /**
     * 显示类别对话框
     * @param context
     * @param result
     */
    public static void showCategoryDialog(final Context context, final Result result) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(result.getName());
        final java.util.List<List> list = result.getList();
        int i = list.size();
        String[] items = new String[i];
        for (int j = 0; j < i; j++) {
            items[j] = list.get(j).getName();
        }
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(context, list, which);
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    /**
     *  启动 详细分类acivity
     * @param context
     * @param list
     * @param which
     */
    private static void startActivity(Context context, java.util.List<List> list, int which) {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra(Key.CATEGORY, (Serializable) list);
        intent.putExtra(Key.WHICH, which);
        context.startActivity(intent);
    }

}

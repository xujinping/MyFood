package com.xjp.myapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;


import com.xjp.myapp.activity.CategoryActivity;
import com.xjp.myapp.beans.Category.List;
import com.xjp.myapp.beans.Category.Result;
import com.xjp.myapp.R;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/11
 * Time: 16:17
 */
public class ShowDialog {

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
                startActivity(context, (list.get(which).getId()), list.get(which).getName());
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private static void startActivity(Context context, String cid, String name) {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra(Key.CID, cid);
        intent.putExtra(Key.CATEGORY, name);
        context.startActivity(intent);
    }

}

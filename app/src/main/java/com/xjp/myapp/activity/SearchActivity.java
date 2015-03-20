package com.xjp.myapp.activity;

import com.xjp.myapp.R;
import com.xjp.myapp.base.BaseActivity;
import com.xjp.myapp.utils.Key;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Description: 菜谱搜索页面
 * User: xjp
 * Date: 2015/3/19
 * Time: 16:44
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private EditText content;

    private Button btnSearch;

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        content = (EditText) findViewById(R.id.edt_search);
        btnSearch = (Button) findViewById(R.id.btn_search1);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search1:
                String msg = content.getText().toString();
                startActivity(msg);
                break;
        }
    }

    /**
     * 跳转到搜索结果页面
     */
    private void startActivity(String msg) {
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra(Key.NAME, msg);
        startActivity(intent);
    }
}

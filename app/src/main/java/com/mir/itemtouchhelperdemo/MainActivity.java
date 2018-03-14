package com.mir.itemtouchhelperdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        initAdapter();
    }

    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(getData());
        mRecyclerView.setAdapter(mAdapter);

        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelper(mAdapter);
        //用Callback 构造 ItemTouchHelper
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView 跟 RecyclerView 建立关系
        helper.attachToRecyclerView(mRecyclerView);
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("item " + i);
        }
        return list;
    }
}

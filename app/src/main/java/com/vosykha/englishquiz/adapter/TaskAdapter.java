package com.vosykha.englishquiz.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vosykha.englishquiz.ListDatabase;
import com.vosykha.englishquiz.TQuestions;

import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private ListDatabase context;
    private int layout;
    private List<TQuestions> taskList;

    public TaskAdapter(ListDatabase context, int layout, List<TQuestions> taskList) {
        this.context = context;
        this.layout = layout;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
             }
}

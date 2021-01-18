package com.example.taskplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.taskplanner.models.TaskInfo;

import java.util.ArrayList;

public class CustomAdapterList extends BaseAdapter {

    ArrayList<TaskInfo> arrayList;
    TextView title;
    //TextView date;

    public CustomAdapterList(ArrayList<TaskInfo> arrayList) {
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklist_model,null);

        title = convertView.findViewById(R.id.task_title);

        TaskInfo taskInfo = (TaskInfo) getItem(position);

        title.setText(taskInfo.getTitle());


        return convertView;
    }
}

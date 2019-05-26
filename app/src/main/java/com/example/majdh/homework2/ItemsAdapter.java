package com.example.majdh.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsAdapter extends BaseAdapter {

    LayoutInflater myInflater;
    ArrayList<String> answers;

    public ItemsAdapter(Context c, ArrayList<String> a)
    {
        myInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.answers = a;
    }

    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int position) {
        return answers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = myInflater.inflate(R.layout.answers_listview_row, null);
        TextView answer = (TextView)v.findViewById(R.id.answer_row);
        answer.setText(answers.get(position));
        return v;
    }
}

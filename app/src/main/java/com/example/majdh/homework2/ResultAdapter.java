package com.example.majdh.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultAdapter extends BaseAdapter {

    LayoutInflater myInflater;
    ArrayList<Question> questions;
    ArrayList<Integer> collectedAnswers;

    public ResultAdapter(Context c, ArrayList<Question> q, ArrayList<Integer> ca)
    {
        myInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.questions = q;
        this.collectedAnswers = ca;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = myInflater.inflate(R.layout.answers_table_listview_row, null);
        TextView questionNum = (TextView)v.findViewById(R.id.questionNum);
        TextView questionContent = (TextView)v.findViewById(R.id.questionContent);
        TextView yourAnswer = (TextView)v.findViewById(R.id.yourAnswer);
        TextView correctAnswer = (TextView)v.findViewById(R.id.correctAnswer);
        questionNum.setText(""+questions.get(position).getQuestionNum());
        questionContent.setText(questions.get(position).getQuestionContent());
        yourAnswer.setText(questions.get(position).getAnswer(collectedAnswers.get(position)));
        correctAnswer.setText(questions.get(position).getCorrectAnswer());
        return v;
    }
}

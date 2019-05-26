package com.example.majdh.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ArrayList<Question> questions;
    private ArrayList<Integer> collectedAnswers;
    private TextView grade;
    private ListView resultTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        grade = (TextView)findViewById(R.id.gradeTextView);
        resultTable = (ListView)findViewById(R.id.answersTableListView);
        questions = (ArrayList<Question>) getIntent().getSerializableExtra("questions");
        collectedAnswers = (ArrayList<Integer>) getIntent().getSerializableExtra("collectedAnswers");

        grade.setText((int)Math.round(calculateGrade())+"%");

        ResultAdapter resultTableAdapter = new ResultAdapter(this, questions, collectedAnswers);
        resultTable.setAdapter(resultTableAdapter);
    }

    private float calculateGrade()
    {
        float result = 0;
        for(int i=0; i<questions.size(); i++)
        {
            if(collectedAnswers.get(i) == questions.get(i).getIndexOfCurrentAnswer())
                result++;
        }
        return (result/questions.size())*100;
    }
}

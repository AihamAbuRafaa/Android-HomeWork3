package com.example.majdh.homework2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import cz.msebera.android.httpclient.Header;

public class quizActivity extends AppCompatActivity{

    private TextView questionNum;
    private TextView questionContent;
    private ListView answersListView;
    private ArrayList<Question> questions;
    private int currQuestion;
    private ArrayList<Integer> collectedAnswers;
    private int questions_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionNum = (TextView)findViewById(R.id.questionNum);
        questionContent = (TextView)findViewById(R.id.questionContent);
        answersListView = (ListView)findViewById(R.id.answers);
        questions = new ArrayList<Question>();
        currQuestion = 0;
        collectedAnswers = new ArrayList<Integer>();

        //questions
        questions_number = getIntent().getIntExtra("questions_number", 1);
        Toast toast = Toast.makeText(getApplicationContext(), "Fetching "+ questions_number +" questions...", Toast.LENGTH_SHORT);
        toast.show();
        addQuestions(questions_number);

        //answer onclick
        answersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                collectedAnswers.add(position);
                if(currQuestion < questions.size()-1)
                {
                    currQuestion++;
                }
                else {
                    Intent resultPage = new Intent(quizActivity.this, ResultActivity.class);
                    resultPage.putExtra("questions", questions);
                    resultPage.putExtra("collectedAnswers", collectedAnswers);
                    startActivity(resultPage);
                }
                showQuestion(questions.get(currQuestion));
            }
        });

    }

    private void addQuestions(int number) {
        String url = "https://opentdb.com/api.php?amount="+number;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                try {
                    JSONArray JA = response.getJSONArray("results");
                    for(int i=0; i<JA.length(); i++)
                    {
                        ArrayList<String> answers = new ArrayList<String>();

                        String question = JA.getJSONObject(i).getString("question");
                        question = Html.fromHtml(question).toString();
                        String correctAnswer = JA.getJSONObject(i).getString("correct_answer");
                        answers.add(correctAnswer);
                        JSONArray incorrectAnswers = JA.getJSONObject(i).getJSONArray("incorrect_answers");
                        for(int j=0; j<incorrectAnswers.length(); j++)
                            answers.add(incorrectAnswers.getString(j));
                        Collections.shuffle(answers);
                        Question q = new Question(i+1, question);
                        q.setAnswers(answers);
                        q.setIndexOfCurrentAnswer(answers.indexOf(correctAnswer));
                        questions.add(q);
                    }
                    showQuestion(questions.get(currQuestion));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showQuestion(Question q)
    {
        ArrayList<String> answers = q.getAnswers();
        questionNum.setText("Question number " + q.getQuestionNum());
        questionContent.setText(q.getQuestionContent());
        ItemsAdapter answersAdapter = new ItemsAdapter(this, answers);
        answersListView.setAdapter(answersAdapter);
    }

}

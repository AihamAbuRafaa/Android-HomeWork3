package com.example.majdh.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startQuiz(View view)
    {
        Intent startQuiz = new Intent(this, quizActivity.class);
        EditText questionsNumber = findViewById(R.id.questionsNumET);
        if(questionsNumber.getText().toString().length() == 0)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Please insert questions number!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        int qNumber = Integer.parseInt(questionsNumber.getText().toString());
        if(qNumber < 1 || qNumber > 50)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Questions number must be between 1 to 50!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        startQuiz.putExtra("questions_number", qNumber);
        startActivity(startQuiz);
    }
}

package com.example.majdh.homework2;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable
{
    private int questionNum;
    private String questionContent;
    private ArrayList<String> answers;
    private int indexOfCurrentAnswer;

    public Question()
    {
        answers = new ArrayList<String>();
    }

    public Question(int qn, String qc)
    {
        questionNum = qn;
        questionContent = qc;
        answers = new ArrayList<String>();
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public void add_answer(String a)
    {
        answers.add(a);
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setIndexOfCurrentAnswer(int indexOfCurrentAnswer) {
        this.indexOfCurrentAnswer = indexOfCurrentAnswer;
    }

    public int getIndexOfCurrentAnswer() {
        return indexOfCurrentAnswer;
    }

    public String getCorrectAnswer()
    {
        return answers.get(indexOfCurrentAnswer);
    }

    public String getAnswer(int index)
    {
        return answers.get(index);
    }
}

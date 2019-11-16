package com.worldtechpoints.bcsknowledge.Question;

public class QuestionSubmit {

    private String mTime;
    private String mQuestion;
    private String mAns;

    public QuestionSubmit() {}


    public QuestionSubmit(String mTime, String mQuestion, String mAns) {
        this.mTime = mTime;
        this.mQuestion = mQuestion;
        this.mAns = mAns;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmAns() {
        return mAns;
    }

    public void setmAns(String mAns) {
        this.mAns = mAns;
    }
}

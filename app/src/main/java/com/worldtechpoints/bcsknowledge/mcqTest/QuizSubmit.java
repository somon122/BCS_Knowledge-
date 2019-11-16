package com.worldtechpoints.bcsknowledge.mcqTest;

public class QuizSubmit {

    String mTime;
    String mQuizQuestion;
    String mQuizFirstOption;
    String mQuizSecondOption;
    String mQuizThirdOption;
    String mQuizFourthOption;
    String mQuizCorrectAns;

    public QuizSubmit() {}

    public QuizSubmit(String mTime, String mQuizQuestion, String mQuizFirstOption,
                      String mQuizSecondOption, String mQuizThirdOption, String mQuizFourthOption, String mQuizCorrectAns) {
        this.mTime = mTime;
        this.mQuizQuestion = mQuizQuestion;
        this.mQuizFirstOption = mQuizFirstOption;
        this.mQuizSecondOption = mQuizSecondOption;
        this.mQuizThirdOption = mQuizThirdOption;
        this.mQuizFourthOption = mQuizFourthOption;
        this.mQuizCorrectAns = mQuizCorrectAns;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmQuizQuestion() {
        return mQuizQuestion;
    }

    public void setmQuizQuestion(String mQuizQuestion) {
        this.mQuizQuestion = mQuizQuestion;
    }

    public String getmQuizFirstOption() {
        return mQuizFirstOption;
    }

    public void setmQuizFirstOption(String mQuizFirstOption) {
        this.mQuizFirstOption = mQuizFirstOption;
    }

    public String getmQuizSecondOption() {
        return mQuizSecondOption;
    }

    public void setmQuizSecondOption(String mQuizSecondOption) {
        this.mQuizSecondOption = mQuizSecondOption;
    }

    public String getmQuizThirdOption() {
        return mQuizThirdOption;
    }

    public void setmQuizThirdOption(String mQuizThirdOption) {
        this.mQuizThirdOption = mQuizThirdOption;
    }

    public String getmQuizFourthOption() {
        return mQuizFourthOption;
    }

    public void setmQuizFourthOption(String mQuizFourthOption) {
        this.mQuizFourthOption = mQuizFourthOption;
    }

    public String getmQuizCorrectAns() {
        return mQuizCorrectAns;
    }

    public void setmQuizCorrectAns(String mQuizCorrectAns) {
        this.mQuizCorrectAns = mQuizCorrectAns;
    }
}

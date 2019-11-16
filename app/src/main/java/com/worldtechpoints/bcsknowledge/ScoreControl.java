package com.worldtechpoints.bcsknowledge;

public class ScoreControl {

    private int score;
    private int mainScore;

    public ScoreControl() {
    }



    public int getMainScore() {
        return mainScore;
    }

    public void setMainScore(int mainScore) {
        this.mainScore = mainScore;
    }
    public void AddMainScore(int amount){

        mainScore += amount;

    }
    public void WithdrawMainScore(int amount) {

        if (getMainScore() - amount >= 0) {
            score -= amount;
        }

    }


        public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }




    public void WithdrawScore(int amount){

        if (getScore() - amount >=0){
            score -= amount;
        }

    }
    public void AddScore(int amount){

        score += amount;


    }


}

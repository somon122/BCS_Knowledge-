package com.worldtechpoints.bcsknowledge;

import android.content.Context;
import android.content.SharedPreferences;

public class ControlClass {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Context context;

    public ControlClass(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("mainScore",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setStoreScore (int score){
        editor.putInt("score",score);
        editor.commit();

    }
    public int getScore(){
        int score = sharedPreferences.getInt("score",0);
        return score;


    }

    public void Delete(){
        editor.clear();
        editor.commit();

    }


}

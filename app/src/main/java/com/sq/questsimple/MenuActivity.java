package com.sq.questsimple;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MenuActivity extends Activity {

    int currentExp;
    int levelNum;
    int levelExp;
    int totalClicks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("MyPres", MODE_PRIVATE);


        levelNum = sharedPref.getInt("level",1);
        levelExp = sharedPref.getInt("levelExp",0);
        currentExp = sharedPref.getInt("currentExp",0);
        totalClicks = sharedPref.getInt("totalClicks",0);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        TextView t2 = findViewById(R.id.test);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
        ReplaceFont.replaceDefaultFont(this, "DEFAULT", "orange.ttf");
    }



    public void onClickReturn(View v){
        Intent myIntent = new Intent(this,MainActivity.class);
        startActivity(myIntent);
    }

    public void onClickStats(View v){
        Intent myIntent = new Intent(this,StatsActivity.class);
        startActivity(myIntent);
    }
    public void onClickCraft(View v){
        Intent myIntent = new Intent(this,CraftActivity.class);
        startActivity(myIntent);
    }

    public void onClickAchievements(View v){
        Intent myIntent = new Intent(this,AchievementActivity.class);
        startActivity(myIntent);
    }


}

package com.sq.questsimple;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StatsActivity extends Activity {

    int currentExp;
    int levelNum;
    int levelExp;
    int totalClicks;
    int dropsTotal;
    int bossTotal;
    long gameTime;
    long currentTime;

    ListView statsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences("MyPres", MODE_PRIVATE);
        levelNum = sharedPref.getInt("level", 1);
        dropsTotal = sharedPref.getInt("dropsTotal",0);
        levelExp = sharedPref.getInt("levelExp", 0);
        currentExp = sharedPref.getInt("currentExp", 0);
        totalClicks = sharedPref.getInt("totalClicks", 0);
        gameTime = sharedPref.getLong("totalTime", 0);
        currentTime = sharedPref.getLong("currentTime",0);
        bossTotal = sharedPref.getInt("bossTotal", 0);

        int timeInt = (int) gameTime;
        int secondsTotal = timeInt/1000;
        int secondsTotalShow = secondsTotal%60;

        int minutesTotal = secondsTotal/60;
        int minutesTotalShow = minutesTotal%60;

        int hourTotal = minutesTotal/60;
        int hourTotalShow = hourTotal%24;

        int dayTotalShow = hourTotal/24;

        String time = dayTotalShow + ":" + hourTotalShow + ":"  + minutesTotalShow + ":" + secondsTotalShow;

        int timeCurrentInt = (int) currentTime;
        int seconds = timeCurrentInt/1000;
        int secondsShow = seconds%60;

        int minutes = seconds/60;
        int minutesShow = minutes%60;

        int hour = minutes/60;
        int hourShow = hour%24;

        int dayShow = hour/24;
        String timeCurrent = dayShow + ":" + hourShow + ":"  + minutesShow + ":" + secondsShow;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stats);

        ReplaceFont.replaceDefaultFont(this, "DEFAULT", "orange.ttf");

        String[] values = {"Total Clicks: " + totalClicks,
                "Play Time: " + time,
                "Current Session Time: " + timeCurrent,
                "Total Loot Bags: " + dropsTotal,
                "Boss Kills: " + bossTotal
        };
        statsList = (ListView) findViewById(R.id.listerView);
        ArrayAdapter arrayAdapter = new ExampleAdapter(this, android.R.layout.simple_list_item_1, values);
        this.statsList.setAdapter(arrayAdapter);
    }
    public void onMenuClick(View v){
        Intent myIntent = new Intent(this,MenuActivity.class);
        startActivity(myIntent);
    }

}

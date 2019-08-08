package com.sq.questsimple;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class AchievementActivity extends Activity {

    int levelNum;
    int str;
    int dex;
    int intt;
    int wepStr;
    int armDex;
    int spellIntt;
    TextView charLevel;
    TextView charStr;
    TextView charDex;
    TextView charInt;
    TextView charWep;
    TextView charArm;
    TextView charSpell;
    String wep;
    String arm;
    String spell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_achievement);

        ReplaceFont.replaceDefaultFont(this, "DEFAULT", "orange.ttf");

        SharedPreferences sharedPref = getSharedPreferences("MyPres", MODE_PRIVATE);
        levelNum = sharedPref.getInt("level",1);
        str = sharedPref.getInt("str",1);
        dex = sharedPref.getInt("dex", 1);
        intt = sharedPref.getInt("intt", 1);
        wep = sharedPref.getString("wep", "None");
        arm = sharedPref.getString("arm", "None");
        spell = sharedPref.getString("spell", "None");
        wepStr =sharedPref.getInt("wepStr",0);
        armDex = sharedPref.getInt("armDex",0);
        spellIntt = sharedPref.getInt("spellIntt",0);


        charLevel = (TextView)findViewById(R.id.charLevelShow);
        charLevel.setText("Level: " + levelNum);
        charStr = (TextView)findViewById(R.id.charStr);
        charStr.setText("Strength: " + (str+wepStr));
        charDex = (TextView)findViewById(R.id.charDex);
        charDex.setText("Dexterity: " + (dex+armDex));
        charInt = (TextView)findViewById(R.id.charInt);
        charInt.setText("Intelligence: " + (intt+spellIntt));
        charWep = (TextView)findViewById(R.id.charWep);
        charWep.setText("Weapon: " + wep);
        charArm = (TextView)findViewById(R.id.charArm);
        charArm.setText("Armor: " + arm);
        charSpell = (TextView)findViewById(R.id.charSpell);
        charSpell.setText("Spell: " + spell);
    }

    public void onMenuClick(View v){
        Intent myIntent = new Intent(this,MenuActivity.class);
        startActivity(myIntent);
    }

}

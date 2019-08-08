package com.sq.questsimple;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Random;
import android.app.Activity;
import android.widget.Toast;


public class BossActivity extends Activity {


    int levelNum;
    int str;
    int dex;
    int intt;
    int strTotal;
    int dexTotal;
    int inttTotal;
    int wepStr;
    int armDex;
    int spellIntt;
    int monsterLevel;
    int health;
    int changeCount;
    int changeCountTotal;
    int userMove;
    int monsterMove;
    int levelExp;
    int maxExp;
    int currentExp;
    int bossTotal;

    TextView setMonsterLevel;
    TextView setMonsterName;
    TextView setMonsterAttack;

    ImageView strSelect;
    ImageView dexSelect;
    ImageView inttSelect;

    ProgressBar healthBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPref = getSharedPreferences("MyPres", MODE_PRIVATE);
        levelNum = sharedPref.getInt("level",1);
        str = sharedPref.getInt("str",1);
        dex = sharedPref.getInt("dex", 1);
        intt = sharedPref.getInt("intt", 1);
        wepStr =sharedPref.getInt("wepStr",0);
        armDex = sharedPref.getInt("armDex",0);
        spellIntt = sharedPref.getInt("spellIntt",0);
        levelExp = sharedPref.getInt("levelExp",0);
        maxExp = sharedPref.getInt("maxExp", 0);
        currentExp = sharedPref.getInt("currentExp",0);
        bossTotal = sharedPref.getInt("bossTotal", 0);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_boss);

        ReplaceFont.replaceDefaultFont(this, "DEFAULT", "orange.ttf");

        String[] monsterType = {"Gate Keeper", "Warlord", "Dragon","Necromancer", "Archemage", "Cockatrice","Phoenix","Giant", "Serpent", "Demigod", "Colossus", "Champion", "Hell Spawn", "Reaper", "Abomination", "Hydra", "Death Stalker", "Queen Fairy"};
        String[] monsterAdj = {"Elder", "Legendary", "Damned", "Unleashed","Elite", "Mythical", "Undedad", "Ruthless", "Blood Hungry", "Relentless", "Wicked", "Horrific", "Enraged"};
        String monsterText =  getRandom(monsterAdj) + " " + getRandom(monsterType);

        setMonsterName = (TextView) findViewById(R.id.monsterName);
        setMonsterName.setText(monsterText);

        strTotal = str + wepStr;
        dexTotal = dex + armDex;
        inttTotal = intt + spellIntt;

        Random levelSet = new Random();
        int Low = (levelNum);
        int High = (levelNum*2);
        monsterLevel = levelSet.nextInt(High-Low) + Low;

        if (monsterLevel <= 0){
            monsterLevel = 1;
        }

        setMonsterLevel = (TextView) findViewById(R.id.monsterLevel);
        setMonsterLevel.setText("Level " + monsterLevel);

        Random healthSet = new Random();
        int monsterHealthHigh = 500 + monsterLevel*monsterLevel*6500;
        int monsterHealthLow = 500 + monsterLevel*monsterLevel*4750;
        health = healthSet.nextInt(monsterHealthHigh - monsterHealthLow) + monsterHealthLow;

        healthBar = (ProgressBar) findViewById(R.id.healthBar);
        healthBar.setMax(health);
        healthBar.setProgress(health);

        setMonsterAttack = (TextView) findViewById(R.id.attackType);

        Random changeAmount = new Random();
        int attackType = changeAmount.nextInt(3);
        if (attackType == 0){
            setMonsterAttack.setText("Monster Attacking With: Strength");
            monsterMove = 0;
        }else if (attackType == 1){
            setMonsterAttack.setText("Monster Attacking With: Dexterity");
            monsterMove = 1;
        }else if (attackType == 2){
            setMonsterAttack.setText("Monster Attacking With: Intelligence");
            monsterMove = 2;
        }
        strSelect = (ImageView) findViewById(R.id.strArrow);
        dexSelect = (ImageView) findViewById(R.id.dexArrow);
        inttSelect = (ImageView) findViewById(R.id.inttArrow);
        strSelect.setVisibility(View.INVISIBLE);
        dexSelect.setVisibility(View.INVISIBLE);
        inttSelect.setVisibility(View.INVISIBLE);
    }

    public void onBack(View v){
        Intent myIntent = new Intent(this,MainActivity.class);
        startActivity(myIntent);
    }

    public void strClick(View v){
        strSelect = (ImageView) findViewById(R.id.strArrow);
        dexSelect = (ImageView) findViewById(R.id.dexArrow);
        inttSelect = (ImageView) findViewById(R.id.inttArrow);
        strSelect.setVisibility(View.VISIBLE);
        dexSelect.setVisibility(View.INVISIBLE);
        inttSelect.setVisibility(View.INVISIBLE);
        userMove = 0;
    }
    public void dexClick(View v){
        strSelect = (ImageView) findViewById(R.id.strArrow);
        dexSelect = (ImageView) findViewById(R.id.dexArrow);
        inttSelect = (ImageView) findViewById(R.id.inttArrow);
        strSelect.setVisibility(View.INVISIBLE);
        dexSelect.setVisibility(View.VISIBLE);
        inttSelect.setVisibility(View.INVISIBLE);
        userMove = 1;
    }
    public void inttClick(View v){
        strSelect = (ImageView) findViewById(R.id.strArrow);
        dexSelect = (ImageView) findViewById(R.id.dexArrow);
        inttSelect = (ImageView) findViewById(R.id.inttArrow);
        strSelect.setVisibility(View.INVISIBLE);
        dexSelect.setVisibility(View.INVISIBLE);
        inttSelect.setVisibility(View.VISIBLE);
        userMove = 2;

    }

    public void onFight(View v){

        if (monsterMove == 0){
            if (userMove == 0){
                health = health - strTotal*80;
            }else if (userMove == 1){
                health = health - dexTotal*40;
            }else if (userMove == 2){
                health = health - inttTotal*160;
            }
        }
        if (monsterMove == 1){
            if (userMove == 0){
                health = health - strTotal*160;
            }else if (userMove == 1){
                health = health - dexTotal*80;
            }else if (userMove == 2){
                health = health - inttTotal*40;
            }
        }
        if (monsterMove == 2){
            if (userMove == 0){
                health = health - strTotal*40;
            }else if (userMove == 1){
                health = health - dexTotal*160;
            }else if (userMove == 2){
                health = health - inttTotal*80;
            }
        }
        healthBar.setProgress(health);
        Random changeAmount = new Random();
        changeCount = changeAmount.nextInt(10);
        changeCountTotal = changeCountTotal + changeCount;
        if (changeCountTotal >= 175){
            int attackType = changeAmount.nextInt(3);
            if (attackType == 0){
                setMonsterAttack.setText("Monster Attacking With: Strength");
                changeCountTotal = 0;
                monsterMove = 0;
            }else if (attackType == 1){
                setMonsterAttack.setText("Monster Attacking With: Dexterity");
                changeCountTotal = 0;
                monsterMove = 1;
            }else if (attackType == 2){
                setMonsterAttack.setText("Monster Attacking With: Intelligence");
                changeCountTotal = 0;
                monsterMove = 2;
            }
        }
        healthBar.setProgress(health);
        if (health <= 0) {
            Intent myIntent = new Intent(this,MainActivity.class);
            startActivity(myIntent);
            int addTo = maxExp/2;
            levelExp = levelExp + addTo;
            currentExp = currentExp + addTo;
            bossTotal = bossTotal + 1;
            Toast.makeText(getApplicationContext(), "Boss Defeated! Gained " + addTo + " Experience", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = getSharedPreferences("MyPres", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("currentExp", currentExp);
            editor.putInt("levelExp", levelExp);
            editor.putInt("bossTotal", bossTotal);
            editor.apply();

        }
    }

    public static String getRandom(String[] myArray) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(myArray.length);
        return myArray[randomIndex];
    }

}

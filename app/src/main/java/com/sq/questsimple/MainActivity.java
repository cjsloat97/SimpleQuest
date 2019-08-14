package com.sq.questsimple;

import java.util.LinkedHashMap;
import java.util.Map;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Random;
import android.view.Window;
import android.app.Activity;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainActivity extends Activity {

    private AdView mAdView;

    long timeCurrentSession;
    long totalSessionTime;
    long closeTime;
    long currentSessionTime;

    int currentExp;
    int levelNumOff;
    int levelNum;
    int levelExp;
    int totalClicks;
    int prestige;
    int drops;
    int dropsTotal;
    int resWood;
    int resRock;
    int str;
    int dex;
    int intt;
    int reset;
    int wepStr;
    int armDex;
    int spellIntt;
    int craftLevel;
    int maxExp;
    String wep;
    String arm;
    String spell;


    ProgressBar exp;

    TextView gainExp;
    TextView score;
    TextView charLevel;
    TextView prestigeDisplay;
    TextView dropNumber;
    TextView monster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        setContentView(R.layout.activity_main);

        ReplaceFont.replaceDefaultFont(this, "DEFAULT", "orange.ttf");

        timeCurrentSession = System.currentTimeMillis();

        SharedPreferences sharedPref = getSharedPreferences("MyPres", MODE_PRIVATE);
        totalSessionTime = sharedPref.getLong("totalTime",0);
        prestige = sharedPref.getInt("prestige", 0);
        dropsTotal = sharedPref.getInt("dropsTotal",0);
        drops = sharedPref.getInt("drops",0);
        resRock = sharedPref.getInt("resRock", 0);
        resWood = sharedPref.getInt("resWood", 0);
        levelNum = sharedPref.getInt("level",1);
        levelExp = sharedPref.getInt("levelExp",0);
        currentExp = sharedPref.getInt("currentExp",0);
        totalClicks = sharedPref.getInt("totalClicks", 0);
        str = sharedPref.getInt("str",1);
        dex = sharedPref.getInt("dex", 1);
        intt = sharedPref.getInt("intt", 1);
        reset = sharedPref.getInt("reset",0);
        craftLevel = sharedPref.getInt("craftLevel" ,1);
        wepStr = sharedPref.getInt("wepStr" ,0);
        armDex = sharedPref.getInt("armDex",0);
        spellIntt = sharedPref.getInt("spellIntt",0);
        wep = sharedPref.getString("wep", "None");
        arm = sharedPref.getString("arm", "None");
        spell = sharedPref.getString("spell", "None");

        if (reset == 0){
            currentExp = 0;
            levelNum = 1;
            levelExp = 0;
            totalClicks = 0;
            prestige = 0;
            drops = 0;
            dropsTotal = 0;
            timeCurrentSession = 0;
            totalSessionTime = 0;
            closeTime = 0;
            currentSessionTime = 0;
            resWood = 0;
            resRock = 0;
            str = 1;
            dex = 1;
            intt = 1;
            reset = 1;
            wepStr = 0;
            armDex = 0;
            spellIntt = 0;
            craftLevel = 1;
            wep = "None";
            arm = "None";
            spell = "None";

        }

        if (levelNum <= 100){
            levelNumOff = levelNum;
        }else{
            levelNumOff = levelNum - (100*prestige);
        }


        String xp = "Exp: " + String.valueOf(currentExp);
        String lvl = "Level " + String.valueOf(levelNum);

        monster = (TextView)findViewById(R.id.monsterText);

        mAdView = findViewById(R.id.theAd);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        prestigeDisplay = (TextView) findViewById(R.id.prestige);
        if (prestige != 0){
            String prestigeNumber = RomanNumerals(prestige);
            String prestigeDisplayText = "Prestige: " + prestigeNumber;
            prestigeDisplay.setText(prestigeDisplayText);
        }


        exp = (ProgressBar) findViewById(R.id.expBar);
        exp.setVisibility(View.VISIBLE);

        charLevel = (TextView) findViewById(R.id.charLevell);
        charLevel.setText(lvl);

        dropNumber = (TextView) findViewById(R.id.lootText);
        dropNumber.setText("Loot Bags: " + drops);

        score = (TextView)findViewById(R.id.totalExp);
        score.setText(xp);

        double numLevel = (double) levelNumOff;
        double maxMax = Math.pow(2, numLevel / 7);
        maxMax = maxMax * 30;
        if (prestige != 0){
            double multiplier = 0.05*prestige;
            maxMax = maxMax + (maxMax*multiplier);
        }
        int maxMath = (int) maxMax;
        exp.setMax(maxMath);
        exp.setProgress(levelExp);
        maxExp = (int) maxMax;

        checkLevelUp();

    }


    public static String getRandom(String[] myArray) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(myArray.length);
        return myArray[randomIndex];
    }

    public void onMenuClick(View v){
         Intent myIntent = new Intent(this,MenuActivity.class);
         startActivity(myIntent);
    }

    public void onOpenLootBag(View v) {
        if (drops != 0) {

            int lowDropWood = 11*levelNum/4;
            int highDropWood = 11*levelNum/3;

            int lowDropRock = 8*levelNum/7;
            int highDropRock = 8*levelNum/5;
            Random dropRandom = new Random();

            int dropResult = dropRandom.nextInt(10) + 1;

            if (dropResult > 6){
                Random resRockRandom = new Random();
                int dropRock = resRockRandom.nextInt(highDropRock - lowDropRock) + lowDropRock;
                resRock = resRock + dropRock;
                Toast.makeText(getApplicationContext(), "Gained " + dropRock + " Rock", Toast.LENGTH_SHORT).show();
            }else{
                Random resWoodRandom = new Random();
                int dropWood = resWoodRandom.nextInt(highDropWood - lowDropWood) + lowDropWood;
                resWood = resWood + dropWood;
                Toast.makeText(getApplicationContext(), "Gained " + dropWood + " Wood", Toast.LENGTH_SHORT).show();
            }




            drops = drops - 1;
            dropNumber.setText("Loot Bags: " + drops);
        }
    }

    public static String RomanNumerals(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
            int matches = Int/entry.getValue();
            res += repeat(entry.getKey(), matches);
            Int = Int % entry.getValue();
        }
        return res;
    }
    public static String repeat(String s, int n) {
        if(s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    public void onClickQuest(View v) {
        Random bossBattleDayo = new Random();
        int bossBattleCheck = bossBattleDayo.nextInt(2250);
        if (bossBattleCheck == 0) {
            Toast.makeText(getApplicationContext(), "Boss Battle!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(this,BossActivity.class);
            startActivity(myIntent);
        } else {
            Random expRand = new Random();
            Random dropRand = new Random();
            int dropCheck = dropRand.nextInt(33);
            if (dropCheck == 1) {
                drops = drops + 1;
                dropsTotal = dropsTotal + 1;
                Toast.makeText(getApplicationContext(), "The Monster Dropped a Loot Bag", Toast.LENGTH_SHORT).show();
                dropNumber = (TextView) findViewById(R.id.lootText);
                dropNumber.setText("Loot Bags: " + drops);
            }

            double min = levelNumOff / 2.23 + prestige;
            double max = 2 * levelNumOff / 1.47 + prestige;

            int Low = (int) min;
            if (Low == 0) {
                Low = 1;
            }

            int High = (int) max;
            int result = expRand.nextInt(High - Low + 1) + Low;
            currentExp = currentExp + result;
            levelExp = levelExp + result;
            totalClicks = totalClicks + 1;
            String totalExp = "Exp: " + String.valueOf(currentExp);
            final String gainedExp = "+" + String.valueOf(result) + " Exp";
            String[] monsterType = {"LEAF","Bird", "Pony", "Unicorn", "Satyr", "Ape", "Turtle", "Shark", "Mermaid", "Slime", "Golem", "Fox", "Goblin", "Cow", "Duck", "Drake", "Rat", "Guard", "Zombie", "Minotaur", "Scorpion", "Bear", "Spider", "Warrior", "Mage", "Troll", "Chef", "Dwarf", "Summoner", "Elemental", "Shadow Figure", "Ghost", "Snake", "Wolf", "Dragon"};
            String[] monsterAdj = {"Space", "Short", "Scary", "Distorted", "Disembodied", "Fluffy", "Narrow", "Heavy", "Tense", "Loud", "Scaly", "Hairy", "Possessed", "Stubby", "Broad", "Passionate", "Cranky", "Heroic", "Villainous", "Barbaric", "Self-conscious", "Defiant", "Dangerous", "Homeless", "Broken", "Silly", "Damaged", "Calm", "Bite-Sized", "Adorable", "Dead", "Forsaken", "Fickle", "Gleaming", "Giddy", "Three-Headed"};
            String[] killType = {"Stabbed", "Smothered", "Catapulted", "Immolated", "Killed", "Destroyed", "Tickled", "Assassinated", "Drowned", "Executed", "Murdered", "Poisoned", "Slaughtered", "Slayed", "Asphyxiated"};
            String monsterText = getRandom(killType) + " a " + getRandom(monsterAdj) + " " + getRandom(monsterType);
            score = (TextView) findViewById(R.id.totalExp);
            gainExp = (TextView) findViewById(R.id.givenExp);
            monster = (TextView) findViewById(R.id.monsterText);
            prestigeDisplay = (TextView) findViewById(R.id.prestige);
            score.setText(totalExp);
            monster.setText(monsterText);
            gainExp.setText(gainedExp);
            exp.setProgress(levelExp);

            checkLevelUp();
        }
    }

    public void checkLevelUp(){
        int levelMax = exp.getMax();

        int checkLevelUp = exp.getProgress();
        if (checkLevelUp >= levelMax) {
            levelExp = levelExp - levelMax;
            exp.setProgress(levelExp);
            levelNumOff = levelNumOff + 1;
            levelNum = levelNum + 1;
            Random levelStat = new Random();
            int levelStatCheck = levelStat.nextInt(3);
            if (levelStatCheck == 0) {
                Random strInc = new Random();
                int lowLevel = levelNum / 2;
                int highLevel = levelNum;
                int strUp = strInc.nextInt(highLevel - lowLevel) + lowLevel;
                str = str + strUp;
                Toast.makeText(getApplicationContext(), "Strength increased by " + strUp, Toast.LENGTH_SHORT).show();
            } else if (levelStatCheck == 1) {
                Random dexInc = new Random();
                int lowLevel = levelNum / 2;
                int highLevel = levelNum;
                int dexUp = dexInc.nextInt(highLevel - lowLevel) + lowLevel;
                dex = dex + dexUp;
                Toast.makeText(getApplicationContext(), "Dexerity increased by " + dexUp, Toast.LENGTH_SHORT).show();
            } else if (levelStatCheck == 2) {
                Random inttInc = new Random();
                int lowLevel = levelNum / 2;
                int highLevel = levelNum;
                int inttUp = inttInc.nextInt(highLevel - lowLevel) + lowLevel;
                intt = intt + inttUp;
                Toast.makeText(getApplicationContext(), "Intelligence increased by " + inttUp, Toast.LENGTH_SHORT).show();

            }
            if (levelNumOff == 100) {
                levelNumOff = 1;
                prestige = prestige + 1;
                levelNum = prestige * 100;
                String prestigeNumber = RomanNumerals(prestige);
                String prestigeDisplayText = "Prestige: " + prestigeNumber;
                prestigeDisplay.setText(prestigeDisplayText);
            }
            String lvl = "Level " + String.valueOf(levelNum);
            charLevel.setText(lvl);
            double numLevel = (double) levelNumOff;
            double maxMax = Math.pow(2, numLevel / 7);
            maxMax = maxMax * 30;
            if (prestige != 0) {
                double multiplier = 0.05 * prestige;
                maxMax = maxMax + (maxMax * multiplier);
            }
            int maxMath = (int) maxMax;
            exp.setMax(maxMath);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences("MyPres", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        closeTime = System.currentTimeMillis();
        currentSessionTime = closeTime - timeCurrentSession;
        totalSessionTime = totalSessionTime + currentSessionTime;
        editor.putLong("totalTime",totalSessionTime);
        editor.putLong("currentTime", currentSessionTime);
        editor.putInt("drops", drops);
        editor.putInt("dropsTotal",dropsTotal);
        editor.putInt("str", str);
        editor.putInt("dex",dex);
        editor.putInt("intt",intt);
        editor.putInt("prestige",prestige);
        editor.putInt("level", levelNum);
        editor.putInt("currentExp", currentExp);
        editor.putInt("levelExp", levelExp);
        editor.putInt("totalClicks", totalClicks);
        editor.putInt("craftLevel",craftLevel);
        editor.putInt("resWood", resWood);
        editor.putInt("resRock",resRock);
        editor.putInt("wepStr", wepStr);
        editor.putInt("armDex", armDex);
        editor.putInt("spellIntt", spellIntt);
        editor.putInt("reset", reset);
        editor.putInt("maxExp",maxExp);
        editor.putString("wep", wep);
        editor.putString("arm", arm);
        editor.putString("spell", spell);
        editor.apply();
    }

    @Override
    protected void onResume(){
        super.onResume();
        timeCurrentSession = System.currentTimeMillis();
    }
}
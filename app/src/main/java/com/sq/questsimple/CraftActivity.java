package com.sq.questsimple;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class CraftActivity extends Activity {

    TextView craftWood;
    TextView craftRock;
    TextView neededWood;
    TextView neededRock;
    TextView craftLvl;
    int craftLevel;
    int resWood;
    int resRock;
    int needWood;
    int needRock;
    int wepStr;
    int armDex;
    int spellIntt;
    String wep;
    String arm;
    String spell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences("MyPres", MODE_PRIVATE);
        resRock = sharedPref.getInt("resRock", 0);
        resWood = sharedPref.getInt("resWood", 0);
        craftLevel = sharedPref.getInt("craftLevel" ,1);
        wepStr = sharedPref.getInt("wepStr" ,0);
        armDex = sharedPref.getInt("armDex",0);
        spellIntt = sharedPref.getInt("spellIntt",0);
        wep = sharedPref.getString("wep", "None");
        arm = sharedPref.getString("arm", "None");
        spell = sharedPref.getString("spell", "None");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_craft);
        ReplaceFont.replaceDefaultFont(this, "DEFAULT", "orange.ttf");

        double numLevel = (double) craftLevel;
        double maxMax = Math.pow(numLevel,1.5);
        needWood  = (int) maxMax * 9;
        needRock = needWood/2;

        craftLvl = (TextView) findViewById(R.id.craftLevel);
        craftLvl.setText("Crafting Level: " + craftLevel);
        craftWood = (TextView)findViewById(R.id.haveWood);
        craftWood.setText("Wood: " + resWood);
        craftRock = (TextView)findViewById(R.id.haveRock);
        craftRock.setText("Rock: " + resRock);
        neededWood = (TextView)findViewById(R.id.needWood);
        neededWood.setText(needWood + " Wood");
        neededRock = (TextView)findViewById(R.id.needRock);
        neededRock.setText(needRock + " Rock");

            }

    public void onCraftClick(View v) {
        if (needWood <= resWood && needRock <= resRock) {
            craftLevel = craftLevel + 1;


            resWood = resWood - needWood;
            resRock = resRock - needRock;

            double numLevel = (double) craftLevel;
            double maxMax = Math.pow(numLevel, 1.5);
            needWood = (int) maxMax * 9;
            needRock = needWood / 2;

            craftLvl = (TextView) findViewById(R.id.craftLevel);
            craftLvl.setText("Crafting Level: " + craftLevel);

            craftWood = (TextView) findViewById(R.id.haveWood);
            craftWood.setText("Wood: " + resWood);
            craftRock = (TextView) findViewById(R.id.haveRock);
            craftRock.setText("Rock: " + resRock);
            neededWood = (TextView) findViewById(R.id.needWood);
            neededWood.setText(needWood + " Wood");
            neededRock = (TextView) findViewById(R.id.needRock);
            neededRock.setText(needRock + " Rock");

            onCraft();
        } else {
            Toast.makeText(getApplicationContext(), "Not enough resources!", Toast.LENGTH_SHORT).show();
        }
    }

    void onCraft (){
        Random levelStat = new Random();
        int levelStatCheck = levelStat.nextInt(3);
        if (levelStatCheck == 0) {
            Random strInc= new Random();
            int lowLevel = craftLevel*5;
            int highLevel = craftLevel*7;
            wepStr = wepStr + strInc.nextInt(highLevel - lowLevel) + lowLevel;
            String[] wepType = {"Sword", "Bow", "Knife", "Dagger", "Crossbow", "Longsword","Katana","Staff", "Wand", "Axe", "Cutlass", "Hammer", "Spear", "Scimitar", "Claw", "Orb", "Spellbook", "Hatchet", "Whip", "Shortbow", "Hand Cannon", "Rapier", "Pickaxe"};
            String[] wepAdj = {"Rusted", "Diamond-Engraved", "Throwing", "Rusted", "Blunted", "Hell-Forged", "Diamond Engraved", "Magical","Off-Balance", "Throwing","Exalted", "Grand", "Blessed", "Mystic", "Mythic", "Epic", "Legendary", "Steel","Bronze","Crystal", "Enchanted"};
            String[] wepLevel = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
            String wepText = getRandom(wepAdj) + " " +getRandom(wepType) + " " + getRandom(wepLevel);
            wep = wepText;
            Toast.makeText(getApplicationContext(), "Crafted: " + wepText + ", Strength Up " + wepStr, Toast.LENGTH_SHORT).show();
        }
        else if(levelStatCheck == 1){
            Random dexInc= new Random();
            int lowLevel = craftLevel*5;
            int highLevel = craftLevel*7;
            armDex = armDex + dexInc.nextInt(highLevel - lowLevel) + lowLevel;
            String[] armType = {"Pants", "Helmet", "Gloves", "Gauntlets", "Boxers", "Skirt", "Hood", "Cloak", "Shield", "Robes", "Scarf","Chainmail", "Visor", "Spacesuit", "Suit", "Greaves"};
            String[] armAdj = {"Golden","Leather", "Cloth", "Mail", "Plate", "Spiked", "Abyssal", "Tactical", "Night Vision", "Elite", "Tattered", "Glimmering", "Shinning", "Ripped", "Torn", "Ruined", "Pristine", "Rainbow", "Dark", "Invisible", "Lucky", "Dragon Scale"};
            String[] armLevel = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
            String armText = getRandom(armAdj) + " " +getRandom(armType) + " " + getRandom(armLevel);
            arm = armText;
            Toast.makeText(getApplicationContext(), "Crafted: " + armText + ", Dexterity Up " + armDex, Toast.LENGTH_SHORT).show();
        }
        else if(levelStatCheck == 2){
            Random inttInc= new Random();
            int lowLevel = craftLevel*5;
            int highLevel = craftLevel*7;
            String[] spellType = {"Fireball","Lightening", "Teleport", "Snare", "Transmute", "Ice Lance", "Elemental Blast", "Shadow Bolt", "Holy Smite", "Heal", "Poison Spray", "Curse"};
            String[] spellAdj = {"Strong","Fizzling", "Weakening", "Powerful", "Weak", "Large", "Puny", "Enchanted", "Multi-Strike", "Damning", "Silencing", "Fluffy", "Infinite"};
            String[] spellLevel = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
            String spellText = getRandom(spellAdj) + " " +getRandom(spellType) + " " + getRandom(spellLevel);
            spell = spellText;
            spellIntt = spellIntt + inttInc.nextInt(highLevel - lowLevel) + lowLevel;
            Toast.makeText(getApplicationContext(), "Crafted: " + spellText + ", Intelligence Up " + spellIntt, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences("MyPres", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("craftLevel",craftLevel);
        editor.putInt("resWood", resWood);
        editor.putInt("resRock",resRock);
        editor.putInt("wepStr", wepStr);
        editor.putInt("armDex", armDex);
        editor.putInt("spellIntt", spellIntt);
        editor.putString("wep", wep);
        editor.putString("arm", arm);
        editor.putString("spell", spell);
        editor.apply();
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
}


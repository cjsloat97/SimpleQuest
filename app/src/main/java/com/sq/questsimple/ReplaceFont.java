package com.sq.questsimple;

import android.content.Context;
import android.graphics.Typeface;
import java.lang.reflect.Field;

public class ReplaceFont {
    public static void replaceDefaultFont(Context context, String nameOfFontBeingReplaced, String nameOfFontInAssets) {
        replaceFont(nameOfFontBeingReplaced, Typeface.createFromAsset(context.getAssets(), nameOfFontInAssets));
    }

    private static void replaceFont(String nameOfFontBeingReplaced, Typeface customFontTypeFace) {
        try {
            Field myField = Typeface.class.getDeclaredField(nameOfFontBeingReplaced);
            myField.setAccessible(true);
            myField.set(null, customFontTypeFace);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }
}

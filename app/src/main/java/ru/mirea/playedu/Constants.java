package ru.mirea.playedu;

import android.content.Context;

import androidx.core.content.ContextCompat;

// Статический класс с константами
public class Constants {

    static int[] getCategoryColors(Context context) {
         int[] colors = {
                ContextCompat.getColor(context, R.color.silver_coin),
                ContextCompat.getColor(context, R.color.white),
                ContextCompat.getColor(context, R.color.black),
                ContextCompat.getColor(context, R.color.purple_400),
                ContextCompat.getColor(context, R.color.purple_700),
                ContextCompat.getColor(context, R.color.golden_coin),
                ContextCompat.getColor(context, R.color.silver_coin),
                ContextCompat.getColor(context, R.color.white),
         };
         return colors;
    }
}

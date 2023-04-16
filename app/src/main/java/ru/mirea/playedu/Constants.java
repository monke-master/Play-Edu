package ru.mirea.playedu;

import android.content.Context;

import androidx.core.content.ContextCompat;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ru.mirea.playedu.model.Achievement;

// Статический класс с константами
public class Constants {

    // Константы иконок персонажа
    public static String MALE_IC = "MALE_HERO";
    public static String FEMALE_IC = "FEMALE_HERO";

    public static int MIN_PASSWORD_LENGTH = 8;
    public static int MAX_PASSWORD_LENGTH = 32;


    public static Achievement[] ACHIEVEMENTS_LIST = new Achievement[]{
            new Achievement(1, 1, "Орден Пропастина", "За заслуги перед Пропастинечеством", true, "No"),
            new Achievement(1, 1, "Звезда Беркова", "За взятие Киевского интеграла", true, "No"),
            new Achievement(1, 1, "Поцелуй Большого Булеана", "Лучше не знать откуда это у вас...", true, "No"),
            new Achievement(1, 1, "Триггер Смирнова", "Собрать трехступенчатый PR-триггер", false, "No")
    };


    public static int[] getCategoryColors(Context context) {
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

    public static String getDeadlineString(Date deadline) {
        Locale russian = new Locale("ru");
        String[] newMonths = {
                "января", "февраля", "марта", "апреля", "мая", "июня",
                "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        DateFormatSymbols dfs = DateFormatSymbols.getInstance(russian);
        dfs.setMonths(newMonths);
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, russian);
        SimpleDateFormat sdf = (SimpleDateFormat) df;
        sdf.setDateFormatSymbols(dfs);
        return sdf.format(deadline);
    }


}

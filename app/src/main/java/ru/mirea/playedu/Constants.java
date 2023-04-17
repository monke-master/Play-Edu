package ru.mirea.playedu;

import android.content.Context;

import androidx.core.content.ContextCompat;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.mirea.playedu.model.Achievement;
import ru.mirea.playedu.model.Power;

// Статический класс с константами
public class Constants {

    // Константы иконок персонажа
    public static String MALE_IC = "MALE_HERO";
    public static String FEMALE_IC = "FEMALE_HERO";
    // Константы для пароля
    public static int MIN_PASSWORD_LENGTH = 8;
    public static int MAX_PASSWORD_LENGTH = 32;

    // Типы продаваемых сил
    public static int SILVER_COINS_TYPE = 0;
    public static int GOLDEN_COINS_TYPE = 1;

    // Список достижений (убрать нахер се неподтребство на сервер)
    public static Achievement[] ACHIEVEMENTS_LIST = new Achievement[]{
            new Achievement(1, 1, "Орден Пропастина", "За заслуги перед Пропастинечеством", true, "No"),
            new Achievement(1, 1, "Звезда Беркова", "За взятие Киевского интеграла", true, "No"),
            new Achievement(1, 1, "Поцелуй Большого Булеана", "Лучше не знать откуда это у вас...", true, "No"),
            new Achievement(1, 1, "Триггер Смирнова", "Собрать трехступенчатый PR-триггер", false, "No")
    };
    // Список сил (убрать нахер се неподтребство на сервер)
    public static Power[] POWERS_LIST = new Power[]{
            new Power(1, 1, "Сила Немировской", "Ворует все деньги у противника", false, "NO", SILVER_COINS_TYPE, 100),
            new Power(2, 1, "Сила Беркова", "Проецирует противника на одномерное пространство", false, "NO", SILVER_COINS_TYPE, 150),
            new Power(2, 1, "Сила Красникова", "Отче наш, сущий на небесах!\n" +
                    "Да святится имя Твое;\n" +
                    "Да приидет Царствие Твое;\n" +
                    "Да будет воля Твоя и на земле, как на небе; \n" +
                    "Хлеб наш насущный дай нам на сей день; \n" +
                    "И прости нам долги наши, как и мы прощаем должникам нашим;\n" +
                    "И не введи нас в искушение, но избавь нас от лукавого. \n" +
                    "Ибо Твое есть Царство и сила и слава вовеки.", false, "NO", GOLDEN_COINS_TYPE, 1500000)

    };


    public static int[] getCategoryColors(Context context) {
         int[] colors = {
                ContextCompat.getColor(context, R.color.silver),
                ContextCompat.getColor(context, R.color.white),
                ContextCompat.getColor(context, R.color.black),
                ContextCompat.getColor(context, R.color.purple_400),
                ContextCompat.getColor(context, R.color.purple_700),
                ContextCompat.getColor(context, R.color.golden),
                ContextCompat.getColor(context, R.color.silver),
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

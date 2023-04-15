package ru.mirea.playedu;

import android.content.Context;

import androidx.core.content.ContextCompat;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

// Статический класс с константами
public class Constants {
    // Перечисление состояний результатов фазы
    public enum PhaseResult {
        DEAL_DAMAGE,
        MISHIT,
        GET_DAMAGE,
        AVOID_DAMAGE
    }
    // Перечисление состояний результатов фазы
    public enum BattleResult {
        DEFEAT,
        WIN_ADVENTURE,
        WIN_BATTLE
    }
    // Разброс здоровья монстров
    public static final int minHealth = 2;
    public static final int maxHealth = 5;
    // Разброс урона монстров
    public static final int minDamage = 1;
    public static final int maxDamage = 5;
    // Список имён сонстров
    public static final String[] monstersNames = {"Одноглазом", "Трёхглазом", "Сессией"};
    // Словарь имен сонстров и их картинок
    public static Map<String, Integer> monsters = new HashMap<String, Integer>();
    // Разброс имён монстров
    public static final int minNamesInd = 0;
    public static final int maxNamesInd = 2;
    // Разброс cкорости в фазе атаки
    public static final int minAttackSpeed = 100;
    public static final int maxAttackSpeed = 500;
    // Разброс cкорости в фазе защиты
    public static final int minDefenseSpeed = 100;
    public static final int maxDefenseSpeed = 200;
    // Разброс в фазе защиты
    public static final int minDefenseSpread = 20;
    public static final int maxDefenseSpread = 300;
    // Разброс времени фазы
    public static final int minTime = 5;
    public static final int maxTime = 10;
    // Коэффициенты сложности для рассчета награды
    public static final float healthKoef = 0.1f;
    public static final float damageKoef = 0.1f;
    public static final float attackSpeedKoef = 0.1f;
    public static final float defenseSpeedKoef = 0.1f;
    public static final float defenseSpreadKoef = 0.1f;
    public static final float timeKoef = 0.1f;
    // Количество противников
    public static final int enemiesCount = 3;

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

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

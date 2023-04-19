package ru.mirea.playedu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ru.mirea.playedu.model.Achievement;
import ru.mirea.playedu.model.Power;

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

    // Константы иконок персонажа
    public static String MALE_IC = "MALE_HERO";
    public static String FEMALE_IC = "FEMALE_HERO";
    // Константы для пароля
    public static int MIN_PASSWORD_LENGTH = 8;
    public static int MAX_PASSWORD_LENGTH = 32;

    // Типы продаваемых сил
    public static int SILVER_COINS_TYPE = 0;
    public static int GOLDEN_COINS_TYPE = 1;

    // Константы эффектов
    public static int EFFECT_INFO = 0;
    public static int EFFECT_USAGE = 1;

    // Сила заглушка
    public static Power selectablePower = new Power(-1, 0, "", "", false, R.drawable.ic_pick_power, SILVER_COINS_TYPE, EFFECT_INFO,0);

    // Список достижений (убрать нахер се неподтребство на сервер)
    public static Achievement[] ACHIEVEMENTS_LIST = new Achievement[]{
            new Achievement(1, 1, "Орден Пропастина", "За заслуги перед Пропастинечеством", true, "No"),
            new Achievement(1, 1, "Звезда Беркова", "За взятие Киевского интеграла", true, "No"),
            new Achievement(1, 1, "Поцелуй Большого Булеана", "Лучше не знать откуда это у вас...", true, "No"),
            new Achievement(1, 1, "Триггер Смирнова", "Собрать трехступенчатый PR-триггер", false, "No")
    };

    // Получение списка сил
    public static Power[] getPowersList(Context context) {
        String[] powersTitles = context.getResources().getStringArray(R.array.powersTitles);
        String[] powersDescriptions = context.getResources().getStringArray(R.array.powersDescription);
        ArrayList<Power> POWERS_LIST = new ArrayList<>();
        POWERS_LIST.add(new Power(0, 0, powersTitles[0], powersDescriptions[0], false, R.drawable.gold_pocket_power_ic, SILVER_COINS_TYPE, EFFECT_INFO,100));
        POWERS_LIST.add(new Power(1, 0, powersTitles[1], powersDescriptions[1], false, R.drawable.ice_power_ic, SILVER_COINS_TYPE, EFFECT_INFO,100));
        POWERS_LIST.add(new Power(2, 0, powersTitles[2], powersDescriptions[2], false, R.drawable.spell_book_power_ic, SILVER_COINS_TYPE, EFFECT_USAGE,100));
        POWERS_LIST.add(new Power(3, 0, powersTitles[3], powersDescriptions[3], false, R.drawable.fire_power_ic, SILVER_COINS_TYPE, EFFECT_INFO,100));
        POWERS_LIST.add(new Power(4, 0, powersTitles[4], powersDescriptions[4], false, R.drawable.earth_power_ic, SILVER_COINS_TYPE, EFFECT_INFO,100));
        POWERS_LIST.add(new Power(5, 0, powersTitles[5], powersDescriptions[5], false, R.drawable.wind_power_ic, SILVER_COINS_TYPE, EFFECT_INFO,100));
        POWERS_LIST.add(new Power(6, 0, powersTitles[6], powersDescriptions[6], false, R.drawable.time_power_ic, SILVER_COINS_TYPE, EFFECT_INFO,100));
        POWERS_LIST.add(new Power(7, 0, powersTitles[7], powersDescriptions[7], false, R.drawable.health_power_ic, SILVER_COINS_TYPE, EFFECT_USAGE,100));
        POWERS_LIST.add(new Power(8, 0, powersTitles[8], powersDescriptions[8], false, R.drawable.speed_power_ic, SILVER_COINS_TYPE, EFFECT_INFO,100));
        POWERS_LIST.add(new Power(9, 0, powersTitles[9], powersDescriptions[9], false, R.drawable.blood_power_ic, SILVER_COINS_TYPE, EFFECT_USAGE,100));
        POWERS_LIST.add(new Power(10, 0, powersTitles[10], powersDescriptions[10], false, R.drawable.wisdom_power_ic, SILVER_COINS_TYPE, EFFECT_INFO,100));
        POWERS_LIST.add(new Power(11, 0, powersTitles[11], powersDescriptions[11], false, R.drawable.poison_power_ic, SILVER_COINS_TYPE, EFFECT_INFO,100));
        POWERS_LIST.add(new Power(12, 0, powersTitles[12], powersDescriptions[12], false, R.drawable.griffin_power_ic, SILVER_COINS_TYPE, EFFECT_USAGE,100));
        POWERS_LIST.add(new Power(13, 0, powersTitles[13], powersDescriptions[13], false, R.drawable.life_power_ic, SILVER_COINS_TYPE, EFFECT_INFO,100));
        POWERS_LIST.add(new Power(14, 0, powersTitles[14], powersDescriptions[14], false, R.drawable.student_power_ic, SILVER_COINS_TYPE, EFFECT_USAGE,100));
        Power[] powersArray = new Power[POWERS_LIST.size()];
        powersArray = POWERS_LIST.toArray(powersArray);
        return powersArray;
    }


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

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

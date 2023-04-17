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

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

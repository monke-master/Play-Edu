package ru.mirea.playedu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

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
        AVOID_DAMAGE,
        POISON_POWER
    }
    // Перечисление состояний результатов фазы
    public enum BattleResult {
        DEFEAT,
        WIN_ADVENTURE,
        WIN_BATTLE
    }
    // Перечисление активных сил
    public enum Powers {
        NONE,
        MONEY_BAG,
        ICE_POWER,
        SPELL_BOOK_POWER,
        FIRE_POWER,
        EARTH_POWER,
        WIND_POWER,
        TIME_POWER,
        HEALTH_POWER,
        SPEED_POWER,
        BLOOD_POWER,
        WISDOM_POWER,
        POISON_POWER,
        GRIFFIN_POWER,
        LIFE_POWER,
        STUDENT_POWER
    }
    // Перечисление текущего статуса активной способоности
    public enum PowerStatus {
        AVAILABLE,
        USED
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
    // Награда за босса
    public static final int bossPrice = 200;
    // Вероятность встретить босса в конце вылазки
    public static final float bossAppearancePercent = 0.25f;
    // Количество противников
    public static final int enemiesCount = 3;

    // Стандартное здоровье игрока
    public static final int playerHealth = 5;
    // Стандартный урон игрока
    public static final int playerDamage = 3;

    // Процентоное увеличение награды силы "Денежный мешок"
    public static final float moneyPowerKoef = 0.25f;
    // Процентное уменьшение разброса круга врага в фазе защиты от силы земли
    public static final float earthPowerKoef = 0.25f;
    // Процентное уменьшение скорости движения вражеского круга от силы ветра
    public static final float windPowerKoef = 0.25f;
    // Количество ошибок от силы времени
    public static final int mistakesCount = 2;
    // Замедление времени фазы от силы скорости
    public static final int speedPowerKoef = 3;
    // Количество добавочного здоровья от силы крови
    public static final int bloodPowerKoef = 2;
    // Процентное увеличение силы противников от силы мудрости
    public static final float wisdomPowerEnemyKoef = 0.15f;
    // Процентное увеличение силы игрока от силы мудрости
    public static final int wisdomPowerPlayerKoef = 1;
    // Процентный шанс заморозить противника от силы льда
    public static final float icePowerKoef = 0.25f;
    // Количество здоровья от силы здоровья
    public static final int healthPowerKoef = 2;

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
    public static Power selectablePower = new Power(-1, 0, "", "", false, R.drawable.ic_pick_power, SILVER_COINS_TYPE, EFFECT_INFO, Powers.NONE,0);

    // Получение массива сил
    public static Power[] getPowersList(Context context) {
        String[] powersTitles = context.getResources().getStringArray(R.array.powersTitles);
        String[] powersDescriptions = context.getResources().getStringArray(R.array.powersDescription);
        ArrayList<Power> POWERS_LIST = new ArrayList<>();
        POWERS_LIST.add(new Power(0, 0, powersTitles[0], powersDescriptions[0], false, R.drawable.gold_pocket_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.MONEY_BAG,100));
        POWERS_LIST.add(new Power(1, 0, powersTitles[1], powersDescriptions[1], false, R.drawable.ice_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.ICE_POWER,100));
        POWERS_LIST.add(new Power(2, 0, powersTitles[2], powersDescriptions[2], false, R.drawable.spell_book_power_ic, SILVER_COINS_TYPE, EFFECT_USAGE,Powers.SPELL_BOOK_POWER,100));
        POWERS_LIST.add(new Power(3, 0, powersTitles[3], powersDescriptions[3], false, R.drawable.fire_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.FIRE_POWER,100));
        POWERS_LIST.add(new Power(4, 0, powersTitles[4], powersDescriptions[4], false, R.drawable.earth_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.EARTH_POWER,100));
        POWERS_LIST.add(new Power(5, 0, powersTitles[5], powersDescriptions[5], false, R.drawable.wind_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.WIND_POWER,100));
        POWERS_LIST.add(new Power(6, 0, powersTitles[6], powersDescriptions[6], false, R.drawable.time_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.TIME_POWER,100));
        POWERS_LIST.add(new Power(7, 0, powersTitles[7], powersDescriptions[7], false, R.drawable.health_power_ic, SILVER_COINS_TYPE, EFFECT_USAGE, Powers.HEALTH_POWER,100));
        POWERS_LIST.add(new Power(8, 0, powersTitles[8], powersDescriptions[8], false, R.drawable.speed_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.SPEED_POWER,100));
        POWERS_LIST.add(new Power(9, 0, powersTitles[9], powersDescriptions[9], false, R.drawable.blood_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.BLOOD_POWER,100));
        POWERS_LIST.add(new Power(10, 0, powersTitles[10], powersDescriptions[10], false, R.drawable.wisdom_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.WISDOM_POWER,100));
        POWERS_LIST.add(new Power(11, 0, powersTitles[11], powersDescriptions[11], false, R.drawable.poison_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.POISON_POWER,100));
        POWERS_LIST.add(new Power(12, 0, powersTitles[12], powersDescriptions[12], false, R.drawable.griffin_power_ic, SILVER_COINS_TYPE, EFFECT_USAGE, Powers.GRIFFIN_POWER,100));
        POWERS_LIST.add(new Power(13, 0, powersTitles[13], powersDescriptions[13], false, R.drawable.life_power_ic, SILVER_COINS_TYPE, EFFECT_INFO, Powers.LIFE_POWER,100));
        POWERS_LIST.add(new Power(14, 0, powersTitles[14], powersDescriptions[14], false, R.drawable.student_power_ic, SILVER_COINS_TYPE, EFFECT_USAGE, Powers.STUDENT_POWER,100));
        Power[] powersArray = new Power[POWERS_LIST.size()];
        powersArray = POWERS_LIST.toArray(powersArray);
        return powersArray;
    }

    // Получение массива достижений
    public static Achievement[] getAchievementsList(Context context) {
        String[] achievementsTitles = context.getResources().getStringArray(R.array.achievementsTitles);
        String[] achievementsDescriptions = context.getResources().getStringArray(R.array.achievementsDescription);
        ArrayList<Achievement> ACHIEVEMENTS_LIST = new ArrayList<>();
        ACHIEVEMENTS_LIST.add(new Achievement(0, 0, achievementsTitles[0], achievementsDescriptions[0], false, R.drawable.adventurer_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(1, 0, achievementsTitles[1], achievementsDescriptions[1], false, R.drawable.genius_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(2, 0, achievementsTitles[2], achievementsDescriptions[2], false, R.drawable.collector_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(3, 0, achievementsTitles[3], achievementsDescriptions[3], false, R.drawable.hero_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(4, 0, achievementsTitles[4], achievementsDescriptions[4], false, R.drawable.legend_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(5, 0, achievementsTitles[5], achievementsDescriptions[5], false, R.drawable.nosy_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(6, 0, achievementsTitles[6], achievementsDescriptions[6], false, R.drawable.fashionista_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(7, 0, achievementsTitles[7], achievementsDescriptions[7], false, R.drawable.undead_killer1_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(8, 0, achievementsTitles[8], achievementsDescriptions[8], false, R.drawable.undead_killer2_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(9, 0, achievementsTitles[9], achievementsDescriptions[9], false, R.drawable.undead_killer3_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(10, 0, achievementsTitles[10], achievementsDescriptions[10], false, R.drawable.kings_assassin_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(11, 0, achievementsTitles[11], achievementsDescriptions[11], false, R.drawable.academician1_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(12, 0, achievementsTitles[12], achievementsDescriptions[12], false, R.drawable.academician2_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(13, 0, achievementsTitles[13], achievementsDescriptions[13], false, R.drawable.academician3_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(14, 0, achievementsTitles[14], achievementsDescriptions[14], false, R.drawable.admiral_karpov_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(15, 0, achievementsTitles[15], achievementsDescriptions[15], false, R.drawable.heroic_endurance1_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(16, 0, achievementsTitles[16], achievementsDescriptions[16], false, R.drawable.heroic_endurance2_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(17, 0, achievementsTitles[17], achievementsDescriptions[17], false, R.drawable.heroic_endurance3_achiev_ic));
        ACHIEVEMENTS_LIST.add(new Achievement(18, 0, achievementsTitles[18], achievementsDescriptions[18], false, R.drawable.red_star_berkov_achiev_ic));
        Achievement[] achievementsArray = new Achievement[0];
        achievementsArray = ACHIEVEMENTS_LIST.toArray(achievementsArray);
        return achievementsArray;
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

    public static float DIALOG_COEFFICIENT = 0.89f;
}

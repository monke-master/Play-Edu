package ru.mirea.playedu;

import java.util.Calendar;

public class DateHelper {

    // Проверка даты, является ли она текущим днем
    public static boolean isToday(Calendar date) {
        Calendar today = Calendar.getInstance();
        return equalDate(date, today);

    }

    // Проверка, указывают ли даты на одинаковый день
    public static boolean equalDate(Calendar date1, Calendar date2) {
        return date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)
                && date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)
                && date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH);
    }

    public static Calendar getTodayDate() {
        return Calendar.getInstance();
    }
}

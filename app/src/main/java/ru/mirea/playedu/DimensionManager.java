package ru.mirea.playedu;

public class DimensionManager {

    public static int calcHorizontalMargin(int windowWidth, int itemWidth, int count, float coeff) {
        return (int) (windowWidth*coeff - count*itemWidth)/ (count - 1);
    }
}

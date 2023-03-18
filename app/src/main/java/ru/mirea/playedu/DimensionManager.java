package ru.mirea.playedu;

public class DimensionManager {

    public static int calcHorizontalMargin(int windowWidth, int itemWidth, int count) {
        return (int) (windowWidth*0.89 - count*itemWidth)/ (count - 1);
    }
}

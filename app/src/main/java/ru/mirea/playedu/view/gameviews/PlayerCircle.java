package ru.mirea.playedu.view.gameviews;

import android.content.Context;

import androidx.core.content.ContextCompat;

import ru.mirea.playedu.R;

public class PlayerCircle extends SpaceBody{
    public PlayerCircle(Context context,  float speed, int size, boolean phase) {
        colorId = ContextCompat.getColor(context, R.color.player); // определяем начальные параметры
        this.size = size;
        x= GameView.maxX / 2;
        y= GameView.maxY / 2;
        this.speed = speed;
        isAttackPhase = phase;
    }

    @Override
    public void update() {
        if (!isAttackPhase) {

        }
    }

    public boolean isCollision(float enemySize) {
        return (size - 0.3 <= enemySize) || (enemySize <= size + 0.3) ;
    }
}

package ru.mirea.playedu.view.gameviews;

import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

import ru.mirea.playedu.R;
import ru.mirea.playedu.view.fragment.GameFragment;

public class PlayerCircle extends SpaceBody{
    public PlayerCircle(Context context,  int size, boolean phase) {
        colorId = ContextCompat.getColor(context, R.color.player); // определяем начальные параметры
        this.size = size;
        x= GameView.maxX / 2;
        y= GameView.maxY / 2;
        isAttackPhase = phase;
    }

    public PlayerCircle(Context context,  float speed, boolean phase) {
        colorId = ContextCompat.getColor(context, R.color.player); // определяем начальные параметры
        this.size = 20;
        x= GameView.maxX / 2;
        y= GameView.maxY / 2;
        this.speed = speed;
        isAttackPhase = phase;
    }

    @Override
    public void update() {
        if (!isAttackPhase) {
            if (GameFragment.isPlayerPressed && size <= 400) {
                size += speed;
            }
            else if (!GameFragment.isPlayerPressed && size >= 10){
                size -= speed;
            }
        }
    }

    public boolean isCollision(float enemySize) {
        return (enemySize <= size + 10) && (enemySize >= size - 10) ;
    }
}

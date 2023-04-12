package ru.mirea.playedu.view.gameviews;

import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.util.Random;

import ru.mirea.playedu.R;
import ru.mirea.playedu.view.fragment.GameFragment;

public class EnemyCircle extends SpaceBody{
    private float minSpeed = (float) 0.1; // минимальная скорость
    private float maxSpeed = (float) 0.5; // максимальная скорость
    private boolean isGrow = true;

    public EnemyCircle(Context context, int speed, int size, boolean phase) {
        colorId = ContextCompat.getColor(context, R.color.enemy);
        x = GameView.maxX / 2;
        this.size = size;
        y = GameView.maxY / 2;
        this.speed = speed;
        isAttackPhase = phase;
    }

    @Override
    public void update() {
        if (isAttackPhase) {
            if(isGrow){
                size += speed;
                if (size >= 200) isGrow = false;
            }
            else {
                size -= speed;
                if (size <= 10) isGrow = true;
            }
        }
    }
}

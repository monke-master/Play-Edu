package ru.mirea.playedu.view.gameviews;

import static ru.mirea.playedu.Constants.getRandomNumber;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.util.Random;

import ru.mirea.playedu.Constants;
import ru.mirea.playedu.R;
import ru.mirea.playedu.view.dialog.DamageDialog;
import ru.mirea.playedu.view.fragment.GameFragment;

public class EnemyCircle extends SpaceBody{
    private boolean isGrow = true;
    private long phaseTime;
    private int sizeSpread;
    private int changeSizeStep = 0;

    public EnemyCircle(Context context, float speed, boolean phase) {
        colorId = ContextCompat.getColor(context, R.color.enemy);
        x = GameView.maxX / 2;
        y = GameView.maxY / 2;
        this.size = 10;
        this.speed = speed;
        isAttackPhase = phase;
    }

    public EnemyCircle(Context context, int sizeSpread,  boolean phase) {
        colorId = ContextCompat.getColor(context, R.color.enemy);
        x = GameView.maxX / 2;
        y = GameView.maxY / 2;
        this.phaseTime = phaseTime * 1000L;
        this.sizeSpread = sizeSpread;
        size = 40;
        isAttackPhase = phase;
    }

    public void updateSize() {
        size = getRandomNumber(40, sizeSpread);
    }

    @Override
    public void update() {
        if (isAttackPhase) {
            if(isGrow){
                size += speed;
                if (size >= 400) isGrow = false;
            }
            else {
                size -= speed;
                if (size <= 10) isGrow = true;
            }
        }
    }
}

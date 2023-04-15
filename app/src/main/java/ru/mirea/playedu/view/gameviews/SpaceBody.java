package ru.mirea.playedu.view.gameviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class SpaceBody {
    protected float x; // координаты
    protected float y;
    protected float size; // размер
    protected float speed; // скорость
    protected int colorId;
    protected boolean isAttackPhase;

    void update(){ // тут будут вычисляться новые координаты
    }

    void drow(Paint paint, Canvas canvas){ // рисуем картинку
        paint.setColor(colorId);
        paint.setAlpha(100);
        canvas.drawCircle(x*GameView.unitW, y*GameView.unitH, size, paint);
    }
}

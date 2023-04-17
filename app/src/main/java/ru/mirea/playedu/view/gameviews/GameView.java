package ru.mirea.playedu.view.gameviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.lifecycle.ViewModelProvider;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import ru.mirea.playedu.viewmodel.GameViewModel;

public class GameView extends SurfaceView implements Runnable {
    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали

    private boolean firstTime = true;
    private boolean gameRunning = true;
    private boolean isAttackPhase;
    private int circleSize;
    private float circleSpeed;
    // Разброс размеров круга игрока
    private final int minSize = 100;
    private final int maxSize = 300;
    private PlayerCircle playerCircle;
    private EnemyCircle enemyCircle;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;


    // Конструктор для фазы атаки
    public GameView(Context context, float speed, boolean phase) {
        super(context);
        // Задаем фазу боя
        isAttackPhase = phase;
        // Задаём начальные параметры для кругов
        circleSpeed = speed;
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Конструктор для фазы защиты
    public GameView(Context context, int size, float speed, boolean phase) {
        super(context);
        // Задаем фазу боя
        isAttackPhase = phase;
        // Задаём время фазы
        // Задаём начальные параметры для кругов
        circleSize = size;
        circleSpeed = speed;
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        if(!firstTime) {
            playerCircle.update();
            enemyCircle.update();
        }
    }

    public boolean isEnemyColide() {
        return playerCircle.isCollision(enemyCircle.size);
    }

    public void updateObjectsSizes() {
        if (enemyCircle != null) enemyCircle.updateSize();
    }

    public void endGame() {
        gameRunning = false;
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if(firstTime){ // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;
                Log.e("Creation", "Create objects");
                if (isAttackPhase) {
                    Random random = new Random();
                    int playerSize = ThreadLocalRandom.current().nextInt(minSize, maxSize + 1);
                    playerCircle = new PlayerCircle(getContext(), playerSize, isAttackPhase); // добавляем круг игрока
                    enemyCircle = new EnemyCircle(getContext(), circleSpeed, isAttackPhase);
                }
                else {
                    playerCircle = new PlayerCircle(getContext(), circleSpeed, isAttackPhase); // добавляем круг игрока
                    enemyCircle = new EnemyCircle(getContext(),  circleSize, isAttackPhase);
                }
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            enemyCircle.drow(paint, canvas);
            playerCircle.drow(paint, canvas); // рисуем корабль

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package ru.mirea.playedu.view.gameviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.lifecycle.ViewModelProvider;

import java.util.Random;

import ru.mirea.playedu.viewmodel.GameViewModel;

public class GameView extends SurfaceView implements Runnable {
    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали

    private boolean firstTime = true;
    private boolean gameRunning = true;
    private boolean isAttackPhase;
    private boolean isEnemyColide = false;
    private int circleSize;
    private int circleSpeed;
    // Разброс размеров круга игрока
    private final int minSize = 100;
    private final int maxSize = 300;
    private PlayerCircle playerCircle;
    private EnemyCircle enemyCircle;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;


    public GameView(Context context, int speed, boolean phase) {
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

    public GameView(Context context, int size, int speed, boolean phase) {
        super(context);
        // Задаем фазу боя
        isAttackPhase = phase;
        // Задаём начальные параметры для кругов
        circleSize = size;
        circleSpeed = speed;
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
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

    private void checkCollision() {
        isEnemyColide = playerCircle.isCollision(enemyCircle.size);
    }

    public boolean isEnemyColide() {
        return isEnemyColide;
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if(firstTime){ // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                if (isAttackPhase) {
                    Random random = new Random();
                    int playerSize = minSize + (maxSize - minSize) * random.nextInt();
                    playerCircle = new PlayerCircle(getContext(), 0, playerSize, isAttackPhase); // добавляем круг игрока
                    enemyCircle = new EnemyCircle(getContext(), circleSpeed, 10, isAttackPhase);
                }
                else {
                    playerCircle = new PlayerCircle(getContext(), circleSpeed, 20, isAttackPhase); // добавляем круг игрока
                    enemyCircle = new EnemyCircle(getContext(), 0, circleSize, isAttackPhase);
                }
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas

            playerCircle.drow(paint, canvas); // рисуем корабль
            enemyCircle.drow(paint, canvas);

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

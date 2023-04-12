package ru.mirea.playedu.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.FragmentGameBinding;
import ru.mirea.playedu.databinding.FragmentQuestsBinding;
import ru.mirea.playedu.model.UserTask;
import ru.mirea.playedu.view.dialog.EnemyPreviewDialog;
import ru.mirea.playedu.view.dialog.StartGameDialog;
import ru.mirea.playedu.view.gameviews.GameView;
import ru.mirea.playedu.viewmodel.GameViewModel;
import ru.mirea.playedu.viewmodel.QuestsViewModel;


public class GameFragment extends Fragment {
    private FragmentGameBinding binding;
    private GameViewModel gameViewModel;
    public static boolean isPlayerPressed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(getLayoutInflater());
        // Инициализация ViewModel
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        StartGameDialog dialog = new StartGameDialog();
        dialog.show(getActivity().getSupportFragmentManager(), "Start game dialog");

        // Проверка на то, что игрок нажал на кнопку начала приключения
        gameViewModel.getStartGame().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    dialog.dismiss();
                    startGame();
                }
            }
        });

        // Проверка на то, что игрок закончил фазу полготовки
        gameViewModel.getIsBattle().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    gameViewModel.setPlayer();
                    gameViewModel.setIsAttack(true);
                }
            }
        });

        gameViewModel.getIsAttack().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    attackPhase();
                }
            }
        });

        return binding.getRoot();
    }

    public void startGame() {
        gameViewModel.createEnemyList();
        gameIteration(gameViewModel.getCurrentEnemyId());
    }

    public void gameIteration(int enemyId) {
        EnemyPreviewDialog dialog = new EnemyPreviewDialog(enemyId);
        dialog.show(getActivity().getSupportFragmentManager(), "Enemy game dialog");
    }

    public void attackPhase() {
        boolean phase = gameViewModel.getIsAttack().getValue();
        int speed = gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId()).getAttackPhaseSpeed();
        GameView gameView = new GameView(getContext(), speed, phase); // создаём gameView
        gameView.setZOrderOnTop(true);
        binding.gameLayout.addView(gameView);
        binding.gameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameView.isEnemyColide()) {
                    gameViewModel.makeHit();
                    binding.healthEnemyBar.setProgress(gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId()).getHealth());
                }
            }
        });
        /*binding.gameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        isPlayerPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isPlayerPressed = false;
                        break;
                }
                return true;
            }
        });*/
    }
}
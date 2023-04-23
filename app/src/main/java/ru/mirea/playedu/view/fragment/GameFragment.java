package ru.mirea.playedu.view.fragment;

import static ru.mirea.playedu.Constants.getRandomNumber;
import static ru.mirea.playedu.Constants.icePowerKoef;
import static ru.mirea.playedu.Constants.maxHealth;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.mirea.playedu.Constants.PowerStatus;
import ru.mirea.playedu.Constants.Powers;
import ru.mirea.playedu.Constants.PhaseResult;
import ru.mirea.playedu.Constants.BattleResult;
import ru.mirea.playedu.R;
import ru.mirea.playedu.databinding.FragmentGameBinding;
import ru.mirea.playedu.databinding.FragmentQuestsBinding;
import ru.mirea.playedu.model.Enemy;
import ru.mirea.playedu.model.Player;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.model.UserTask;
import ru.mirea.playedu.view.dialog.ActivePowerDialog;
import ru.mirea.playedu.view.dialog.DamageDialog;
import ru.mirea.playedu.view.dialog.EnemyPreviewDialog;
import ru.mirea.playedu.view.dialog.FightEndDialog;
import ru.mirea.playedu.view.dialog.StartGameDialog;
import ru.mirea.playedu.view.gameviews.GameView;
import ru.mirea.playedu.viewmodel.GameViewModel;
import ru.mirea.playedu.viewmodel.QuestsViewModel;


public class GameFragment extends Fragment {
    private FragmentGameBinding binding;
    private GameViewModel gameViewModel;
    private EnemyPreviewDialog enemyPreviewDialog;
    private DamageDialog damageDialog;
    private FightEndDialog fightEndDialog;
    public static boolean isPlayerPressed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(getLayoutInflater());
        // Инициализация ViewModel
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        StartGameDialog dialog = new StartGameDialog();
        dialog.setCancelable(false);
        dialog.show(getActivity().getSupportFragmentManager(), "Start game dialog");
        binding.clickableArea.setVisibility(View.GONE);
        gameViewModel.setIsFragmentEnter(true);
        // Проверка на то, что игрок нажал на кнопку начала приключения
        gameViewModel.getStartGame().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!gameViewModel.getIsFragmentEnter()) {
                    if (aBoolean) {
                        dialog.dismiss();
                        gameViewModel.setPlayer();
                        binding.healthPlayerBar.setMax(gameViewModel.getPlayer().getHealth());
                        gameViewModel.reloadAllPowersStatus(true);
                        startGame();
                    }
                    else {
                        dialog.dismiss();
                        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
                        NavController navController = navHostFragment.getNavController();
                        navController.popBackStack();
                    }
                }
            }
        });

        // Проверка на то, что игрок закончил фазу полготовки или тольк её начал
        gameViewModel.getIsBattle().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.clickableArea.setVisibility(View.VISIBLE);
                    enemyPreviewDialog.dismiss();
                    if (gameViewModel.getCurrentEnemyId() == 0) {
                        setPowersImg();
                        gameViewModel.addPassivePowersEffects();
                    }
                    gameViewModel.reloadAllPowersStatus(false);
                    binding.healthEnemyBar.setMax(gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId()).getHealth());
                    gameViewModel.setIsAttack(true);
                }
                else {
                    if (gameViewModel.isAllEnemyKilled()) {
                        gameViewModel.restartGame();
                    }
                    else {
                        gameViewModel.setCurrentEnemy(gameViewModel.getCurrentEnemyId() + 1);
                        gameIteration(gameViewModel.getCurrentEnemyId());
                    }
                }
            }
        });

        // Проверка на фазу боя
        gameViewModel.getIsAttack().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.gameLayout.removeAllViewsInLayout();
                if (gameViewModel.isPlayerKilled()) {
                    if (!gameViewModel.useActivePower(Powers.LIFE_POWER)) {
                        binding.clickableArea.setVisibility(View.GONE);
                        gameViewModel.setBattleResult(BattleResult.DEFEAT);
                        fightEndDialog = new FightEndDialog();
                        fightEndDialog.setCancelable(false);
                        fightEndDialog.show(getActivity().getSupportFragmentManager(), "Fight end dialog");
                    }
                    return;
                }
                if (gameViewModel.isEnemyKilled()) {
                    binding.clickableArea.setVisibility(View.GONE);
                    if (gameViewModel.isAllEnemyKilled()) gameViewModel.setBattleResult(BattleResult.WIN_ADVENTURE);
                    else gameViewModel.setBattleResult(BattleResult.WIN_BATTLE);
                    fightEndDialog = new FightEndDialog();
                    fightEndDialog.setCancelable(false);
                    fightEndDialog.show(getActivity().getSupportFragmentManager(), "Fight end dialog");
                    return;
                }
                binding.healthPlayerBar.setProgress(gameViewModel.getPlayer().getHealth());
                binding.healthEnemyBar.setProgress(gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId()).getHealth());
                if (aBoolean) {
                    attackPhase();
                }
                else {
                    if (!(gameViewModel.getCurrentPhaseResult() == PhaseResult.DEAL_DAMAGE) || !gameViewModel.useActivePower(Powers.ICE_POWER)) {
                        defensePhase();
                    }
                }
            }
        });

        // Обработка действий связанных с активными способностями
        gameViewModel.getCurrentActivePower().observe(getViewLifecycleOwner(), new Observer<Power>() {
            @Override
            public void onChanged(Power power) {
                ActivePowerDialog activePowerDialog;
                switch (power.getPowerType()) {
                    case FIRE_POWER:
                        activePowerDialog = new ActivePowerDialog(power);
                        activePowerDialog.show(getActivity().getSupportFragmentManager(), "Active power dialog");
                        break;
                    case ICE_POWER:
                        if (getRandomNumber(0, 100) >= 100 * icePowerKoef) {
                            activePowerDialog = new ActivePowerDialog(power);
                            activePowerDialog.show(getActivity().getSupportFragmentManager(), "Active power dialog");
                        }
                        else {
                            defensePhase();
                        }
                        break;
                    case TIME_POWER:
                        gameViewModel.decrementPlayerMistakeCount();
                        activePowerDialog = new ActivePowerDialog(power);
                        activePowerDialog.show(getActivity().getSupportFragmentManager(), "Active power dialog");
                        break;
                    case HEALTH_POWER:
                        binding.healthPlayerBar.setProgress(gameViewModel.getPlayer().getHealth());
                        activePowerDialog = new ActivePowerDialog(power);
                        activePowerDialog.show(getActivity().getSupportFragmentManager(), "Active power dialog");
                        break;
                    case GRIFFIN_POWER:
                        enemyPreviewDialog.dismiss();
                        activePowerDialog = new ActivePowerDialog(power);
                        activePowerDialog.show(getActivity().getSupportFragmentManager(), "Active power dialog");
                        break;
                    case LIFE_POWER:
                        if (power.getPowerStatus() == PowerStatus.AVAILABLE) {
                            gameViewModel.revivePlayer();
                            binding.healthPlayerBar.setProgress(gameViewModel.getPlayer().getHealth());
                            activePowerDialog = new ActivePowerDialog(power);
                            activePowerDialog.show(getActivity().getSupportFragmentManager(), "Active power dialog");
                        }
                        else {
                            gameViewModel.setBattleResult(BattleResult.DEFEAT);
                            fightEndDialog = new FightEndDialog();
                            fightEndDialog.setCancelable(false);
                            fightEndDialog.show(getActivity().getSupportFragmentManager(), "Fight end dialog");
                        }
                        break;
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
        enemyPreviewDialog = new EnemyPreviewDialog(enemyId);
        enemyPreviewDialog.setCancelable(false);
        enemyPreviewDialog.show(getActivity().getSupportFragmentManager(), "Enemy game dialog");
    }

    public void attackPhase() {
        binding.phaseTitle.setText(R.string.attack);
        Enemy enemy = gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId());
        float speed = enemy.getAttackPhaseSpeed() * 0.01f;
        GameView gameView = new GameView(getContext(), speed, true); // создаём gameView
        gameView.setZOrderOnTop(true);
        binding.gameLayout.addView(gameView);
        CountDownTimer countDownTimer = new CountDownTimer(enemy.getPhaseTime() * 1000L, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.timerTitle.setText(Long.toString(millisUntilFinished / 1000));
            }

            public void onFinish() {
                gameView.endGame();
                if (gameViewModel.useActivePower(Powers.POISON_POWER)) {
                    gameViewModel.setPhaseResult(PhaseResult.POISON_POWER);
                }
                {
                    gameViewModel.setPhaseResult(PhaseResult.MISHIT);
                }
                if (gameViewModel.getPlayerMistakeCount() == 0) {
                    damageDialog = new DamageDialog();
                    damageDialog.show(getActivity().getSupportFragmentManager(), "Phase result dialog");
                }
                else if (!gameViewModel.useActivePower(Powers.TIME_POWER)) {
                    damageDialog = new DamageDialog();
                    damageDialog.show(getActivity().getSupportFragmentManager(), "Phase result dialog");
                }
            }

        }.start();
        binding.clickableArea.setOnTouchListener(null);
        binding.clickableArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameView.isEnemyColide()) {
                    gameViewModel.setPhaseResult(PhaseResult.DEAL_DAMAGE);
                    gameViewModel.makeHitEnemy();
                }
                else {
                    if (gameViewModel.useActivePower(Powers.POISON_POWER)) {
                        gameViewModel.setPhaseResult(PhaseResult.POISON_POWER);
                    }
                    else {
                        gameViewModel.setPhaseResult(PhaseResult.MISHIT);
                    }
                }
                countDownTimer.cancel();
                gameView.endGame();
                if (gameViewModel.getCurrentPhaseResult() == PhaseResult.DEAL_DAMAGE || gameViewModel.getPlayerMistakeCount() == 0) {
                    gameViewModel.useActivePower(Powers.STUDENT_POWER);
                    damageDialog = new DamageDialog();
                    damageDialog.show(getActivity().getSupportFragmentManager(), "Phase result dialog");
                }
                else if (!gameViewModel.useActivePower(Powers.TIME_POWER)) {
                    damageDialog = new DamageDialog();
                    damageDialog.show(getActivity().getSupportFragmentManager(), "Phase result dialog");
                }
            }
        });
    }

    public void defensePhase() {
        binding.phaseTitle.setText(R.string.defense);
        Enemy enemy = gameViewModel.getEnemy(gameViewModel.getCurrentEnemyId());
        float speed = enemy.getDefensePhaseSpeed()* 0.01f;
        int size = enemy.getDefencePhaseSpread();
        GameView gameView = new GameView(getContext(), size, speed, false); // создаём gameView
        gameView.setZOrderOnTop(true);
        binding.gameLayout.addView(gameView);

        CountDownTimer countDownTimer = new CountDownTimer(enemy.getPhaseTime() * 1000L, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.timerTitle.setText(Long.toString(millisUntilFinished / 1000));
                if (millisUntilFinished > 500) gameView.updateObjectsSizes();
            }

            public void onFinish() {
                gameView.endGame();
                if (gameView.isEnemyColide()) {
                    gameViewModel.setPhaseResult(PhaseResult.AVOID_DAMAGE);
                }
                else {
                    gameViewModel.setPhaseResult(PhaseResult.GET_DAMAGE);
                }
                if (gameViewModel.getCurrentPhaseResult() == PhaseResult.AVOID_DAMAGE) {
                    damageDialog = new DamageDialog();
                    damageDialog.show(getActivity().getSupportFragmentManager(), "Phase result dialog");
                }
                else if (gameViewModel.getPlayerMistakeCount() == 0) {
                    gameViewModel.makeHitPlayer();
                    damageDialog = new DamageDialog();
                    damageDialog.show(getActivity().getSupportFragmentManager(), "Phase result dialog");
                }
                else if (!gameViewModel.useActivePower(Powers.TIME_POWER)) {
                    gameViewModel.makeHitPlayer();
                    damageDialog = new DamageDialog();
                    damageDialog.show(getActivity().getSupportFragmentManager(), "Phase result dialog");
                }
            }

        }.start();
        binding.clickableArea.setOnTouchListener(new View.OnTouchListener() {
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
        });
    }

    // Установка иконок сил в нижней части экрана
    private void setPowersImg() {
        ArrayList<Power> selectedPowers = gameViewModel.getSelectedPowersList().getValue();
        binding.firstPower.setImageResource(selectedPowers.get(0).getIcon());
        binding.secondPower.setImageResource(selectedPowers.get(1).getIcon());
        binding.thirdPower.setImageResource(selectedPowers.get(2).getIcon());
    }
}
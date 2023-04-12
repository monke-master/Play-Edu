package ru.mirea.playedu.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.playedu.R;
import ru.mirea.playedu.model.Enemy;
import ru.mirea.playedu.viewmodel.GameViewModel;

public class EnemyPreviewDialog extends DialogFragment {

    private View view;
    private int enemyId;
    private GameViewModel gameViewModel;

    public EnemyPreviewDialog(int enemyId) {
        this.enemyId = enemyId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = requireActivity().getLayoutInflater().inflate(
                R.layout.dialog_enemy_preview,
                null,
                false);

        // Фон диалога
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        view = requireActivity().getLayoutInflater().
                inflate(R.layout.dialog_enemy_preview, null, false);
        builder.setView(view);
        TextView enemyTitle = (TextView) view.findViewById(R.id.hdr);
        TextView healthNum = (TextView) view.findViewById(R.id.health_txt);
        ProgressBar healthBar = (ProgressBar) view.findViewById(R.id.health_progress_bar);
        TextView damageNum = (TextView) view.findViewById(R.id.damage_txt);
        ProgressBar damageBar = (ProgressBar) view.findViewById(R.id.damage_progress_bar);
        Button beginBattleBtn = (Button) view.findViewById(R.id.button2);

        // Инициализация ViewModel
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        Enemy enemy = gameViewModel.getEnemy(enemyId);
        enemyTitle.setText(String.format("Сражение с %s", enemy.getName()));
        healthNum.setText(enemy.getHealth());
        healthBar.setProgress(enemy.getHealth());
        damageNum.setText(enemy.getDamage());
        damageBar.setProgress(enemy.getDamage());
        beginBattleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameViewModel.setIsBattle(true);
            }
        });

        return builder.create();
    }
}

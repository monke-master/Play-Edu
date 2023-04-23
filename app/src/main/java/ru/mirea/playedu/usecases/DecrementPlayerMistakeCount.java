package ru.mirea.playedu.usecases;

import android.util.Log;

import ru.mirea.playedu.data.repository.PlayerRepository;
import ru.mirea.playedu.model.Player;

public class DecrementPlayerMistakeCount {
    PlayerRepository playerRepository;

    public DecrementPlayerMistakeCount(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void execute() {
        Player player = playerRepository.getPlayer();
        player.setMistakeCount(player.getMistakeCount() - 1);
        playerRepository.setPlayer(player);
    }
}

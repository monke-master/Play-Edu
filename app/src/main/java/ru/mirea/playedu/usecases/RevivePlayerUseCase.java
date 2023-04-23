package ru.mirea.playedu.usecases;

import ru.mirea.playedu.data.repository.PlayerRepository;
import ru.mirea.playedu.model.Player;

public class RevivePlayerUseCase {
    PlayerRepository playerRepository;

    public RevivePlayerUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void execute() {
        Player player = playerRepository.getPlayer();
        player.setHealth(1);
        player.setDamage(1);
        playerRepository.setPlayer(player);
    }
}

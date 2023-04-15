package ru.mirea.playedu.usecases;

import ru.mirea.playedu.data.repository.PlayerRepository;
import ru.mirea.playedu.model.Player;

public class CreatePlayerUseCase {
    PlayerRepository playerRepository;
    public CreatePlayerUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void execute() {
        Player player = new Player();
        player.setHealth(5);
        player.setDamage(3);
        player.setMistakeCount(0);
        playerRepository.setPlayer(player);
    }
}

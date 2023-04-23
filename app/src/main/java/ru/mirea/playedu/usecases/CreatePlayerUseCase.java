package ru.mirea.playedu.usecases;

import static ru.mirea.playedu.Constants.playerDamage;
import static ru.mirea.playedu.Constants.playerHealth;

import ru.mirea.playedu.data.repository.PlayerRepository;
import ru.mirea.playedu.model.Player;

public class CreatePlayerUseCase {
    PlayerRepository playerRepository;
    public CreatePlayerUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void execute() {
        Player player = new Player();
        player.setHealth(playerHealth);
        player.setDamage(playerDamage);
        player.setMistakeCount(0);
        playerRepository.setPlayer(player);
    }
}

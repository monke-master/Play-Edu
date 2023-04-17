package ru.mirea.playedu.usecases;

import ru.mirea.playedu.data.repository.EnemyRepository;
import ru.mirea.playedu.data.repository.PlayerRepository;
import ru.mirea.playedu.model.Enemy;
import ru.mirea.playedu.model.Player;

public class MakeHitPlayerUseCase {
    private PlayerRepository playerRepository;
    private EnemyRepository enemyRepository;

    public MakeHitPlayerUseCase(PlayerRepository playerRepository, EnemyRepository enemyRepository) {
        this.playerRepository = playerRepository;
        this.enemyRepository = enemyRepository;
    }

    public void execute(int enemyId) {
        Enemy enemy = enemyRepository.getEnemyById(enemyId);
        Player player = playerRepository.getPlayer();
        player.setHealth(player.getHealth() - enemy.getDamage());
    }
}

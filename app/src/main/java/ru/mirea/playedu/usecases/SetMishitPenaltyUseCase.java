package ru.mirea.playedu.usecases;

import ru.mirea.playedu.data.repository.EnemyRepository;
import ru.mirea.playedu.model.Enemy;

public class SetMishitPenaltyUseCase {
    EnemyRepository enemyRepository;

    public SetMishitPenaltyUseCase(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;
    }

    public void execute(int enemyId) {
        Enemy enemy = enemyRepository.getEnemyById(enemyId);
        enemy.setDefencePhaseSpread((int)(enemy.getDefencePhaseSpread() * 1.15));
        enemyRepository.updateEnemy(enemyId, enemy);
    }
}

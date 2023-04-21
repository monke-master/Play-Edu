package ru.mirea.playedu.usecases;

import static ru.mirea.playedu.Constants.getRandomNumber;
import static ru.mirea.playedu.Constants.healthPowerKoef;
import static ru.mirea.playedu.Constants.playerHealth;

import ru.mirea.playedu.Constants.Powers;
import ru.mirea.playedu.data.repository.EnemyRepository;
import ru.mirea.playedu.data.repository.PlayerRepository;
import ru.mirea.playedu.model.Enemy;
import ru.mirea.playedu.model.Player;
import ru.mirea.playedu.model.Power;

public class ActivePowersChangeStatsUseCase {
    EnemyRepository enemyRepository;
    PlayerRepository playerRepository;

    public ActivePowersChangeStatsUseCase(EnemyRepository enemyRepository, PlayerRepository playerRepository) {
        this.enemyRepository = enemyRepository;
        this.playerRepository = playerRepository;
    }

    public void execute(Powers powerType, int enemyId) {
        Player player = playerRepository.getPlayer();
        Enemy enemy = enemyRepository.getEnemyById(enemyId);
        switch (powerType) {
            case SPELL_BOOK_POWER:
                int paramNum = getRandomNumber(0, 17);
                switch (paramNum) {
                    case 0:
                        enemy.setHealth((int)(enemy.getHealth() * 0.5));
                        break;
                    case 1:
                        enemy.setHealth((int)(enemy.getHealth() * 1.5));
                        break;
                    case 2:
                        enemy.setDamage((int)(enemy.getDamage() * 0.5));
                        break;
                    case 3:
                        enemy.setDamage((int)(enemy.getDamage() * 1.5));
                        break;
                    case 4:
                        enemy.setDefencePhaseSpread((int)(enemy.getDefencePhaseSpread() * 0.5));
                        break;
                    case 5:
                        enemy.setDefencePhaseSpread((int)(enemy.getDefencePhaseSpread() * 1.5));
                        break;
                    case 6:
                        enemy.setPhaseTime((int)(enemy.getPhaseTime() * 0.5));
                        break;
                    case 7:
                        enemy.setPhaseTime((int)(enemy.getPhaseTime() * 1.5));
                        break;
                    case 8:
                        enemy.setDefensePhaseSpeed((int)(enemy.getDefensePhaseSpeed() * 0.5));
                    case 9:
                        enemy.setDefensePhaseSpeed((int)(enemy.getDefensePhaseSpeed() * 1.5));
                    case 10:
                        enemy.setAttackPhaseSpeed((int)(enemy.getAttackPhaseSpeed() * 0.5));
                        break;
                    case 11:
                        enemy.setAttackPhaseSpeed((int)(enemy.getAttackPhaseSpeed() * 1.5));
                        break;
                    case 12:
                        player.setHealth((int)(player.getHealth() * 0.5));
                        break;
                    case 13:
                        player.setHealth((int)(player.getHealth() * 1.5));
                        break;
                    case 14:
                        player.setDamage((int)(player.getDamage() * 0.5));
                        break;
                    case 15:
                        player.setDamage((int)(player.getDamage() * 1.5));
                        break;
                    case 16:
                        player.setMistakeCount(player.getMistakeCount() + 3);
                        break;
                }
                break;
            case FIRE_POWER:
                enemy.setHealth(enemy.getHealth() - 1);
                break;
            case HEALTH_POWER:
                if (player.getHealth() < playerHealth) player.setHealth(player.getHealth() + healthPowerKoef);
                break;
            case GRIFFIN_POWER:
                enemy.setHealth(0);
                break;
            case LIFE_POWER:
                player.setHealth(1);
                break;
            case STUDENT_POWER:
                if (player.getHealth() < playerHealth) player.setHealth(player.getHealth() + 1);
        }
        playerRepository.setPlayer(player);
        enemyRepository.updateEnemy(enemyId, enemy);
    }
}

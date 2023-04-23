package ru.mirea.playedu.usecases;

import static ru.mirea.playedu.Constants.bloodPowerKoef;
import static ru.mirea.playedu.Constants.earthPowerKoef;
import static ru.mirea.playedu.Constants.mistakesCount;
import static ru.mirea.playedu.Constants.moneyPowerKoef;
import static ru.mirea.playedu.Constants.speedPowerKoef;
import static ru.mirea.playedu.Constants.windPowerKoef;
import static ru.mirea.playedu.Constants.wisdomPowerEnemyKoef;
import static ru.mirea.playedu.Constants.wisdomPowerPlayerKoef;

import android.util.Log;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.EnemyRepository;
import ru.mirea.playedu.data.repository.PlayerRepository;
import ru.mirea.playedu.data.repository.PowerRepository;
import ru.mirea.playedu.model.Enemy;
import ru.mirea.playedu.model.Player;
import ru.mirea.playedu.model.Power;

public class AddedPassivePowersEffectsUseCase {
    PlayerRepository playerRepository;
    EnemyRepository enemyRepository;

    public AddedPassivePowersEffectsUseCase(PlayerRepository playerRepository, EnemyRepository enemyRepository) {
        this.playerRepository = playerRepository;
        this.enemyRepository = enemyRepository;
    }

    public void execute(int enemyId, ArrayList<Power> powers) {
        Player player = playerRepository.getPlayer();
        Enemy enemy = enemyRepository.getEnemyById(enemyId);
        for (Power power : powers) {
            switch (power.getPowerType()) {
                // Сила "Денежный мешок"
                case MONEY_BAG:
                    enemy.setPrice((int)(enemy.getPrice() * (1 + moneyPowerKoef)));
                    break;
                // Сила земли
                case EARTH_POWER:
                    enemy.setDefencePhaseSpread((int)(enemy.getDefencePhaseSpread() * (1 - earthPowerKoef)));
                    break;
                // Сила ветра
                case WIND_POWER:
                    enemy.setAttackPhaseSpeed((int)(enemy.getAttackPhaseSpeed() * (1 - windPowerKoef)));
                    break;
                // Сила времени
                case TIME_POWER:
                    player.setMistakeCount(mistakesCount);
                    break;
                // Сила скорости
                case SPEED_POWER:
                    enemy.setPhaseTime(enemy.getPhaseTime() + speedPowerKoef);
                    break;
                // Сила крови
                case BLOOD_POWER:
                    player.setHealth(player.getHealth() + bloodPowerKoef);
                    break;
                // Сила мудрости
                case WISDOM_POWER:
                    player.setDamage(player.getDamage() + wisdomPowerPlayerKoef);
                    enemy.setDamage((int)(enemy.getDamage() * (1 + wisdomPowerEnemyKoef)));
                    enemy.setHealth((int)(enemy.getHealth() * (1 + wisdomPowerEnemyKoef)));
                    enemy.setAttackPhaseSpeed((int)(enemy.getAttackPhaseSpeed() * (1 + wisdomPowerEnemyKoef)));
                    enemy.setDefensePhaseSpeed((int)(enemy.getDefensePhaseSpeed() * (1 + wisdomPowerEnemyKoef)));
                    enemy.setDefencePhaseSpread((int)(enemy.getDefencePhaseSpread() * (1 + wisdomPowerEnemyKoef)));
                    break;
            }
        }
        playerRepository.setPlayer(player);
        enemyRepository.updateEnemy(enemyId, enemy);
    }
}

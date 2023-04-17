package ru.mirea.playedu.usecases;

import static ru.mirea.playedu.Constants.attackSpeedKoef;
import static ru.mirea.playedu.Constants.damageKoef;
import static ru.mirea.playedu.Constants.defenseSpeedKoef;
import static ru.mirea.playedu.Constants.defenseSpreadKoef;
import static ru.mirea.playedu.Constants.enemiesCount;
import static ru.mirea.playedu.Constants.getRandomNumber;
import static ru.mirea.playedu.Constants.healthKoef;
import static ru.mirea.playedu.Constants.maxAttackSpeed;
import static ru.mirea.playedu.Constants.maxDamage;
import static ru.mirea.playedu.Constants.maxDefenseSpeed;
import static ru.mirea.playedu.Constants.maxDefenseSpread;
import static ru.mirea.playedu.Constants.maxHealth;
import static ru.mirea.playedu.Constants.maxNamesInd;
import static ru.mirea.playedu.Constants.maxTime;
import static ru.mirea.playedu.Constants.minAttackSpeed;
import static ru.mirea.playedu.Constants.minDamage;
import static ru.mirea.playedu.Constants.minDefenseSpeed;
import static ru.mirea.playedu.Constants.minDefenseSpread;
import static ru.mirea.playedu.Constants.minHealth;
import static ru.mirea.playedu.Constants.minNamesInd;
import static ru.mirea.playedu.Constants.minTime;
import static ru.mirea.playedu.Constants.monsters;
import static ru.mirea.playedu.Constants.monstersNames;
import static ru.mirea.playedu.Constants.timeKoef;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


import ru.mirea.playedu.Constants;
import ru.mirea.playedu.R;
import ru.mirea.playedu.data.repository.EnemyRepository;
import ru.mirea.playedu.model.Enemy;

public class CreateEnemyListUseCase {
    // Репозиторий для противников
    EnemyRepository enemyRepository;


    public CreateEnemyListUseCase(EnemyRepository enemyRepository) {
        monsters.put("Одноглазом", R.drawable.enemy_session);
        monsters.put("Трёхглазом", R.drawable.enemy_session);
        monsters.put("Сессией", R.drawable.enemy_session);
        this.enemyRepository = enemyRepository;
    }

    public void execute() {
        enemyRepository.deleteAllEnemies();
        for (int i = 0; i < enemiesCount; i++) {
            enemyRepository.addEnemy(createEnemy(i));
        }
    }

    public Enemy createEnemy(int index) {
        Random random = new Random();
        Enemy enemy = new Enemy();
        enemy.setEnemyId(index);
        enemy.setHealth(getRandomNumber(minHealth, maxHealth + 1));
        enemy.setDamage(getRandomNumber(minDamage, maxDamage + 1));
        int nameInd = getRandomNumber(minNamesInd, minNamesInd + 1);
        enemy.setName(monstersNames[nameInd]);
        enemy.setImageId(monsters.get(monstersNames[nameInd]));
        enemy.setAttackPhaseSpeed(getRandomNumber(minAttackSpeed, maxAttackSpeed + 1));
        enemy.setDefensePhaseSpeed(getRandomNumber(minDefenseSpeed, maxDefenseSpeed + 1));
        enemy.setDefencePhaseSpread(getRandomNumber(minDefenseSpread, maxDefenseSpread + 1));
        enemy.setPhaseTime(getRandomNumber(minTime, maxTime + 1));
        int price = (int)(enemy.getHealth() * healthKoef + enemy.getDamage() * damageKoef + enemy.getAttackPhaseSpeed() * attackSpeedKoef + enemy.getDefensePhaseSpeed() * defenseSpeedKoef + enemy.getDefencePhaseSpread() * defenseSpreadKoef + enemy.getPhaseTime() * timeKoef);
        enemy.setPrice(price);
        return enemy;
    }
}

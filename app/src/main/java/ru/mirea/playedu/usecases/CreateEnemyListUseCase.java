package ru.mirea.playedu.usecases;

import static ru.mirea.playedu.Constants.attackSpeedKoef;
import static ru.mirea.playedu.Constants.bossAppearancePercent;
import static ru.mirea.playedu.Constants.bossPrice;
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
        monsters.put("Одноглазом", R.drawable.oneeye_enemy);
        monsters.put("Трёхглазом", R.drawable.pic_enemy);
        monsters.put("Сессией", R.drawable.enemy_session);
        this.enemyRepository = enemyRepository;
    }

    public void execute() {
        enemyRepository.deleteAllEnemies();
        for (int i = 0; i < enemiesCount; i++) {
            if (i == enemiesCount - 1 && getRandomNumber(0, 101) > bossAppearancePercent) {
                enemyRepository.addEnemy(createBossEnemy(i));
            }
            else {
                enemyRepository.addEnemy(createEnemy(i));
            }
        }
    }

    public Enemy createEnemy(int index) {
        Enemy enemy = new Enemy();
        enemy.setEnemyId(index);
        enemy.setHealth(getRandomNumber(minHealth, maxHealth + 1));
        enemy.setDamage(getRandomNumber(minDamage, maxDamage + 1));
        int nameInd = getRandomNumber(minNamesInd, maxNamesInd);
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

    public Enemy createBossEnemy(int index) {
        Enemy enemy = new Enemy();
        enemy.setEnemyId(index);
        enemy.setHealth(maxHealth * 2);
        enemy.setDamage(maxDamage * 2);
        enemy.setName(monstersNames[maxNamesInd]);
        enemy.setImageId(monsters.get(monstersNames[maxNamesInd]));
        enemy.setAttackPhaseSpeed((int)(maxAttackSpeed * 1.5));
        enemy.setDefensePhaseSpeed((int)(maxDefenseSpeed * 1.5));
        enemy.setDefencePhaseSpread((int)(maxDefenseSpread * 1.5));
        enemy.setPhaseTime((int)(minTime / 1.5));
        enemy.setPrice(bossPrice);
        return enemy;
    }
}

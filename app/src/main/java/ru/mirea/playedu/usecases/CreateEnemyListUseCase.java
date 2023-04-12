package ru.mirea.playedu.usecases;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ru.mirea.playedu.R;
import ru.mirea.playedu.data.repository.EnemyRepository;
import ru.mirea.playedu.model.Enemy;

public class CreateEnemyListUseCase {
    // Репозиторий для противников
    EnemyRepository enemyRepository;
    // Разброс здоровья монстров
    private final int minHealth = 3;
    private final int maxHealth = 8;
    // Разброс урона монстров
    private final int minDamage = 3;
    private final int maxDamage = 8;
    // Список имён сонстров
    private final String[] monstersNames = {"Одноглазом", "Трёхглазом", "Сессией"};
    // Словарь имен сонстров и их картинок
    private Map<String, Integer> monsters = new HashMap<String, Integer>();
    // Разброс имён монстров
    private final int minNamesInd = 0;
    private final int maxNamesInd = 2;
    // Разброс cкорости в фазе атаки
    private final int minAttackSpeed = 2;
    private final int maxAttackSpeed = 20;
    // Разброс cкорости в фазе защиты
    private final int minDefenseSpeed = 2;
    private final int maxDefenseSpeed = 20;
    // Разброс в фазе защиты
    private final int minDefenseSpread = 2;
    private final int maxDefenseSpread = 20;
    // Разброс времени фазы
    private final int minTime = 5;
    private final int maxTime = 10;
    // Коэффициенты сложности для рассчета награды
    private final float healthKoef = 0.1f;
    private final float damageKoef = 0.1f;
    private final float attackSpeedKoef = 0.1f;
    private final float defenseSpeedKoef = 0.1f;
    private final float defenseSpreadKoef = 0.1f;
    private final float timeKoef = 0.1f;
    // Количество противников
    private final int enemiesCount = 5;

    public CreateEnemyListUseCase(EnemyRepository enemyRepository) {
        monsters.put("Одноглазом", R.drawable.enemy_session);
        monsters.put("Трёхглазом", R.drawable.enemy_session);
        monsters.put("Сессией", R.drawable.enemy_session);
        this.enemyRepository = enemyRepository;
    }

    public void execute() {
        for (int i = 0; i < enemiesCount; i++) {
            enemyRepository.addEnemy(createEnemy(i));
        }
    }

    public Enemy createEnemy(int index) {
        Random random = new Random();
        Enemy enemy = new Enemy();
        enemy.setEnemyId(index);
        enemy.setHealth(minHealth + (maxHealth - minHealth) * random.nextInt());
        enemy.setDamage(minDamage + (maxDamage - minDamage) * random.nextInt());
        int nameInd = minNamesInd + (maxNamesInd - minNamesInd) * random.nextInt();
        enemy.setName(monstersNames[nameInd]);
        enemy.setImageId(monsters.get(monstersNames[nameInd]));
        enemy.setAttackPhaseSpeed(minAttackSpeed + (maxAttackSpeed - minAttackSpeed) * random.nextInt());
        enemy.setDefensePhaseSpeed(minDefenseSpeed + (maxDefenseSpeed - minDefenseSpeed) * random.nextInt());
        enemy.setDefencePhaseSpread(minDefenseSpread + (maxDefenseSpread - minDefenseSpread) * random.nextInt());
        enemy.setPhaseTime(minTime + (maxTime - minTime) * random.nextInt());
        int price = (int)(enemy.getHealth() * healthKoef + enemy.getDamage() * damageKoef + enemy.getAttackPhaseSpeed() * attackSpeedKoef + enemy.getDefensePhaseSpeed() * defenseSpeedKoef + enemy.getDefencePhaseSpread() * defenseSpreadKoef + enemy.getPhaseTime() * timeKoef);
        enemy.setPrice(price);
        return enemy;
    }
}

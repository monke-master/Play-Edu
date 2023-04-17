package ru.mirea.playedu.data.storage.cache;

import java.util.ArrayList;

import ru.mirea.playedu.model.Enemy;


// Класс для хранения врагов
// Реализует паттерн Singleton
public class EnemyCacheStorage {

    // Список врагов
    private ArrayList<Enemy> enemies;

    private static EnemyCacheStorage instance = null;

    private EnemyCacheStorage() {
        enemies = new ArrayList<>();
    }

    // Возвращает экземпляр класса
    // Если экземпляра нет, создает новый
    public static EnemyCacheStorage getInstance() {
        if (instance == null)
            instance = new EnemyCacheStorage();

        return instance;
    }

    // Возвращает врага с заданным id, если он есть в списке
    // Иначе возвращает null
    public Enemy getEnemyById(int id) {
        for (Enemy enemy: enemies) {
            if (enemy.getEnemyId() == id) {
                return enemy;
            }
        }
        return null;
    }

    // Возвращает список всех врагов
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    // Добавляет врага в список
    public void addEnemy(Enemy category) {
        enemies.add(category);
    }

    // Заменяет врага с заданным id на нового
    public boolean updateEnemy(int enemyId, Enemy newEnemy) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getEnemyId() == enemyId) {
                enemies.set(i, newEnemy);
                return true;
            }
        }
        return false;
    }

    // Удаляет врага с заданным id
    public boolean deleteEnemy(Enemy enemy) {
        return enemies.remove(enemy);
    }

    // Удалает всех врагов
    public void deleteAllEnemies() {
        enemies.clear();
    }


}

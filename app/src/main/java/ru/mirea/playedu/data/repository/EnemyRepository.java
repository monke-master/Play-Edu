package ru.mirea.playedu.data.repository;

import java.util.ArrayList;

import ru.mirea.playedu.data.storage.cache.EnemyCacheStorage;
import ru.mirea.playedu.model.Enemy;
import ru.mirea.playedu.model.Response;

// Репозиторий для хранения данных о врагах
public class EnemyRepository {

    private final EnemyCacheStorage cacheStorage;

    public EnemyRepository(EnemyCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    // Возвращает врага с заданным id, если он есть в списке
    // Иначе возвращает null
    public Enemy getEnemyById(int enemyId) {
        return cacheStorage.getEnemyById(enemyId);
    }

    // Возвращает список всех врагов
    public ArrayList<Enemy> getEnemies() {
        return cacheStorage.getEnemies();
    }

    // Добавляет врага в список
    public Response addEnemy(Enemy enemy) {
        cacheStorage.addEnemy(enemy);
        return new Response(200, "Success");
    }

    // Заменяет врага с заданным id на нового
    public Response updateEnemy(int enemyId, Enemy newEnemy) {
        if (cacheStorage.updateEnemy(enemyId, newEnemy))
            return new Response(200, "Success");
        else
            return new Response(404, "Not found");
    }

    // Удаляет врага с заданным id
    public Response deleteEnemy(Enemy enemy) {
        if (cacheStorage.deleteEnemy(enemy))
            return new Response(200, "Success");
        else
            return new Response(404, "Not found");
    }

    // Удаляет всех противников
    public void deleteAllEnemies() {
        cacheStorage.deleteAllEnemies();
    }
}

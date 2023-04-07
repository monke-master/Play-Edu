package ru.mirea.playedu.data.repository;

import java.util.ArrayList;

import ru.mirea.playedu.data.storage.cache.PowerCacheStorage;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.model.RepositoryResponse;

// Репозиторий для сил пользователя
public class PowerRepository {

    private PowerCacheStorage cacheStorage;

    public PowerRepository(PowerCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    // Возвращает силу с заданным id, если она есть с списке
    // Иначе возвращает null
    public Power getPowerById(int id) {
        return cacheStorage.getPowerById(id);
    }

    // Возвращает список сил
    public ArrayList<Power> getPowers() {
        return cacheStorage.getPowers();
    }

    // Добавляет силу
    public RepositoryResponse addPower(Power power) {
        cacheStorage.addPower(power);
        return new RepositoryResponse(200, "Success");
    }

    // Заменяет силу с заданным id на новую
    public RepositoryResponse updatePower(int id, Power newPower) {
        if (cacheStorage.updatePower(id, newPower))
            return new RepositoryResponse(200, "Success");
        else
            return new RepositoryResponse(404, "Not found");
    }

    // Удаление силы с заданным id
    public RepositoryResponse deletePower(Power power) {
        if (cacheStorage.deletePower(power))
            return new RepositoryResponse(200, "Success");
        else
            return new RepositoryResponse(404, "Not found");
    }
}

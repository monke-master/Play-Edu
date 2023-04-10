package ru.mirea.playedu.data.repository;

import java.util.ArrayList;

import ru.mirea.playedu.data.storage.cache.PowerCacheStorage;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.model.Response;

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
    public Response addPower(Power power) {
        cacheStorage.addPower(power);
        return new Response(200, "Success");
    }

    // Заменяет силу с заданным id на новую
    public Response updatePower(int id, Power newPower) {
        if (cacheStorage.updatePower(id, newPower))
            return new Response(200, "Success");
        else
            return new Response(404, "Not found");
    }

    // Удаление силы с заданным id
    public Response deletePower(Power power) {
        if (cacheStorage.deletePower(power))
            return new Response(200, "Success");
        else
            return new Response(404, "Not found");
    }
}

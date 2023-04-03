package ru.mirea.playedu.data.storage.cache;

import java.util.ArrayList;

import ru.mirea.playedu.model.Power;

// Класс для хранения сил пользователя в кэше
// Реализует паттерн Singleton
public class PowerCacheStorage {

    private ArrayList<Power> powers;

    private static PowerCacheStorage instance = null;

    private PowerCacheStorage() {
        powers = new ArrayList<>();
    }

    // Возвращает экземпляр класса
    // Если экземпляра нет, создает новый
    private static PowerCacheStorage getInstance() {
        if (instance == null)
            instance = new PowerCacheStorage();

        return instance;
    }

    // Возвращает силу с заданным id, если она есть с списке
    // Иначе возвращает null
    public Power getPowerById(int id) {
        for (Power power: powers) {
            if (power.getPowerId() == id)
                return power;
        }
        return null;
    }

    // Возвращает список сил
    public ArrayList<Power> getPowers() {
        return powers;
    }

    // Добавляет силу
    public void addPower(Power power) {
        powers.add(power);
    }

    // Заменяет силу с заданным id на новую
    public boolean updatePower(int powerId, Power newPower) {
        for (int i = 0; i < powers.size(); i++) {
            if (powers.get(i).getPowerId() == powerId) {
                powers.set(i, newPower);
                return true;
            }
        }
        return false;
    }

    // Удаление силы с заданным id
    public boolean deletePower(Power power) {
        return powers.remove(power);
    }
}

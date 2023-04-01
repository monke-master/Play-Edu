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

    private static PowerCacheStorage getInstance() {
        if (instance == null)
            instance = new PowerCacheStorage();

        return instance;
    }


    public ArrayList<Power> getPowers() {
        return powers;
    }

    public void addPower(Power power) {
        powers.add(power);
    }

    public boolean updatePower(int powerId, Power power) {
        for (int i = 0; i < powers.size(); i++) {
            if (powers.get(i).getPowerId() == powerId) {
                powers.set(i, power);
                return true;
            }
        }

        return false;
    }

    public boolean deletePower(Power power) {
        return powers.remove(power);
    }
}

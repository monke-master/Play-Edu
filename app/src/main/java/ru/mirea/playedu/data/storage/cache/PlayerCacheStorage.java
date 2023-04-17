package ru.mirea.playedu.data.storage.cache;

import java.util.ArrayList;

import ru.mirea.playedu.model.Player;

// Класс для хранения данных игрока
// Реализует паттерн Singleton
public class PlayerCacheStorage {

    private Player player;

    private static PlayerCacheStorage instance = null;

    private PlayerCacheStorage() {

    }

    // Возвращает экземпляр класса
    // Если экземпляра нет, создает новый
    public static PlayerCacheStorage getInstance() {
        if (instance == null)
            instance = new PlayerCacheStorage();
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}

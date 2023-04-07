package ru.mirea.playedu.data.repository;

import ru.mirea.playedu.data.storage.cache.PlayerCacheStorage;
import ru.mirea.playedu.model.Player;

// Репозиторий для хранения данных игрока
public class PlayerRepository {

    private PlayerCacheStorage cacheStorage;

    public PlayerRepository(PlayerCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    public Player getPlayer() {
        return cacheStorage.getPlayer();
    }

    public void setPlayer(Player player) {
        cacheStorage.setPlayer(player);
    }
}

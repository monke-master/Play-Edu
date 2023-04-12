package ru.mirea.playedu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.playedu.data.repository.EnemyRepository;
import ru.mirea.playedu.data.repository.PlayerRepository;
import ru.mirea.playedu.data.repository.UserTaskRepository;
import ru.mirea.playedu.data.storage.cache.EnemyCacheStorage;
import ru.mirea.playedu.data.storage.cache.PlayerCacheStorage;
import ru.mirea.playedu.model.Enemy;
import ru.mirea.playedu.usecases.CreateEnemyListUseCase;
import ru.mirea.playedu.usecases.CreatePlayerUseCase;
import ru.mirea.playedu.usecases.MakeHitUseCase;
import ru.mirea.playedu.view.gameviews.EnemyCircle;

public class GameViewModel extends ViewModel {
    // Объявление репозитория игрока
    private final PlayerRepository playerRepository;
    // Объявление репозитория монстров
    private final EnemyRepository enemyRepository;
    // Use case создания списка противников
    CreateEnemyListUseCase createEnemyListUseCase;
    // Use case создания игрока
    CreatePlayerUseCase createPlayerUseCase;
    // Use case удара по противнику
    MakeHitUseCase makeHitUseCase;
    // Текущий противник
    private int currentEnemy = 0;
    // Текущая фаза
    private final MutableLiveData<Boolean> isAttack = new MutableLiveData<>();
    // Триггер начала сражения
    private final MutableLiveData<Boolean> isBattle = new MutableLiveData<>();

    private final MutableLiveData<Boolean> startGame = new MutableLiveData<>();

    public GameViewModel() {
        playerRepository = new PlayerRepository(PlayerCacheStorage.getInstance());
        enemyRepository = new EnemyRepository(EnemyCacheStorage.getInstance());
        createEnemyListUseCase = new CreateEnemyListUseCase(enemyRepository);
        createPlayerUseCase = new CreatePlayerUseCase(playerRepository);
        makeHitUseCase = new MakeHitUseCase(playerRepository, enemyRepository);
    }

    public void setStartGame(boolean state) {
        startGame.setValue(state);
    }

    public LiveData<Boolean> getStartGame() {
        return startGame;
    }

    public void createEnemyList() {
        createEnemyListUseCase.execute();
    }

    public Enemy getEnemy(int index) {
        return enemyRepository.getEnemyById(index);
    }

    public void setIsAttack(boolean state) {
        isAttack.setValue(state);
    }

    public LiveData<Boolean> getIsAttack() {
        return isAttack;
    }

    public void setIsBattle(boolean state) {
        isBattle.setValue(state);
    }

    public LiveData<Boolean> getIsBattle() {
        return isBattle;
    }

    public void setCurrentEnemy(int currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public int getCurrentEnemyId() {
        return currentEnemy;
    }

    public void setPlayer() {
        createPlayerUseCase.execute();
    }

    public boolean isEnemyKilled() {
        return getEnemy(getCurrentEnemyId()).getHealth() <= 0;
    }

    public void makeHit() {
        makeHitUseCase.execute(currentEnemy);
    }
}

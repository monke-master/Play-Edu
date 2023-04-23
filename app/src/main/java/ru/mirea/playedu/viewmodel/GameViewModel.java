package ru.mirea.playedu.viewmodel;

import static ru.mirea.playedu.Constants.selectablePower;
import static ru.mirea.playedu.Constants.speedPowerKoef;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.mirea.playedu.Constants.Powers;
import ru.mirea.playedu.Constants.BattleResult;
import ru.mirea.playedu.Constants.PhaseResult;
import ru.mirea.playedu.data.repository.EnemyRepository;
import ru.mirea.playedu.data.repository.PlayerRepository;
import ru.mirea.playedu.data.repository.PowerRepository;
import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.data.storage.cache.EnemyCacheStorage;
import ru.mirea.playedu.data.storage.cache.PlayerCacheStorage;
import ru.mirea.playedu.data.storage.cache.PowerCacheStorage;
import ru.mirea.playedu.data.storage.cache.UserCacheStorage;
import ru.mirea.playedu.databinding.ActivityLaunchBinding;
import ru.mirea.playedu.model.Enemy;
import ru.mirea.playedu.model.Player;
import ru.mirea.playedu.model.Power;
import ru.mirea.playedu.model.User;
import ru.mirea.playedu.usecases.ActivePowersChangeStatsUseCase;
import ru.mirea.playedu.usecases.AddedPassivePowersEffectsUseCase;
import ru.mirea.playedu.usecases.CreateEnemyListUseCase;
import ru.mirea.playedu.usecases.CreatePlayerUseCase;
import ru.mirea.playedu.usecases.DecrementPlayerMistakeCount;
import ru.mirea.playedu.usecases.DisablePowerUseCase;
import ru.mirea.playedu.usecases.GetBoughtPowersUseCase;
import ru.mirea.playedu.usecases.GetUserUseCase;
import ru.mirea.playedu.usecases.MakeHitEnemyUseCase;
import ru.mirea.playedu.usecases.MakeHitPlayerUseCase;
import ru.mirea.playedu.usecases.ReloadAllPowersStatus;
import ru.mirea.playedu.usecases.RevivePlayerUseCase;
import ru.mirea.playedu.usecases.SetAdventureRewardUseCase;
import ru.mirea.playedu.usecases.SetMishitPenaltyUseCase;

public class GameViewModel extends ViewModel {
    // Перечисление результатов фазы
    private PhaseResult phaseResult;
    // Перечисление результатов стражения
    private BattleResult battleResult;
    // Перечисление обрабатываемых сил
    private Powers activePowers;
    // Объявление репозитория игрока
    private final PlayerRepository playerRepository;
    // Объявление репозитория монстров
    private final EnemyRepository enemyRepository;
    // Объявление репозитория сил
    private final PowerRepository powerRepository;
    // Объявление репозитория пользователя
    private final UserRepository userRepository;
    // Use case создания списка противников
    CreateEnemyListUseCase createEnemyListUseCase;
    // Use case создания игрока
    CreatePlayerUseCase createPlayerUseCase;
    // Use case удара по противнику
    MakeHitEnemyUseCase makeHitEnemyUseCase;
    // Use case получения урона игроком
    MakeHitPlayerUseCase makeHitPlayerUseCase;
    // Use case получения купленных сил
    GetBoughtPowersUseCase getBoughtPowersUseCase;
    // Use case обработки пассивных способностей;
    AddedPassivePowersEffectsUseCase addedPassivePowersEffectsUseCase;
    // Use case изменения параметров от обрабатываемой силы
    ActivePowersChangeStatsUseCase activePowersChangeStatsUseCase;
    // Use case назначения штрафа за промах в фазе атаки
    SetMishitPenaltyUseCase setMishitPenaltyUseCase;
    // Use case уменьшения количества ошибок игрока
    DecrementPlayerMistakeCount decrementPlayerMistakeCount;
    // Use case дизактивации силы
    DisablePowerUseCase disablePowerUseCase;
    // Use case востановления активности сил
    ReloadAllPowersStatus reloadAllPowersStatus;
    // Use case возрождения игрока
    RevivePlayerUseCase revivePlayerUseCase;
    // Use case получения пользователя
    GetUserUseCase getUserUseCase;
    // Use case получения награды за вылазку
    SetAdventureRewardUseCase setAdventureRewardUseCase;
    // Текущий противник
    private int currentEnemy = 0;
    // Заход на экран?
    private boolean isFragmentEnter = true;
    // Текущая фаза
    private final MutableLiveData<Boolean> isAttack = new MutableLiveData<>();
    // Триггер начала сражения
    private final MutableLiveData<Boolean> isBattle = new MutableLiveData<>();
    // Триггер начала игры
    private final MutableLiveData<Boolean> startGame = new MutableLiveData<>();
    // Список выбранных сил
    private final MutableLiveData<ArrayList<Power>> selectedPowers = new MutableLiveData<>();
    // Список купленных сил
    private final MutableLiveData<ArrayList<Power>> boughtPowers = new MutableLiveData<>();
    // Использующаяся активная сила
    private final MutableLiveData<Power> currentActivePower = new MutableLiveData<>();


    public GameViewModel() {
        playerRepository = new PlayerRepository(PlayerCacheStorage.getInstance());
        enemyRepository = new EnemyRepository(EnemyCacheStorage.getInstance());
        powerRepository = new PowerRepository(PowerCacheStorage.getInstance());
        userRepository = new UserRepository(UserCacheStorage.getInstance());
        createEnemyListUseCase = new CreateEnemyListUseCase(enemyRepository);
        createPlayerUseCase = new CreatePlayerUseCase(playerRepository);
        makeHitEnemyUseCase = new MakeHitEnemyUseCase(playerRepository, enemyRepository);
        makeHitPlayerUseCase = new MakeHitPlayerUseCase(playerRepository, enemyRepository);
        getBoughtPowersUseCase = new GetBoughtPowersUseCase(powerRepository);
        addedPassivePowersEffectsUseCase = new AddedPassivePowersEffectsUseCase(playerRepository, enemyRepository);
        activePowersChangeStatsUseCase = new ActivePowersChangeStatsUseCase(enemyRepository, playerRepository);
        setMishitPenaltyUseCase = new SetMishitPenaltyUseCase(enemyRepository);
        decrementPlayerMistakeCount = new DecrementPlayerMistakeCount(playerRepository);
        disablePowerUseCase = new DisablePowerUseCase(powerRepository);
        reloadAllPowersStatus = new ReloadAllPowersStatus(powerRepository);
        revivePlayerUseCase = new RevivePlayerUseCase(playerRepository);
        getUserUseCase = new GetUserUseCase(userRepository);
        setAdventureRewardUseCase = new SetAdventureRewardUseCase(userRepository, enemyRepository);
        setEmptySelectedPowersList();
        getData();
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

    public Player getPlayer() { return playerRepository.getPlayer(); }

    public boolean isEnemyKilled() {
        return getEnemy(getCurrentEnemyId()).getHealth() <= 0;
    }

    public boolean isPlayerKilled() {
        return playerRepository.getPlayer().getHealth() <= 0;
    }

    public void makeHitEnemy() {
        makeHitEnemyUseCase.execute(currentEnemy);
    }

    public void makeHitPlayer() { makeHitPlayerUseCase.execute(currentEnemy); }

    public void setPhaseResult(PhaseResult phaseResult) { this.phaseResult = phaseResult; }

    public PhaseResult getCurrentPhaseResult() { return phaseResult; }

    public BattleResult getCurrentBattleResult() {
        return battleResult;
    }

    public void setBattleResult(BattleResult battleResult) {
        this.battleResult = battleResult;
    }

    public boolean isAllEnemyKilled() {
        return enemyRepository.getEnemies().size() == currentEnemy + 1;
    }

    public int getEnemiesLeft() {
        return enemyRepository.getEnemies().size() - currentEnemy - 1;
    }

    public void restartGame() {
        currentEnemy = 0;
        startGame.setValue(true);
    }

    private void setEmptySelectedPowersList() {
        ArrayList<Power> selectablePowers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            selectablePowers.add(selectablePower);
        }
        selectedPowers.setValue(selectablePowers);
    }

    public LiveData<ArrayList<Power>> getSelectedPowersList() {
        return selectedPowers;
    }

    public void updateSelectedPowersList(Power power, int index) {
        ArrayList<Power> newPowers = selectedPowers.getValue();
        newPowers.set(index, power);
        selectedPowers.setValue(newPowers);
    }

    private void getData() {
        boughtPowers.setValue(getBoughtPowersUseCase.execute());
    }

    public LiveData<ArrayList<Power>> getBoughtPowers() {
        return boughtPowers;
    }

    public LiveData<Power> getCurrentActivePower() {
        return currentActivePower;
    }
    public void addPassivePowersEffects() {
        addedPassivePowersEffectsUseCase.execute(currentEnemy, selectedPowers.getValue());
    }

    public boolean useActivePower(Powers power) {
        for (Power elem : selectedPowers.getValue()) {
            if (elem.getPowerType() == power) {
                activePowersChangeStatsUseCase.execute(power, currentEnemy);
                currentActivePower.setValue(elem);
                return true;
            }
        }
        return false;
    }

    public void setMishitPenalty() {
        setMishitPenaltyUseCase.execute(currentEnemy);
    }

    public void decrementPlayerMistakeCount() {
        decrementPlayerMistakeCount.execute();
    }

    public int getPlayerMistakeCount() {
        return getPlayer().getMistakeCount();
    }

    public void disablePower(Power power) {
        disablePowerUseCase.execute(power);
    }

    public void reloadAllPowersStatus(boolean includeOnceUsedPowers) {
        reloadAllPowersStatus.execute(includeOnceUsedPowers);
    }

    public void revivePlayer() {
        revivePlayerUseCase.execute();
    }

    public User getUser() {
        return getUserUseCase.execute();
    }

    public int setAdventureReward() {
        return setAdventureRewardUseCase.execute();
    }

    public boolean getIsFragmentEnter() {
        return isFragmentEnter;
    }

    public void setIsFragmentEnter(boolean state) {
        isFragmentEnter = state;
    }
}

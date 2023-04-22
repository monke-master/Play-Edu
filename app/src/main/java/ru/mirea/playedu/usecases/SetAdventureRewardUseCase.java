package ru.mirea.playedu.usecases;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.EnemyRepository;
import ru.mirea.playedu.data.repository.UserRepository;
import ru.mirea.playedu.model.Enemy;
import ru.mirea.playedu.model.User;

public class SetAdventureRewardUseCase {
    UserRepository userRepository;
    EnemyRepository enemyRepository;

    public SetAdventureRewardUseCase(UserRepository userRepository, EnemyRepository enemyRepository) {
        this.userRepository = userRepository;
        this.enemyRepository = enemyRepository;
    }

    public int execute() {
        User user = userRepository.getUser();
        ArrayList<Enemy> enemies = enemyRepository.getEnemies();
        int reward = 0;
        for (Enemy enemy : enemies) {
            reward += enemy.getPrice();
        }
        user.setGoldenCoins(reward);
        userRepository.updateUser(user);
        return reward;
    }
}

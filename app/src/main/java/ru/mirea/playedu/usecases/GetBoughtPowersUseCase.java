package ru.mirea.playedu.usecases;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.PowerRepository;
import ru.mirea.playedu.model.Power;

// UseCase получения списка сил игрока
public class GetBoughtPowersUseCase {

    private PowerRepository powerRepository;

    public GetBoughtPowersUseCase(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }

    public ArrayList<Power> execute() {
        ArrayList<Power> powers = powerRepository.getPowers();
        ArrayList<Power> boughtPowers = new ArrayList<>();
        for (Power power: powers) {
            if (power.isBought())
                boughtPowers.add(power);
        }
        return boughtPowers;
    }
}

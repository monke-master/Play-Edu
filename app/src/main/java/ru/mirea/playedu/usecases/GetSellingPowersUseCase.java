package ru.mirea.playedu.usecases;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.PowerRepository;
import ru.mirea.playedu.model.Power;

// UseCase получения списка продающихся сил
public class GetSellingPowersUseCase {

    private PowerRepository powerRepository;

    public GetSellingPowersUseCase(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }

    public ArrayList<Power> execute() {
        ArrayList<Power> powers = powerRepository.getPowers();
        ArrayList<Power> sellingPowers = new ArrayList<>();
        for (Power power: powers) {
            if (!power.isBought())
                sellingPowers.add(power);
        }
        return sellingPowers;
    }
}

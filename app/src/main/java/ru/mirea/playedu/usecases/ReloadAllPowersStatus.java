package ru.mirea.playedu.usecases;

import java.util.ArrayList;

import ru.mirea.playedu.Constants.PowerStatus;
import ru.mirea.playedu.Constants.Powers;
import ru.mirea.playedu.data.repository.PowerRepository;
import ru.mirea.playedu.model.Power;

public class ReloadAllPowersStatus {
    PowerRepository powerRepository;

    public ReloadAllPowersStatus(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }

    public void execute(boolean includeOnceUsedPowers) {
        ArrayList<Powers> onceUsedPowersTypes = new ArrayList<>();
        onceUsedPowersTypes.add(Powers.GRIFFIN_POWER);
        onceUsedPowersTypes.add(Powers.LIFE_POWER);
        onceUsedPowersTypes.add(Powers.STUDENT_POWER);
        if (includeOnceUsedPowers) {
            for (Power power : powerRepository.getPowers()) {
                power.setPowerStatus(PowerStatus.AVAILABLE);
                powerRepository.updatePower(power.getPowerId(), power);
            }
        }
        else {
            for (Power power : powerRepository.getPowers()) {
                if (!onceUsedPowersTypes.contains(power.getPowerType())) {
                    power.setPowerStatus(PowerStatus.AVAILABLE);
                    powerRepository.updatePower(power.getPowerId(), power);
                }
            }
        }
    }

}

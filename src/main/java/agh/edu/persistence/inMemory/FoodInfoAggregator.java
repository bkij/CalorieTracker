package agh.edu.persistence.inMemory;

import agh.edu.model.FoodInfo;
import agh.edu.persistence.FoodInfoPersistence;

import java.util.List;
import java.util.function.Predicate;

public class FoodInfoAggregator implements FoodInfoPersistence {
    @Override
    public void initializePersistence() {

    }

    @Override
    public void finalizePersistence() {

    }

    @Override
    public void save(FoodInfo info) {

    }

    @Override
    public List<FoodInfo> getByPredicate(Predicate<FoodInfo> predicate) {
        return null;
    }
}

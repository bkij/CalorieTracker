package agh.edu.persistence;

import agh.edu.model.FoodInfo;

import java.util.List;
import java.util.function.Predicate;

public interface FoodInfoPersistence {
    void initializePersistence();
    void finalizePersistence();
    void save(FoodInfo info);
    List<FoodInfo> getByPredicate(Predicate<FoodInfo> predicate);
}

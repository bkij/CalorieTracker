package agh.edu.storage;

import agh.edu.model.FoodInfo;

import java.util.List;
import java.util.function.Predicate;

public interface FoodInfoStorage {
    void initializeStorage();
    void finalizeStorage();
    void save(FoodInfo info);
    List<FoodInfo> getByPredicate(Predicate<FoodInfo> predicate);
}

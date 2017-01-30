package agh.edu.storage;

import agh.edu.model.Statistic;

import java.util.Set;
import java.util.function.Predicate;

public interface StatisticsStorage {
    void initializeStorage();
    void finalizeStorage();
    void save(Statistic stat);
    Set<Statistic> getByPredicate(Predicate<Statistic> predicate);
}

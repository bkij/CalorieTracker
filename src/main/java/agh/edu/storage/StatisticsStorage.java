package agh.edu.storage;

import agh.edu.model.Statistic;

import java.util.Set;
import java.util.function.Predicate;

public interface StatisticsStorage {
    void initializePersistence();
    void finalizePersistence();
    void save(Statistic stat);
    Set<Statistic> getByPredicate(Predicate<Statistic> predicate);
}

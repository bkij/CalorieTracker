package agh.edu.persistence;

import agh.edu.model.Statistic;

import java.util.Set;
import java.util.function.Predicate;

public interface StatisticPersistence {
    void initializePersistence();
    void finalizePersistence();
    void save(Statistic stat);
    Set<Statistic> getByPredicate(Predicate<Statistic> predicate);
}

package agh.edu.persistence;

import agh.edu.model.Statistic;

import java.util.List;
import java.util.function.Predicate;

public interface StatisticPersistence {
    void intializePersistence();
    void finalizePersistence();
    void save(Statistic stat);
    List<Statistic> getByPredicate(Predicate<Statistic> predicate);
}

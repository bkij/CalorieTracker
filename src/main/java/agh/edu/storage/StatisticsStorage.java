package agh.edu.storage;

import agh.edu.model.Statistic;

import java.time.LocalDate;
import java.util.Optional;
import java.util.SortedSet;
import java.util.function.Predicate;

public interface StatisticsStorage {
    void initializeStorage();
    void finalizeStorage();
    void save(Statistic stat);
    SortedSet<Statistic> getByPredicate(Predicate<Statistic> predicate);
    Optional<Statistic> getByDate(LocalDate date);
}

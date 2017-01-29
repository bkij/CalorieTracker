package agh.edu.persistence.inMemory;

import agh.edu.model.Statistic;
import agh.edu.persistence.StatisticPersistence;

import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;

public class StatisticAggregator implements StatisticPersistence {
    TreeSet<Statistic> stats;
    @Override
    public void initializePersistence() {

    }

    @Override
    public void finalizePersistence() {

    }

    @Override
    public void save(Statistic stat) {

    }

    @Override
    public List<Statistic> getByPredicate(Predicate<Statistic> predicate) {
        return null;
    }
}

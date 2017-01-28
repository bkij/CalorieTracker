package agh.edu.persistence.inMemory;

import agh.edu.model.Statistic;
import agh.edu.persistence.StatisticPersistence;

import java.util.List;
import java.util.function.Predicate;

public class StatisticAggregator implements StatisticPersistence {
    @Override
    public void intializePersistence() {

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

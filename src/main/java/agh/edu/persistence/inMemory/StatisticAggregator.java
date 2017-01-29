package agh.edu.persistence.inMemory;

import agh.edu.exceptions.PersistenceException;
import agh.edu.model.Statistic;
import agh.edu.persistence.StatisticPersistence;
import agh.edu.util.Utils;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StatisticAggregator implements StatisticPersistence {
    private TreeSet<Statistic> stats;
    private final String fileDir = "./";
    private final String fileName = "stats.ctdb";

    @Override
    public void initializePersistence() {
        try (FileInputStream statsInputStream = new FileInputStream(fileDir + fileName); ObjectInputStream statsReader = new ObjectInputStream(statsInputStream)) {
            stats = (TreeSet<Statistic>)statsReader.readObject();
        } catch(IOException ex) {
            if(Utils.fileExsts(fileDir, fileName)) {
                throw new PersistenceException("Statistic file exists but cannot be opened.");
            }
        } catch(ClassCastException | ClassNotFoundException ex) {
            throw new PersistenceException("Wrong statistic file format.");
        }
    }

    @Override
    public void finalizePersistence() {
        try (FileOutputStream statsOutputStream = new FileOutputStream(fileDir + fileName); ObjectOutputStream statsWriter = new ObjectOutputStream(statsOutputStream)) {
            statsWriter.writeObject(stats);
        } catch(IOException ex) {
            throw new PersistenceException("Cannot write to stats file");
        }
    }

    @Override
    public void save(Statistic stat) {
        stats.add(stat);
    }

    @Override
    public Set<Statistic> getByPredicate(Predicate<Statistic> predicate) {
        return stats.stream().filter(predicate).collect(Collectors.toSet());
    }
}

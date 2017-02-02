package agh.edu.storage.inMemory;

import agh.edu.exceptions.PersistenceException;
import agh.edu.model.Statistic;
import agh.edu.storage.StatisticsStorage;
import agh.edu.util.Utils;

import java.io.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StatisticFileStorage implements StatisticsStorage {
    private TreeSet<Statistic> stats;
    private final String fileDir;
    private final String fileName;

    public StatisticFileStorage(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.fileName = fileName;
        this.stats = new TreeSet<>();
    }

    @Override
    public void initializeStorage() {
        try (FileInputStream statsInputStream = new FileInputStream(fileDir + fileName); ObjectInputStream statsReader = new ObjectInputStream(statsInputStream)) {
            stats = (TreeSet<Statistic>)statsReader.readObject();
        } catch(IOException ex) {
            if(Utils.fileExists(fileDir, fileName)) {
                throw new PersistenceException("Statistic file exists but cannot be opened.");
            }
        } catch(ClassCastException | ClassNotFoundException ex) {
            throw new PersistenceException("Wrong statistic file format.");
        }
    }

    @Override
    public void finalizeStorage() {
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
    public SortedSet<Statistic> getByPredicate(Predicate<Statistic> predicate) {
        return new TreeSet<>(stats.stream().filter(predicate).collect(Collectors.toSet()));
    }

    @Override
    public Optional<Statistic> getByDate(LocalDate date) {
        return stats.stream().filter(stat -> stat.getDate().equals(date)).findFirst();
    }
}

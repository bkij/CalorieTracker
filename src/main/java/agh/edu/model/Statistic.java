package agh.edu.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Statistic implements Serializable, Comparable<Statistic> {
    private final LocalDate date;
    private double totalKcal;
    private double totalProt;
    private double totalCarbs;
    private double totalFat;

    public Statistic() {
        this(LocalDate.now());
    }

    public Statistic(LocalDate date) {
        this(date, 0, 0, 0, 0);
    }

    public Statistic(LocalDate date, double totalKcal, double totalProt, double totalCarbs, double totalFat) {
        this.date = date;
        this.totalKcal = totalKcal;
        this.totalProt = totalProt;
        this.totalCarbs = totalCarbs;
        this.totalFat = totalFat;
    }

    @Override
    public int compareTo(Statistic statistic) {
        return this.date.compareTo(statistic.getDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistic statistic = (Statistic) o;

        return date.equals(statistic.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotalKcal() {
        return totalKcal;
    }

    public void addKcal(double kcal) {
        this.totalKcal += kcal;
    }

    public double getTotalProt() {
        return totalProt;
    }

    public void addProt(double prot) {
        this.totalProt += prot;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public void addCarbs(double carbs) {
        this.totalCarbs += carbs;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void addFat(double fat) {
        this.totalFat += fat;
    }
}

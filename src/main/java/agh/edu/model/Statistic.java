package agh.edu.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Statistic implements Serializable {
    private final LocalDate date;
    private double totalKcal;
    private double totalProt;
    private double totalCarbs;
    private double totalFat;

    public Statistic() {
        date = LocalDate.now();
        totalKcal = 0.0;
        totalProt = 0.0;
        totalCarbs = 0.0;
        totalFat = 0.0;
    }

    public double getTotalKcal() {
        return totalKcal;
    }

    public void setTotalKcal(double totalKcal) {
        this.totalKcal = totalKcal;
    }

    public double getTotalProt() {
        return totalProt;
    }

    public void setTotalProt(double totalProt) {
        this.totalProt = totalProt;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }
}

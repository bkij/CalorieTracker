package agh.edu.model.observable;

import agh.edu.model.Statistic;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class ObservableStatistic {
    private ObjectProperty<LocalDate> dateProperty;
    private DoubleProperty kcalProperty;
    private DoubleProperty protProperty;
    private DoubleProperty carbsProperty;
    private DoubleProperty fatProperty;

    public ObservableStatistic(LocalDate date, double totalKcal, double totalProt, double totalCarbs, double totalFat) {
        this.dateProperty = new SimpleObjectProperty<>(date);
        this.kcalProperty = new SimpleDoubleProperty(totalKcal);
        this.protProperty = new SimpleDoubleProperty(totalProt);
        this.carbsProperty = new SimpleDoubleProperty(totalCarbs);
        this.fatProperty = new SimpleDoubleProperty(totalFat);
    }

    public static ObservableStatistic fromStatistic(Statistic stat) {
        return new ObservableStatistic(stat.getDate(), stat.getTotalKcal(), stat.getTotalProt(), stat.getTotalCarbs(), stat.getTotalFat());
    }

    public Statistic toStatistic() {
        return new Statistic(dateProperty.get(), kcalProperty.get(), protProperty.get(), carbsProperty.get(), fatProperty.get());
    }

    public LocalDate getDate() { return dateProperty.get();}

    public ObjectProperty<LocalDate> getDateProperty() { return dateProperty; }

    public DoubleProperty getKcalProperty() {
        return kcalProperty;
    }

    public DoubleProperty getProtProperty() {
        return protProperty;
    }

    public DoubleProperty getCarbsProperty() {
        return carbsProperty;
    }

    public DoubleProperty getFatProperty() {
        return fatProperty;
    }

    public void addKcal(double kcal) {
        kcalProperty.setValue(kcalProperty.getValue() + kcal);
    }
    public void addProt(double prot) {
        protProperty.setValue(protProperty.getValue() + prot);
    }
    public void addCarbs(double carbs) {
        carbsProperty.setValue(carbsProperty.getValue() + carbs);
    }
    public void addFat(double fat) {
        fatProperty.setValue(fatProperty.getValue() + fat);
    }
}

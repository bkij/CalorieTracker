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

    public ObservableStatistic(LocalDate dateProperty, double totalKcal, double totalProt, double totalCarbs, double totalFat) {
        this.dateProperty = new SimpleObjectProperty<>(dateProperty);
        this.kcalProperty = new SimpleDoubleProperty(totalKcal);
        this.protProperty = new SimpleDoubleProperty(totalProt);
        this.carbsProperty = new SimpleDoubleProperty(totalCarbs);
        this.fatProperty = new SimpleDoubleProperty(totalFat);
    }

    public static ObservableStatistic fromStatistic(Statistic stat) {
        return new ObservableStatistic(stat.getDate(), stat.getTotalKcal(), stat.getTotalProt(), stat.getTotalCarbs(), stat.getTotalFat());
    }

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
}

package agh.edu.model.observable;

import agh.edu.model.Statistic;
import agh.edu.model.UserConfig;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class CurrentInfo {
    private ObjectProperty<ObservableStatistic> currentDayStats;
    private ObjectProperty<ObservableUserConfig> currentUserConfig;

    public CurrentInfo(Statistic currentDayStats, UserConfig currentUserConfig) {
        this.currentDayStats = new SimpleObjectProperty<>(ObservableStatistic.fromStatistic(currentDayStats));
        this.currentUserConfig = new SimpleObjectProperty<>(ObservableUserConfig.fromUserConfig(currentUserConfig));
    }

    public ObjectProperty<ObservableStatistic> observableStatisticProperty() {
        return currentDayStats;
    }

    public ObjectProperty<ObservableUserConfig> observableUserConfigProperty() {
        return currentUserConfig;
    }

    public ObservableUserConfig getObservableConfig() {
        return currentUserConfig.get();
    }

    public ObservableStatistic getObservableStatistic() {
        return currentDayStats.get();
    }

    public void setCurrentDayStats(Statistic newDayStats) {
        currentDayStats.setValue(ObservableStatistic.fromStatistic(newDayStats));
    }
}

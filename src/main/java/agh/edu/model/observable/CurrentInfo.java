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
        // TODO: binding/listening here?
    }

    public ObservableUserConfig getObservableConfig() {
        return currentUserConfig.get();
    }

    public ObservableStatistic getObservableStatistic() {
        return currentDayStats.get();
    }
}

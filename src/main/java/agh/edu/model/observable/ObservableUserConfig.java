package agh.edu.model.observable;

import agh.edu.model.UserConfig;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObservableUserConfig {
    private StringProperty userNameProperty;
    private DoubleProperty BMRProperty;
    private DoubleProperty protProperty;
    private DoubleProperty carbProperty;
    private DoubleProperty fatProperty;

    public ObservableUserConfig(String userName, double BMR, double prot, double carbs, double fat) {
        this.userNameProperty = new SimpleStringProperty(userName);
        this.BMRProperty = new SimpleDoubleProperty(BMR);
        this.protProperty = new SimpleDoubleProperty(prot);
        this.carbProperty = new SimpleDoubleProperty(carbs);
        this.fatProperty = new SimpleDoubleProperty(fat);
    }

    public static ObservableUserConfig fromUserConfig(UserConfig userConfig) {
        return new ObservableUserConfig(userConfig.getUserName(), userConfig.getBMR(), userConfig.getProtein(), userConfig.getCarbs(), userConfig.getFat());
    }

    public DoubleProperty getBMRProperty() {
        return BMRProperty;
    }

    public StringProperty getUserNameProperty() {
        return userNameProperty;
    }

    public DoubleProperty getProtProperty() {
        return protProperty;
    }

    public DoubleProperty getCarbProperty() {
        return carbProperty;
    }

    public DoubleProperty getFatProperty() {
        return fatProperty;
    }
}

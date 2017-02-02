package agh.edu.model.viewable;

import agh.edu.model.FoodInfo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewableFoodInfo {
    private final StringProperty name;
    private final DoubleProperty kcal;
    private final DoubleProperty prot;
    private final DoubleProperty carbs;
    private final DoubleProperty fat;

    public ViewableFoodInfo(String name, double kcal, double prot, double carbs, double fat) {
        this.name = new SimpleStringProperty(name);
        this.kcal = new SimpleDoubleProperty(kcal);
        this.prot = new SimpleDoubleProperty(prot);
        this.carbs = new SimpleDoubleProperty(carbs);
        this.fat = new SimpleDoubleProperty(fat);
    }

    public static ViewableFoodInfo fromFoodInfo(FoodInfo foodInfo) {
        return new ViewableFoodInfo(foodInfo.getName(), foodInfo.getKcal(), foodInfo.getProtein(), foodInfo.getCarbs(), foodInfo.getFat());
    }

    public String getName() { return name.get();}
    public double getKcal() { return kcal.get();}
    public double getProt() { return prot.get();}
    public double getCarbs() { return carbs.get();}
    public double getFat() { return  fat.get();}
}

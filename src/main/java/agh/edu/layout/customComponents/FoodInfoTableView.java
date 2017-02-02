package agh.edu.layout.customComponents;

import agh.edu.model.viewable.ViewableFoodInfo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class FoodInfoTableView extends TableView {
    private ObservableList<ViewableFoodInfo> currentFoodInfo;
    private DoubleProperty totalKcalChosen = new SimpleDoubleProperty(0);
    private DoubleProperty totalProtChosen = new SimpleDoubleProperty(0);
    private DoubleProperty totalCarbsChosen = new SimpleDoubleProperty(0);
    private DoubleProperty totalFatChosen = new SimpleDoubleProperty(0);

    public FoodInfoTableView() {
        TableColumn nameCol = new TableColumn("Name");
        TableColumn kcalCol = new TableColumn("Calories");
        TableColumn protCol = new TableColumn("Protein");
        TableColumn carbsCol = new TableColumn("Carbs");
        TableColumn fatCol = new TableColumn("Fat");

        nameCol.setCellValueFactory(new PropertyValueFactory<ViewableFoodInfo, String>("name"));
        kcalCol.setCellValueFactory(new PropertyValueFactory<ViewableFoodInfo, Double>("kcal"));
        protCol.setCellValueFactory(new PropertyValueFactory<ViewableFoodInfo, Double>("prot"));
        carbsCol.setCellValueFactory(new PropertyValueFactory<ViewableFoodInfo, Double>("carbs"));
        fatCol.setCellValueFactory(new PropertyValueFactory<ViewableFoodInfo, Double>("fat"));

        nameCol.prefWidthProperty().bind(this.widthProperty().divide(3));
        kcalCol.prefWidthProperty().bind(this.widthProperty().divide(6));
        protCol.prefWidthProperty().bind(this.widthProperty().divide(6));
        carbsCol.prefWidthProperty().bind(this.widthProperty().divide(6));
        fatCol.prefWidthProperty().bind(this.widthProperty().divide(6));

        this.getColumns().addAll(nameCol, kcalCol, protCol, carbsCol, fatCol);
        this.setPlaceholder(new Text("No food found"));
    }

    public ObservableList<ViewableFoodInfo> getCurrentFoodInfo() {
        return currentFoodInfo;
    }

    public void setCurrentFoodInfo(ObservableList<ViewableFoodInfo> currentFoodInfo) {
        this.currentFoodInfo = currentFoodInfo;
    }

    public DoubleProperty totalKcalChosenProperty() {
        return totalKcalChosen;
    }

    public DoubleProperty totalProtChosenProperty() {
        return totalProtChosen;
    }

    public DoubleProperty totalCarbsChosenProperty() {
        return totalCarbsChosen;
    }

    public DoubleProperty totalFatChosenProperty() {
        return totalFatChosen;
    }

    public double getTotalKcalChosen() {
        return totalKcalChosen.get();
    }

    public double getTotalProtChosen() {
        return totalProtChosen.get();
    }

    public double getTotalCarbsChosen() {
        return totalCarbsChosen.get();
    }

    public double getTotalFatChosen() {
        return totalFatChosen.get();
    }

    public void addKcalToTotal(double kcal) {
        totalKcalChosen.setValue(totalKcalChosen.getValue() + kcal);
    }
    public void addCarbsToTotal(double carbs) {
        totalCarbsChosen.setValue(totalCarbsChosen.getValue() + carbs);
    }
    public void addProtToTotal(double prot) {
        totalProtChosen.setValue(totalProtChosen.getValue() + prot);
    }
    public void addFatToTotal(double fat) {
    totalFatChosen.setValue(totalFatChosen.getValue() + fat);
    }

    public void clearChosen() {
        totalKcalChosen.set(0);
        totalCarbsChosen.set(0);
        totalProtChosen.set(0);
        totalFatChosen.set(0);
    }
}

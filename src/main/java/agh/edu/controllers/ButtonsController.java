package agh.edu.controllers;

import agh.edu.layout.customComponents.FoodInfoTableView;
import agh.edu.model.Statistic;
import agh.edu.model.observable.CurrentInfo;
import agh.edu.model.observable.ObservableStatistic;
import agh.edu.model.viewable.ViewableFoodInfo;
import agh.edu.storage.StorageAggregator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.controlsfx.control.action.ActionMap;
import org.controlsfx.control.action.ActionProxy;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ButtonsController {
    private StorageAggregator storages;
    private CurrentInfo currentInfo;
    // The controller needs those references to change windows on button click
    private FlowPane mainWindow;
    private FlowPane changeDayWindow;
    private FlowPane addMealWindow;
    private FlowPane statsWindow;
    private BorderPane mainLayout;

    public ButtonsController(List<FlowPane> windows, BorderPane mainLayout, StorageAggregator storages, CurrentInfo currentInfo) {
        if(windows.size() != 4) {
            throw new IllegalArgumentException("Wrong number of windows, couldn't construct button controller");
        }
        this.storages = storages;
        this.currentInfo = currentInfo;
        this.mainLayout = mainLayout;
        this.mainWindow = windows.get(0);
        this.changeDayWindow = windows.get(1);
        this.addMealWindow = windows.get(2);
        this.statsWindow = windows.get(3);

        ActionMap.register(this);
    }

    /* Main buttons */
    @ActionProxy(text="")
    public void addButton(ActionEvent event) {
        mainLayout.setCenter(addMealWindow);
    }
    @ActionProxy(text="")
    public void changeDayButton(ActionEvent event) {
        mainLayout.setCenter(changeDayWindow);
    }
    @ActionProxy(text="")
    public void statsButton(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Coming soon").showAndWait();
    }
    @ActionProxy(text="")
    public void optionsButton(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Coming soon").showAndWait();
    }

    /* Add meal buttons */
    @ActionProxy(text = "Search")
    public void searchButton(ActionEvent event) {
        String searchPhrase = getTextPrompt().getText();
        List<ViewableFoodInfo> matchingFoods = storages.getFoodInfoStorage()
                .getByPredicate(food -> food.getName().matches("(?i)(.*)" + searchPhrase + "(.*)")) // Match pattern anywhere in text ignoring case
                .stream()
                .map(ViewableFoodInfo::fromFoodInfo)
                .collect(Collectors.toList());
        FoodInfoTableView tableView = getFoodInfoView();
        tableView.setItems(FXCollections.observableArrayList(matchingFoods));
    }
    @ActionProxy(text = "Add")
    public void addFoodButton(ActionEvent event) {
        FoodInfoTableView tableView = getFoodInfoView();
        ViewableFoodInfo selectedFoodInfo = (ViewableFoodInfo)tableView.getSelectionModel().getSelectedItem();

        if(selectedFoodInfo != null) {
            TextInputDialog gramatureDialog = new TextInputDialog("100");
            gramatureDialog.setContentText("Enter the weight of chosen food in grams: ");
            // Validation
            gramatureDialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, buttonEvent -> {
                if(!gramatureDialog.getEditor().getText().matches("[0-9]{1,4}")) {
                    buttonEvent.consume();
                    new Alert(Alert.AlertType.ERROR, "Please enter a number (max 9999)").showAndWait();
                }
            });

            Optional<String> grams = gramatureDialog.showAndWait();
            if(!grams.isPresent()) {
                // User clicked cancel
                return;
            }

            double multiplier = Double.parseDouble(grams.get()) / 100;

            System.out.println(multiplier);

            tableView.addKcalToTotal(selectedFoodInfo.getKcal() * multiplier);
            tableView.addCarbsToTotal(selectedFoodInfo.getCarbs() * multiplier);
            tableView.addProtToTotal(selectedFoodInfo.getProt() * multiplier);
            tableView.addFatToTotal(selectedFoodInfo.getFat() * multiplier);
        }
    }
    @ActionProxy(text="")
    public void addMealOK(ActionEvent event) {
        FoodInfoTableView tableView = getFoodInfoView();

        ObservableStatistic currentStats = currentInfo.getObservableStatistic();
        currentStats.addCarbs(tableView.getTotalCarbsChosen());
        currentStats.addKcal(tableView.getTotalKcalChosen());
        currentStats.addProt(tableView.getTotalProtChosen());
        currentStats.addFat(tableView.getTotalFatChosen());


        storages.getStatisticsStorage().save(currentStats.toStatistic());

        tableView.clearChosen();
        mainLayout.setCenter(mainWindow);
    }
    @ActionProxy(text="")
    public void addMealCancel(ActionEvent event) {
        FoodInfoTableView tableView = getFoodInfoView();
        tableView.clearChosen();
        mainLayout.setCenter(mainWindow);
    }

    private FoodInfoTableView getFoodInfoView() {
        FlowPane containerWindow = (FlowPane)addMealWindow.getChildren().get(0);
        Optional<Node> foodInfoView = containerWindow.getChildren().stream().filter(child -> child.getClass() == FoodInfoTableView.class).findFirst();
        if(!foodInfoView.isPresent()) {
            throw new RuntimeException("Fatal error - no table view in add meal window found");
        }
        return (FoodInfoTableView)foodInfoView.get();
    }

    private TextField getTextPrompt() {
        FlowPane containerWindow = (FlowPane)addMealWindow.getChildren().get(0);
        Optional<Node> textPrompt = containerWindow.getChildren().stream().filter(child -> child.getClass() == TextField.class).findFirst();
        if(!textPrompt.isPresent()) {
            throw new RuntimeException("Fatal error - no table view in add meal window found");
        }
        return (TextField) textPrompt.get();
    }

    /* Change day buttons */
    @ActionProxy(text="")
    public void changeDayOK(ActionEvent event) {
        Optional<Node> windowDatePicker = changeDayWindow.getChildren().stream().filter(child -> child.getClass() == DatePicker.class).findFirst();
        if(!windowDatePicker.isPresent()) {
            new Alert(Alert.AlertType.ERROR, "Fatal error - couldn't change day").showAndWait();
        }
        else {
            LocalDate newDate = ((DatePicker)windowDatePicker.get()).getValue();
            Optional<Statistic> stat = storages.getStatisticsStorage().getByDate(newDate);
            if(!stat.isPresent()) {
                Statistic newStat = new Statistic(newDate);
                storages.getStatisticsStorage().save(newStat);
                currentInfo.setCurrentDayStats(newStat);
            }
            else {
                currentInfo.setCurrentDayStats(stat.get());
            }
        }
        mainLayout.setCenter(mainWindow);
    }
    @ActionProxy(text="")
    public void changeDayCancel(ActionEvent event) {
        mainLayout.setCenter(mainWindow);
    }

}

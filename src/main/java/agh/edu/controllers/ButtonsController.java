package agh.edu.controllers;

import agh.edu.model.Statistic;
import agh.edu.model.observable.CurrentInfo;
import agh.edu.storage.StorageAggregator;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.controlsfx.control.action.ActionMap;
import org.controlsfx.control.action.ActionProxy;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    }
    @ActionProxy(text="")
    public void changeDayButton(ActionEvent event) {
        mainLayout.setCenter(changeDayWindow);
    }
    @ActionProxy(text="")
    public void statsButton(ActionEvent event) {

    }
    @ActionProxy(text="")
    public void optionsButton(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Coming soon").showAndWait();
    }
    /* Add meal buttons */

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

package agh.edu.controllers;

import agh.edu.model.observable.CurrentInfo;
import agh.edu.storage.StorageAggregator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.controlsfx.control.action.ActionMap;
import org.controlsfx.control.action.ActionProxy;

import java.util.List;

public class ButtonsController {
    StorageAggregator storages;
    CurrentInfo currentInfo;
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
        // TODO: date stuff
        mainLayout.setCenter(mainWindow);
    }
    @ActionProxy(text="")
    public void changeDayCancel(ActionEvent event) {
        mainLayout.setCenter(mainWindow);
    }

}

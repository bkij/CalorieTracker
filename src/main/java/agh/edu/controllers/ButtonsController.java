package agh.edu.controllers;

import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.controlsfx.control.action.ActionMap;
import org.controlsfx.control.action.ActionProxy;

import java.util.List;

public class ButtonsController {
    // The controller needs those references to change windows on button click
    private FlowPane mainWindow;
    private FlowPane changeDayWindow;
    private FlowPane addMealWindow;
    private FlowPane statsWindow;
    private BorderPane mainLayout;

    public ButtonsController(List<FlowPane> windows, BorderPane mainLayout) {
        if(windows.size() != 4) {
            throw new IllegalArgumentException("Wrong number of windows, couldn't construct button controller");
        }
        this.mainLayout = mainLayout;
        this.mainWindow = windows.get(0);
        this.changeDayWindow = windows.get(1);
        this.addMealWindow = windows.get(2);
        this.statsWindow = windows.get(3);

        ActionMap.register(this);
    }

    @ActionProxy(text="")
    public void addButton(ActionEvent event) {

    }
}

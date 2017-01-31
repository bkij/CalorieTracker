package agh.edu.controllers;

import javafx.scene.layout.FlowPane;
import org.controlsfx.control.action.ActionMap;

import java.util.List;

public class ButtonsController {
    // The controller needs those references to change windows on button click
    private FlowPane mainWindow;
    private FlowPane changeDayWindow;
    private FlowPane addMealWindow;
    private FlowPane statsWindow;

    public ButtonsController(List<FlowPane> windows) {
        if(windows.size() != 4) {
            throw new IllegalArgumentException("Wrong number of windows, couldn't construct button controller");
        }
        this.mainWindow = windows.get(0);
        this.changeDayWindow = windows.get(1);
        this.addMealWindow = windows.get(2);
        this.statsWindow = windows.get(3);

        ActionMap.register(this);
    }
}

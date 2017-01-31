package agh.edu.layout;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public interface NutritionInfoContainerCreator {
    VBox getCalorieInfo();
    HBox getMacroInfo();
    HBox getMicroInfo();
}

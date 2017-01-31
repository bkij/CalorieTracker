package agh.edu.layout;

import agh.edu.layout.customComponents.PercentProgressIndicator;
import agh.edu.model.UserConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class SoftRectangleNutritionContainer implements NutritionInfoContainerCreator {
    private UserConfig userConfig;
    private Insets parentPadding;
    private int parentHeight;
    private int parentWidth;
    private int containerAreaWidth;
    private Text currentCalories;

    public SoftRectangleNutritionContainer(UserConfig userConfig, Insets parentPadding, int parentHeight, int parentWidth) {
        this.userConfig = userConfig;
        this.parentHeight = parentHeight;
        this.parentWidth = parentWidth;
        this.parentPadding = parentPadding;
        this.containerAreaWidth = parentWidth - (int)parentPadding.getLeft() - (int)parentPadding.getRight();
        this.currentCalories = new Text("0");
    }

    @Override
    public VBox getCalorieInfo() {
        VBox calorieContainer = getBasicStyledVBox();
        int containerWidth = parentHeight / 4;
        int containerHeight = containerAreaWidth / 3 - (int)(parentPadding.getLeft() + parentPadding.getRight());

        TextFlow calorieLabel = new TextFlow(new Text("Calories:"));
        calorieLabel.setMinWidth(containerWidth);
        calorieLabel.setTextAlignment(TextAlignment.CENTER);
        calorieLabel.setMinHeight(containerHeight / 3);

        TextFlow calorieValues = new TextFlow(currentCalories, new Text(" / " + userConfig.getBMR() + " kcal"));
        calorieValues.setTextAlignment(TextAlignment.CENTER);

        StackPane progressIndicator = new PercentProgressIndicator(0.0, userConfig.getBMR());

        calorieContainer.getChildren().addAll(calorieLabel, calorieValues, progressIndicator);
        calorieContainer.setMinHeight(containerHeight);
        calorieContainer.setMinHeight(containerWidth);
        return calorieContainer;
    }

    @Override
    public HBox getMacroInfo() {
        return getBasicStyledHBox();
    }

    @Override
    public HBox getMicroInfo() {
        return getBasicStyledHBox();
    }

    private VBox getBasicStyledVBox() {
        VBox styledBox = new VBox();
        styledBox.getStyleClass().add("softRectVBox");
        styledBox.setAlignment(Pos.TOP_CENTER);
        styledBox.setPadding(new Insets(10));
        styledBox.setSpacing(15);
        return styledBox;
    }

    private HBox getBasicStyledHBox() {
        HBox styledBox = new HBox();
        styledBox.getStyleClass().add("softRectVBox");
        styledBox.setAlignment(Pos.TOP_CENTER);
        styledBox.setPadding(new Insets(10));
        styledBox.setSpacing(15);
        return styledBox;
    }
}

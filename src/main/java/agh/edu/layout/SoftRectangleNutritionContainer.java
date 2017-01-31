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
    private int parentVgap;
    private int parentHGap;
    private int containerAreaWidth;
    private int calorieBoxWidth;
    private int calorieBoxHeight;
    private int macroBoxWidth;
    private int macroBoxHeight;
    private Text currentCalories;
    private Text currentProt;
    private Text currentCarbs;
    private Text currentFat;
    private final int BOXES_PADDING = 10;

    public SoftRectangleNutritionContainer(UserConfig userConfig, Insets parentPadding, int parentHeight, int parentWidth, int vgap, int hgap) {
        this.userConfig = userConfig;
        this.parentHeight = parentHeight;
        this.parentWidth = parentWidth;
        this.parentPadding = parentPadding;
        this.parentVgap = vgap;
        this.parentHGap = hgap;

        this.containerAreaWidth = parentWidth - (int)parentPadding.getLeft() - (int)parentPadding.getRight();
        this.currentCalories = new Text("0");
        this.currentProt = new Text("0");
        this.currentCarbs = new Text("0");
        this.currentFat = new Text("0");
    }

    @Override
    public VBox getCalorieInfo() {
        VBox calorieContainer = getBasicStyledVBox();
        calorieBoxWidth = (containerAreaWidth / 4) - parentHGap;
        calorieBoxHeight = parentHeight / 6;

        TextFlow calorieLabel = new TextFlow(new Text("Calories:"));
        calorieLabel.setMinWidth(calorieBoxWidth);
        calorieLabel.setMinHeight(calorieBoxHeight / 3);
        calorieLabel.setTextAlignment(TextAlignment.CENTER);
        calorieLabel.getStyleClass().add("nutritionLabelText");

        TextFlow calorieValues = new TextFlow(currentCalories, new Text(" / " + userConfig.getBMR() + " kcal"));
        calorieValues.setTextAlignment(TextAlignment.CENTER);
        calorieValues.getStyleClass().add("nutritionValuesText");

        StackPane progressIndicator = new PercentProgressIndicator(0.0, userConfig.getBMR());

        calorieContainer.getChildren().addAll(calorieLabel, calorieValues, progressIndicator);
        calorieContainer.setMinHeight(calorieBoxHeight);
        calorieContainer.setMinHeight(calorieBoxWidth);
        return calorieContainer;
    }

    @Override
    public HBox getMacroInfo() {
        HBox macroContainer = getBasicStyledHBox();
        macroBoxWidth = containerAreaWidth - calorieBoxWidth - (parentHGap * 3); // TODO: parentHGap *3 is a workaound, change?
        macroBoxHeight = calorieBoxHeight;
        int macroRegionWidth = (macroBoxWidth - (4 * BOXES_PADDING)) / 3;

        // PROTEIN PART
        TextFlow proteinLabel = new TextFlow(new Text("Protein:"));
        proteinLabel.setMinWidth(macroRegionWidth);
        proteinLabel.setMinHeight(macroBoxHeight / 3);
        proteinLabel.setTextAlignment(TextAlignment.CENTER);
        proteinLabel.getStyleClass().add("nutritionLabelText");

        TextFlow proteinValues = new TextFlow(currentProt, new Text(" / " + userConfig.getProtein() + " grams"));
        proteinValues.setTextAlignment(TextAlignment.CENTER);
        proteinValues.getStyleClass().add("nutritionValuesText");

        PercentProgressIndicator proteinIndicator = new PercentProgressIndicator(0.0, userConfig.getProtein());

        VBox proteinBox = getSubStyledVbox();
        proteinBox.setMinHeight(macroBoxHeight);
        proteinBox.getChildren().addAll(proteinLabel, proteinValues, proteinIndicator);

        // CARBS PART
        TextFlow carbsLabel = new TextFlow(new Text("Carbs:"));
        carbsLabel.setMinWidth(macroRegionWidth);
        carbsLabel.setMinHeight(macroBoxHeight / 3);
        carbsLabel.setTextAlignment(TextAlignment.CENTER);
        carbsLabel.getStyleClass().add("nutritionLabelText");

        TextFlow carbValues = new TextFlow(currentCarbs, new Text(" / " + userConfig.getCarbs() + " grams"));
        carbValues.setTextAlignment(TextAlignment.CENTER);
        carbValues.getStyleClass().add("nutritionValuesText");

        PercentProgressIndicator carbsIndicator = new PercentProgressIndicator(0.0, userConfig.getCarbs());

        VBox carbBox = getSubStyledVbox();
        carbBox.setMinHeight(macroBoxHeight);
        carbBox.getChildren().addAll(carbsLabel, carbValues, carbsIndicator);

        // FAT PART
        TextFlow fatLabel = new TextFlow(new Text("Fat:"));
        fatLabel.setMinWidth(macroRegionWidth);
        fatLabel.setMinHeight(macroBoxHeight / 3);
        fatLabel.setTextAlignment(TextAlignment.CENTER);
        fatLabel.getStyleClass().add("nutritionLabelText");

        TextFlow fatValues = new TextFlow(currentFat, new Text(" / " + userConfig.getFat() + " grams"));
        fatValues.setTextAlignment(TextAlignment.CENTER);
        fatValues.getStyleClass().add("nutritionValuesText");

        PercentProgressIndicator fatIndicator = new PercentProgressIndicator(0.0, userConfig.getFat());

        VBox fatBox = getSubStyledVbox();
        fatBox.setMinHeight(macroBoxHeight);
        fatBox.getChildren().addAll(fatLabel, fatValues, fatIndicator);

        // Adding everything together
        macroContainer.getChildren().addAll(proteinBox, carbBox, fatBox);
        macroContainer.setMinWidth(macroBoxWidth);
        macroContainer.setMinHeight(macroBoxHeight);
        return macroContainer;
    }

    @Override
    public HBox getMicroInfo() {
        HBox microContainer = getBasicStyledHBox();
        int containerWidth = macroBoxWidth + calorieBoxWidth + parentHGap + 15; // TODO: FIX
        int containerHeight = 3 * calorieBoxHeight / 2;

        TextFlow microLabel = new TextFlow(new Text("Microelements:"));
        microLabel.setMinWidth(containerWidth);
        microLabel.setMinHeight(containerHeight / 3);
        microLabel.setTextAlignment(TextAlignment.CENTER);
        microLabel.getStyleClass().add("nutritionLabelText");

        microContainer.getChildren().addAll(microLabel);
        microContainer.setMinWidth(containerWidth);
        microContainer.setMinHeight(containerHeight);
        return microContainer;
    }

    private VBox getBasicStyledVBox() {
        VBox styledBox = new VBox();
        styledBox.getStyleClass().add("softRectVBox");
        styledBox.setAlignment(Pos.TOP_CENTER);
        styledBox.setPadding(new Insets(BOXES_PADDING));
        styledBox.setSpacing(10);
        return styledBox;
    }

    private HBox getBasicStyledHBox() {
        HBox styledBox = new HBox();
        styledBox.getStyleClass().add("softRectVBox");
        styledBox.setAlignment(Pos.TOP_CENTER);
        styledBox.setPadding(new Insets(BOXES_PADDING));
        styledBox.setSpacing(15);
        return styledBox;
    }

    private VBox getSubStyledVbox() {
        VBox subStyledBox = new VBox();
        subStyledBox.setAlignment(Pos.TOP_CENTER);
        subStyledBox.setSpacing(10);
        return subStyledBox;
    }
}

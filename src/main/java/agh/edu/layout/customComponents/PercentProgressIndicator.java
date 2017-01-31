package agh.edu.layout.customComponents;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class PercentProgressIndicator extends StackPane {
    final double requirementsSatisfied;
    final private double totalRequirements;
    final private ProgressBar progressBar = new ProgressBar();
    final private Text percentDone = new Text("0%");

    public PercentProgressIndicator(double requirementsSatisfied, double totalRequirements) {
        this.requirementsSatisfied = 0.0;
        this.totalRequirements = totalRequirements;

        progressBar.setMaxWidth(Double.MAX_VALUE);  // Let progress bar be filled
        progressBar.setProgress(requirementsSatisfied / totalRequirements);
        this.getChildren().addAll(progressBar, percentDone);
    }
}

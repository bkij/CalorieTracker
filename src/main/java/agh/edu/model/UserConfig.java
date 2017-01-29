package agh.edu.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserConfig implements Serializable {
    /*
     * BMR - Basic Metabolic Rate
     *
     * Default values taken from US Food and Drug Administration's
     * reference intake recommendations. They constitute the RDA,
     * Recommended Dietary Allowances, considered sufficient to
     * meet the requirements of 97.5% of population
     */
    private String userName;

    private final static double initialBMR = 2000.0;
    private double BMR = initialBMR;

    /*
     * Macronutrients
     * in grams
     */

    private double protein = 130;
    private double fat = 44.5;      // Based on RDA of 20% of total calories
    private double carbs = 56;

    /*
     * Micronutrients
     * in micrograms
     */
    private Map<String, Double> micronutrients = new HashMap<>();

    public UserConfig() {
        initializeMicronutrients();
    }

    private void initializeMicronutrients() {
        micronutrients.put("A", 900.0);
        micronutrients.put("C", 90_000.0);
        micronutrients.put("D", 15.0);
        micronutrients.put("K", 120.0);
        micronutrients.put("B6", 1300.0);
        micronutrients.put("E", 15_000.0);
        micronutrients.put("Calcium", 1_000_000.0);
        micronutrients.put("Choline", 550_000.0);
        micronutrients.put("Copper", 900.0);
        micronutrients.put("B12", 2.4);
        micronutrients.put("B9", 400.0);
        micronutrients.put("Iron", 8000.0);
        micronutrients.put("Magnesium", 420_000.0);
        micronutrients.put("Manganese", 2300.0);
        micronutrients.put("B3", 16_000.0);
        micronutrients.put("B5", 5000.0);
        micronutrients.put("Phosphorus", 700_000.0);
        micronutrients.put("Potassium", 4_700_000.0);
        micronutrients.put("B2", 1300.0);
        micronutrients.put("Selenium", 55.0);
        micronutrients.put("Sodium", 1_500_000.0);
        micronutrients.put("B1", 1200.0);
        micronutrients.put("Zinc", 11_000.0);
    }

    public String getUserName() {
        return userName;
    }

    public double getBMR() {
        return BMR;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getMicronutritient(String name) {
        if(!micronutrients.containsKey(name)) {
            throw new IllegalArgumentException("Unknown micronutritient name.");
        }
        return micronutrients.get(name);
    }

    public void setBMR(double BMR) {
        this.BMR = BMR;
        this.protein *= (BMR / initialBMR);
        this.carbs *= (BMR / initialBMR);
        this.fat *= (BMR / initialBMR);
        for(Map.Entry<String, Double> entry : micronutrients.entrySet()) {
            micronutrients.replace(entry.getKey(), entry.getValue() * (BMR / initialBMR));
        }
    }
}

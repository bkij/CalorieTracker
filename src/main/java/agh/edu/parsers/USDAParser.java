package agh.edu.parsers;

import agh.edu.exceptions.ParsingException;
import agh.edu.model.FoodInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class USDAParser implements NutritionalDataParser {
    private final int numNutritionFields = 46;
    private final String filesDir;
    private final String fileName;

    public USDAParser(String filesDir, String fileName) {
        this.filesDir = filesDir;
        this.fileName = fileName;
    }

    @Override
    public List<FoodInfo> parse() {
        List<FoodInfo> foodData = new LinkedList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filesDir + fileName);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            for(String currentLine = reader.readLine(); currentLine != null; currentLine = reader.readLine()) {
                FoodInfo currentFoodInfo = getInfoForCurrentLine(currentLine);
                foodData.add(currentFoodInfo);
            }

        } catch(IOException ex) {
            //TODO: Change?
            throw new ParsingException("Fatal error - IOException");
        }

        return foodData;
    }

    private FoodInfo getInfoForCurrentLine(String currentLine) {
        FoodInfo currentFoodInfo = new FoodInfo();

        String[] parts = currentLine.split(Pattern.quote("^"));
        double[] nutInfo = new double[parts.length - 7];

        for(int i = 0; i < nutInfo.length; i++) {
            nutInfo[i] = Double.parseDouble(parts[i + 2]);
        }
        if(nutInfo.length != numNutritionFields) {
            throw new ParsingException("File format problem");
        }
        setNutritionalInfo(currentFoodInfo, nutInfo, parts[1]);

        return currentFoodInfo;
    }

    private void setNutritionalInfo(FoodInfo info, double[] nutInfo, String foodName) {
        info.setName(foodName);
        info.setKcal(nutInfo[1]);
        info.setProtein(nutInfo[2]);
        info.setFat(nutInfo[3]);
        info.setCarbs(nutInfo[5]);
        info.setCalcium(nutInfo[8]);
        info.setIron(nutInfo[9]);
        info.setMagnesium(nutInfo[10]);
        info.setPhosphorus(nutInfo[11]);
        info.setPotassium(nutInfo[12]);
        info.setSodium(nutInfo[13]);
        info.setZinc(nutInfo[14]);
        info.setCopper(nutInfo[15]);
        info.setManganese(nutInfo[16]);
        info.setSelenium(nutInfo[17]);
        info.setC(nutInfo[18]);
        info.setB1(nutInfo[19]);
        info.setB2(nutInfo[20]);
        info.setB3(nutInfo[21]);
        info.setB5(nutInfo[22]);
        info.setB6(nutInfo[23]);
        info.setCholine(nutInfo[28]);
        info.setB12(nutInfo[29]);
        info.setA(nutInfo[31]);
        info.setE(nutInfo[38]);
        info.setD(nutInfo[39]);
        info.setK(nutInfo[41]);
    }
}

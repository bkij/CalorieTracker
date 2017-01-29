package agh.edu.persistence.inMemory;

import agh.edu.exceptions.PersistenceException;
import agh.edu.model.FoodInfo;
import agh.edu.persistence.FoodInfoPersistence;
import agh.edu.util.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FoodInfoAggregator implements FoodInfoPersistence {
    ArrayList<FoodInfo> foodData;
    private final String fileDir;
    private final String fileName;

    public FoodInfoAggregator(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.fileName = fileName;
        this.foodData = new ArrayList<>();
    }

    @Override
    public void initializePersistence() {
        try (FileInputStream foodInputStream = new FileInputStream(fileDir + fileName); ObjectInputStream foodReader = new ObjectInputStream(foodInputStream)){
            foodData = (ArrayList<FoodInfo>)foodReader.readObject();
        } catch(IOException ex) {
            if(Utils.fileExists(fileDir, fileName)) {
                throw new PersistenceException("Food file exists but cannot be opened");
            }
        } catch(ClassCastException | ClassNotFoundException ex) {
            throw new PersistenceException("Wrong food file format");
        }
    }

    @Override
    public void finalizePersistence() {
        try (FileOutputStream foodOutputStream = new FileOutputStream(fileDir + fileName); ObjectOutputStream foodWriter = new ObjectOutputStream(foodOutputStream)){
            foodWriter.writeObject(foodData);
            foodWriter.close();
        } catch(IOException ex) {
            throw new PersistenceException("Cannot write to food file");
        }
    }

    @Override
    public void save(FoodInfo info) {
        foodData.add(info);
    }

    @Override
    public List<FoodInfo> getByPredicate(Predicate<FoodInfo> predicate) {
        return foodData.stream().filter(predicate).collect(Collectors.toList());
    }
}

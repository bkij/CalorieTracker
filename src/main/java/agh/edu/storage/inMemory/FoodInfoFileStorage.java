package agh.edu.storage.inMemory;

import agh.edu.exceptions.PersistenceException;
import agh.edu.model.FoodInfo;
import agh.edu.storage.FoodInfoStorage;
import agh.edu.util.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FoodInfoFileStorage implements FoodInfoStorage {
    ArrayList<FoodInfo> foodData;
    private final String fileDir;
    private final String fileName;

    public FoodInfoFileStorage(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.fileName = fileName;
        this.foodData = new ArrayList<>();
    }

    @Override
    public void initializeStorage() {
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
    public void finalizeStorage() {
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

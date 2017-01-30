package agh.edu.storage.inMemory;

import agh.edu.exceptions.PersistenceException;
import agh.edu.model.UserConfig;
import agh.edu.storage.UserConfigStorage;
import agh.edu.util.Utils;

import java.io.*;

public class UserConfigFileStorage implements UserConfigStorage {
    private UserConfig currentUserConfig;
    private final String fileDir;
    private final String fileName;

    public UserConfigFileStorage(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.fileName = fileName;
    }

    @Override
    public boolean configExists() {
        return Utils.fileExists(fileDir, fileName);
    }

    @Override
    public void initializePersistence() {
        try (FileInputStream configStream = new FileInputStream(fileDir + fileName); ObjectInputStream configReader = new ObjectInputStream(configStream)){
            currentUserConfig = (UserConfig)configReader.readObject();
        } catch(IOException ex) {
            if(configExists()) {
                throw new PersistenceException("Config file exists but can't be opened, " + ex.getMessage());
            }
        } catch(ClassNotFoundException | ClassCastException ex) {
            throw new PersistenceException("Wrong config file format, " + ex.getMessage());
        }
    }

    @Override
    public void finalizePersistence() {
        try (FileOutputStream configOutputStream = new FileOutputStream(fileDir + fileName); ObjectOutputStream configWriter = new ObjectOutputStream(configOutputStream)){
            configWriter.writeObject(currentUserConfig);
        } catch(IOException ex) {
            throw new PersistenceException("Cannot write to config file, " + ex.getMessage());
        }
    }

    @Override
    public void save(UserConfig userConfig) {
        currentUserConfig = userConfig;
    }

    @Override
    public UserConfig get() {
        return currentUserConfig;
    }
}

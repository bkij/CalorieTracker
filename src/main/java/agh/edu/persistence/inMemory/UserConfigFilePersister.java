package agh.edu.persistence.inMemory;

import agh.edu.exceptions.PersistenceException;
import agh.edu.model.UserConfig;
import agh.edu.persistence.UserConfigPersistence;
import agh.edu.util.Utils;

import java.io.*;

public class UserConfigFilePersister implements UserConfigPersistence {
    private UserConfig currentUserConfig;
    private final String fileDir;
    private final String fileName;

    public UserConfigFilePersister(String fileDir, String fileName) {
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
                throw new PersistenceException("Config file exists but can't be opened.");
            }
        } catch(ClassNotFoundException | ClassCastException ex) {
            throw new PersistenceException("Wrong config file format.");
        }
    }

    @Override
    public void finalizePersistence() {
        try (FileOutputStream configOutputStream = new FileOutputStream(fileDir + fileName); ObjectOutputStream configWriter = new ObjectOutputStream(configOutputStream)){
            configWriter.writeObject(currentUserConfig);
        } catch(IOException ex) {
            throw new PersistenceException("Cannot write to config file");
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

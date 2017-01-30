package agh.edu.storage;

import agh.edu.model.UserConfig;

public interface UserConfigStorage {
    boolean configExists();
    void initializePersistence();
    void finalizePersistence();
    void save(UserConfig userConfig);
    UserConfig get();
}

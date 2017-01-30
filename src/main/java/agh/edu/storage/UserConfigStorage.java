package agh.edu.storage;

import agh.edu.model.UserConfig;

public interface UserConfigStorage {
    boolean configExists();
    void initializeStorage();
    void finalizeStorage();
    void save(UserConfig userConfig);
    UserConfig get();
}

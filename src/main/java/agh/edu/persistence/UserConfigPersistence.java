package agh.edu.persistence;

import agh.edu.model.UserConfig;

public interface UserConfigPersistence {
    void initializePersistence();
    void finalizePersistence();
    void save(UserConfig userConfig);
    UserConfig get();
}

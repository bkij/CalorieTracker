package agh.edu.persistence;

import agh.edu.model.UserConfig;

public interface UserConfigPersistence {
    boolean configExists();
    void initializePersistence();
    void finalizePersistence();
    void save(UserConfig userConfig);
    UserConfig get();
}

package config;

import main.application.AppData;
import service.ReadWriteServices.RWRestaurantService;
import service.ReadWriteServices.RWUserService;

public class FileDataConfig {
    private final RWUserService rwUserService;
    private final RWRestaurantService rwRestaurantService;

    private static FileDataConfig INSTANCE;

    private FileDataConfig() {
        this.rwUserService = RWUserService.getInstance();
        this.rwRestaurantService = RWRestaurantService.getInstance();
    }

    public static FileDataConfig getInstance() {
        if (INSTANCE == null) INSTANCE = new FileDataConfig();
        return INSTANCE;
    }

    public void config(AppData appData) {
        rwUserService.read(appData);
        rwRestaurantService.read(appData);
    }
}

package config;

import config.AdminConfig;
import config.FileDataConfig;
import main.application.AppData;

public class AppConfig {
    private final AppData appData;
    private final AdminConfig adminConfig;
    private final FileDataConfig fileDataConfig;

    public AppConfig(AppData appData) {
        this.appData = appData;
        this.adminConfig = AdminConfig.getInstance();
        this.fileDataConfig = FileDataConfig.getInstance();
    }

    public void configAdmins() {
        adminConfig.config(appData);
    }
    public void configFileData() {
        fileDataConfig.config(appData);
    }
}

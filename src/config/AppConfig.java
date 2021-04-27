package config;

import app_core.AppData;

public class AppConfig {
    private final AppData appData;
    private final AdminConfig adminConfig;

    public AppConfig() {
        this.appData = AppData.getInstance();
        this.adminConfig = AdminConfig.getInstance();
    }

    public void configAdmins() {
        adminConfig.config(appData);
    }
}

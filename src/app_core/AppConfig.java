package app_core;

import config.AdminConfig;
import config.DatabaseSchema;

public class AppConfig {
    private final AdminConfig adminConfig;
    private final DatabaseSchema databaseSchema;

    public AppConfig() {
        this.adminConfig = AdminConfig.getInstance();
        this.databaseSchema = DatabaseSchema.getInstance();
    }

    public void config() {
        databaseSchema.config();
        adminConfig.config();
    }
}

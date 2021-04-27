package config;

import app_core.AppData;
import model.users.AppAdmin;
import model.users.User;

public final class AdminConfig {
    private static final String adminName = "Costi Tica";
    private static final String adminPhoneNumber = "0784633333";
    private static final String adminEmail = "admin@gmail.com";
    private static final String adminPassword = "Admin1!";

    private static AdminConfig INSTANCE;

    private AdminConfig() {}

    public static AdminConfig getInstance() {
        if (INSTANCE == null) INSTANCE = new AdminConfig();
        return INSTANCE;
    }

    public void config(AppData appData) {
        AppAdmin admin = (AppAdmin) appData.getUsers().stream()
                .filter(user -> user.getRole() == User.Role.APP_ADMIN && user.getEmail().equals(adminEmail))
                .findFirst().orElse(null);

        if (admin != null) return;

        admin = new AppAdmin.Builder()
                .withId(appData.getNextUserId())
                .withName(adminName)
                .withPhoneNumber(adminPhoneNumber)
                .withEmail(adminEmail)
                .withPassword(adminPassword)
                .withRole(User.Role.APP_ADMIN)
                .withRank(AppAdmin.Rank.GENERAL)
                .build();
        appData.addUser(admin);
    }
}

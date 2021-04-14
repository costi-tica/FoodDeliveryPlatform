package config;

import main.Application;
import model.users.AppAdmin;
import model.users.User;

import java.util.List;

public final class adminConfig {
    private static final String adminName = "Costi Tica";
    private static final String adminPhoneNumber = "0784633333";
    private static final String adminEmail = "admin@gmail.com";
    private static final String adminPassword = "Admin1!";

    public static void config(List<User> users) {
        AppAdmin admin = (AppAdmin) users.stream()
                .filter(user -> user.getRole() == User.Role.APP_ADMIN && user.getEmail().equals(adminEmail))
                .findFirst().orElse(null);

        if (admin != null) return;

        admin = new AppAdmin.Builder()
                .withId(Application.getNextUserId())
                .withName(adminName)
                .withPhoneNumber(adminPhoneNumber)
                .withEmail(adminEmail)
                .withPassword(adminPassword)
                .withRole(User.Role.APP_ADMIN)
                .withRank(AppAdmin.Rank.GENERAL)
                .build();
        users.add(admin);
    }
}

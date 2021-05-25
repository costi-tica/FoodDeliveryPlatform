package config;

import app_core.AppData;
import model.users.AppAdmin;
import model.users.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void config() {
        long insertedId;
        String user = "insert into users values (null, ?, ?, ?, ?, (SELECT id FROM user_roles WHERE role_name = ?))";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(user, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, adminName);
            statement.setString(2, adminPhoneNumber);
            statement.setString(3, adminEmail);
            statement.setString(4, adminPassword);
            statement.setString(5, String.valueOf(User.Role.APP_ADMIN));
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertedId = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            String admin = "insert into admins values (?, (select id from admin_ranks where `rank` = ?))";
            try (PreparedStatement statement2 = DatabaseConnection.getInstance().prepareStatement(admin)) {
                statement2.setLong(1, insertedId);
                statement2.setString(2, String.valueOf(AppAdmin.Rank.GENERAL));
                statement2.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }
    }
}

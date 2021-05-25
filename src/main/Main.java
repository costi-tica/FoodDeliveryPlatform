package main;

import app_core.Application;

public class Main {
    public static void main(String[] args) {
        Application app = Application.getInstance();
        app.start();
//        try {
//            Connection con = DatabaseConnection.getInstance();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        String sql = "insert into users values (null, ?, ?, ?, ?);";
//        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {//try with resources
//            statement.setString(1, "cristi");
//            statement.setString(2, "454675");
//            statement.setString(3, "cristi@gmail.com");
//            statement.setString(4, "parola");
//
//            statement.executeUpdate();
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
    }
}


package repository;

import config.DatabaseConnection;
import model.users.Client;
import model.users.Courier;
import model.users.ResOwner;
import model.users.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    public UserRepository() {}

    private long addNewUser(User user) {
        String sqlUser = "insert into users values (null, ?, ?, ?, ?, (select id from user_roles where role_name = ?)) ";
        long insertedUserId = -1;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhoneNumber());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, String.valueOf(User.Role.CLIENT));
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertedUserId = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return insertedUserId;
    }

    public void addNewClient(Client client) {
        long insertedUserId = addNewUser(client);

        if (insertedUserId == -1) return;

        long insertedAddressId = new AddressRepository().addNewAddress(client.getAddress());

        if (insertedAddressId == -1) return;

        String sqlClient = "insert into clients values (?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sqlClient)) {
            statement.setLong(1, insertedUserId);
            statement.setLong(2, insertedAddressId);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewCourier(Courier courier) {
        long insertedUserId = addNewUser(courier);

        if (insertedUserId == -1) return;

        String sqlCourier = "insert into couriers values (?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sqlCourier)) {
            statement.setLong(1, insertedUserId);
            statement.setBoolean(2, false);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewResOwner(ResOwner owner) {
        long insertedUserId = addNewUser(owner);

        if (insertedUserId == -1) return;

        String sqlResOwner = "insert into restaurant_owner values (?, null)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sqlResOwner)) {
            statement.setLong(1, insertedUserId);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public long login(String email, String password) {
        String sqlUser = "select id from users where email = ? and password = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sqlUser)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if (!result.next()) return -1;
            return result.getLong("id");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public User.Role getRole(long userId) {
        String sqlUser = "select role_name from user_roles where user_id = ?";
        User.Role role = User.Role.CLIENT;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sqlUser)) {
            statement.setLong(1, userId);
            ResultSet result = statement.executeQuery();

            role = User.Role.valueOf(result.getString("role_name"));
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

//    public Client getClientById(long userId) {
//        String sqlUser =
//                "select u.id, u.name, u.phone_number, u.email, u.password, a.city, a.street, a.number" +
//                "from users u join clients c on (u.id = c.user_id)" +
//                            " join addresseswhere user_id = ?";
//        User.Role role = User.Role.CLIENT;
//        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sqlUser)) {
//            statement.setLong(1, userId);
//            ResultSet result = statement.executeQuery();
//
//            role = User.Role.valueOf(result.getString("role_name"));
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//        return role;
//    }
}

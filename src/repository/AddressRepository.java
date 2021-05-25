package repository;

import config.DatabaseConnection;
import model.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressRepository {

    public long addNewAddress(Address address) {
        String sqlAddress = "insert into addresses values (null, ?, ?, ?)";
        long insertedAddressId = -1;
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sqlAddress, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setLong(3, address.getNumber());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertedAddressId = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating address failed, no ID obtained.");
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return insertedAddressId;
    }
}

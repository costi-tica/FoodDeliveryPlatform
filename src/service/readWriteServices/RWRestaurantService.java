package service.readWriteServices;

import app_core.AppData;
import model.Address;
import model.Restaurant;
import model.users.ResOwner;
import service.UserService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class RWRestaurantService extends ReadWriteService{
    private static final String FILE_PATH = DIRECTORY_PATH + "restaurants.csv";
    private static RWRestaurantService INSTANCE;

    private RWRestaurantService() {}

    public static RWRestaurantService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RWRestaurantService();
        }
        return INSTANCE;
    }

    public void read(AppData appData) {
        try {
            checkIfDirectoryAndFileExist(FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line;
            String[] resData;
            while ((line = reader.readLine()) != null) {
                resData = line.split(",");

                String name = resData[0],
                        city = resData[1],
                        street = resData[2];
                int number = Integer.parseInt(resData[3]);
                String ownerEmail = resData[4];

                ResOwner owner = (ResOwner) (new UserService()).getUserByEmail(appData.getUsers(), ownerEmail);
                if (owner == null) continue;

                Address address = new Address.Builder()
                        .withCity(city)
                        .withStreet(street)
                        .withNumber(number)
                        .build();

                Restaurant res = new Restaurant.Builder()
                        .withId(appData.getNextRestaurantId())
                        .withName(name)
                        .withAddress(address)
                        .withEmptyMenu()
                        .withNoReviews()
                        .build();

                appData.getRestaurants().add(res);
                owner.setOwnedRestaurant(res);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(Restaurant res, ResOwner owner){
        try {
            checkIfDirectoryAndFileExist(FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.APPEND);

            Address address = res.getAddress();

            writer.write(res.getName() + "," +
                    address.getCity() + "," +
                    address.getStreet() + "," +
                    address.getNumber() + "," +
                    owner.getEmail());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

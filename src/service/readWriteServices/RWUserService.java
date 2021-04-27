package service.readWriteServices;

import model.Address;
import app_core.AppData;
import model.users.Client;
import model.users.Courier;
import model.users.ResOwner;
import model.users.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;

public final class RWUserService extends ReadWriteService{
    private static final String FILE_PATH = DIRECTORY_PATH + "users.csv";
    private static RWUserService INSTANCE;

    private RWUserService() {}

    public static RWUserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RWUserService();
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
            String[] userData;
            while ((line = reader.readLine()) != null) {
                userData = line.split(",");
                String name = userData[1],
                        phoneNumber = userData[2],
                        email = userData[3],
                        password = userData[4];
                switch (User.Role.valueOf(userData[0])) {
                    case CLIENT -> {
                        String city = userData[5],
                                street = userData[6];
                        int number = Integer.parseInt(userData[7]);

                        Address address = new Address.Builder()
                                .withCity(city)
                                .withStreet(street)
                                .withNumber(number)
                                .build();

                        Client client = new Client.Builder()
                                .withId(appData.getNextUserId())
                                .withName(name)
                                .withPhoneNumber(phoneNumber)
                                .withEmail(email)
                                .withPassword(password)
                                .withAddress(address)
                                .withRole(User.Role.CLIENT)
                                .build();
                        appData.addUser(client);
                    }
                    case COURIER -> {
                        Courier courier = new Courier.Builder()
                                .withId(appData.getNextUserId())
                                .withName(name)
                                .withPhoneNumber(phoneNumber)
                                .withEmail(email)
                                .withPassword(password)
                                .withRole(User.Role.COURIER)
                                .build();
                        appData.addUser(courier);
                    }
                    case RES_OWNER -> {
                        ResOwner resOwner = new ResOwner.Builder()
                                .withId(appData.getNextUserId())
                                .withName(name)
                                .withPhoneNumber(phoneNumber)
                                .withEmail(email)
                                .withPassword(password)
                                .withRole(User.Role.RES_OWNER)
                                .build();
                        appData.addUser(resOwner);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(User user) {
        try {
            checkIfDirectoryAndFileExist(FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.APPEND);
            writer.write(user.getRole() + "," +
                    user.getName() + "," +
                    user.getPhoneNumber() + "," +
                    user.getEmail() + "," +
                    user.getPassword());
            if (user instanceof Client){
                Address address = ((Client) user).getAddress();
                String addressToString = address.getCity() + "," +
                        address.getStreet() + "," +
                        address.getNumber();
                writer.write("," + addressToString);
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

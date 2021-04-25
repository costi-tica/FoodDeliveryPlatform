package service.ReadWriteServices;

import model.Address;
import main.application.AppData;
import model.users.Client;
import model.users.Courier;
import model.users.ResOwner;
import model.users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;

public class RWUserService implements ReadWriteService{
    private static final String DIRECTORY_PATH = "resources/app_data/";
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
            checkDirectoryAndFileExist(DIRECTORY_PATH, FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String name = userData[1],
                        phoneNumber = userData[2],
                        email = userData[3],
                        password = userData[4];
                switch (User.Role.valueOf(userData[0])) {
                    case CLIENT -> {
                        String city = userData[5],
                                street = userData[6],
                                info = userData[8].equals("no") ? "" : userData[8];
                        int number = Integer.parseInt(userData[7]);

                        Address address = new Address.Builder()
                                .withCity(city)
                                .withStreet(street)
                                .withNumber(number)
                                .withAdditionalInfo(info)
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

    public void write(AppData appData) {
        //...
    }
}

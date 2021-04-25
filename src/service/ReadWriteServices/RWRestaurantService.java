package service.ReadWriteServices;

import main.application.AppData;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RWRestaurantService implements ReadWriteService{
    private static final String DIRECTORY_PATH = "resources/app_data/";
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
            checkDirectoryAndFileExist(DIRECTORY_PATH, FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line = "";
            while ((line = reader.readLine()) != null) {
                //...
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(AppData appData){
        //...
    }
}

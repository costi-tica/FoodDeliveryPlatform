package service.readWriteServices;

import app_core.AppData;
import model.Restaurant;
import model.products.Dish;
import model.products.Drink;
import model.products.Product;
import model.users.ResOwner;
import service.RestaurantService;
import service.UserService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class RWProductService extends ReadWriteService{
    private static final String FILE_PATH = DIRECTORY_PATH + "products.csv";
    private static RWProductService INSTANCE;

    private RWProductService() {}

    public static RWProductService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RWProductService();
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
            RestaurantService restaurantService = new RestaurantService();
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line;
            String[] productData;
            while ((line = reader.readLine()) != null) {
                productData = line.split(",");

                String restaurantOwnerEmail = productData[0];
                String drinkOrDish = productData[1];
                String name = productData[2];
                double price = Double.parseDouble(productData[3]);
                int quantity = Integer.parseInt(productData[4]);
                String category = productData[5];

                ResOwner owner = (ResOwner) (new UserService()).getUserByEmail(appData.getUsers(),restaurantOwnerEmail);
                if (owner == null) continue;

                Restaurant res = owner.getOwnedRestaurant();

                if (drinkOrDish.equalsIgnoreCase("drink")){
                    Drink drink = new Drink.Builder()
                            .withId(res.getNextProdId())
                            .withName(name)
                            .withPrice(price)
                            .withQuantity(quantity)
                            .withMillilitersAsUnit()
                            .build();
                    restaurantService.addDrink(res, drink, category);
                } else {
                    String[] ingredients = productData[6].split("/");
                    Dish dish = new Dish.Builder()
                            .withId(res.getNextProdId())
                            .withName(name)
                            .withPrice(price)
                            .withQuantity(quantity)
                            .withGramsAsUnit()
                            .withIngredients(ingredients)
                            .build();
                    restaurantService.addDish(res, dish, category);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void write(ResOwner resOwner, Product product, String category) {
        try {
            checkIfDirectoryAndFileExist(FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.APPEND);
            writer.write(resOwner.getEmail() + "," +
                    (product instanceof Drink ? "drink" : "dish") + "," +
                    product.getName() + "," +
                    product.getPrice() + "," +
                    product.getQuantity() + "," +
                    category);
            if (product instanceof Dish){
                writer.write("," + String.join("/", ((Dish) product).getIngredients()));
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

package service;

import model.Restaurant;
import model.products.Dish;
import model.products.Drink;
import model.products.Product;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class ProductService {
    private final Scanner scanner;

    public ProductService(){
        scanner = new Scanner(System.in);
    }

//  GET
    public Product getProductById(Restaurant res, int id){
        Map<String, List<Dish>> dishes = res.getMenu().getDishes();
        for (String categ : dishes.keySet()) for (Dish d : dishes.get(categ))
            if (d.getId() == id) return d;

        Map<String,List<Drink>> drinks = res.getMenu().getDrinks();
        for (String categ : drinks.keySet()) for (Drink d : drinks.get(categ))
            if (d.getId() == id) return d;

        return null;
    }
    public String getProductCategory(Restaurant res, int id){
        Map<String,List<Dish>> dishes = res.getMenu().getDishes();
        for (String categ : dishes.keySet()) for (Dish d : dishes.get(categ))
            if (d.getId() == id) return categ;

        Map<String,List<Drink>> drinks = res.getMenu().getDrinks();
        for (String categ : drinks.keySet()) for (Drink d : drinks.get(categ))
            if (d.getId() == id) return categ;

        return "";
    }

//  UPDATE PRODUCT FIELDS (SCANNER)
    private void updateProductFields(Product product, String[] fields){
        for (String field : fields){
            switch (field.toLowerCase()) {
                case "name" -> {
                    System.out.println("New name: ");
                    product.setName(scanner.nextLine());
                }
                case "price" -> {
                    System.out.println("New price: ");
                    product.setPrice(scanner.nextDouble());
                }
                case "quantity" -> {
                    System.out.println("New quantity/serving: ");
                    product.setQuantity(scanner.nextInt());
                    scanner.nextLine();
                }
            }
        }
    }
    public void updateDishFields(Dish dish, String[] fields){
        updateProductFields(dish, fields);
        for(String field : fields){
            if (field.equalsIgnoreCase("ingredients")){
                System.out.println("New ingredients: ('/' between ingredients)");
                dish.setIngredients(scanner.nextLine().split("/"));
                break;
            }
        }
    }
    public void updateDrinkFields(Drink drink, String[] fields){
        updateProductFields(drink, fields);
    }

//  EDIT PRODUCT FROM MENU
    public void editProduct(Restaurant res, int prodId){
        Product toEdit = getProductById(res, prodId);

        if (toEdit == null) {
            System.out.println("Product does not exist.");
            return;
        }

        System.out.println("Produsul actual: \n" + toEdit.toString() + '\n');
        System.out.println("Ce campuri doriti sa modificati? (cu / intre ele)");

        String[] fields = scanner.nextLine().split("/");
        if (toEdit instanceof Dish)
            updateDishFields((Dish)toEdit, fields);
        else
            updateDrinkFields((Drink)toEdit, fields);

    }
//  DELETE PRODUCT FROM MENU
    public void deleteProduct(Restaurant res, int prodId){
        Product toDelete = getProductById(res, prodId);
        String category = getProductCategory(res, prodId);

        if (toDelete != null){
            if (toDelete instanceof Dish)
                res.getMenu().getDishes().get(category).remove(toDelete);
            else if (toDelete instanceof Drink)
                res.getMenu().getDrinks().get(category).remove(toDelete);
        }
    }
}

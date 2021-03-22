package model.products;


import java.util.Arrays;
import java.util.Scanner;

public class Dish extends Product {
    private String[] ingredients;

    public Dish(int id, String name, double price, int quantity, String[] ingredients) {
        super(id, name, price, quantity, "g");
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return super.toString() + '\n' +
                "Ingredients: " + Arrays.toString(ingredients);
    }

    @Override
    public void updateFields(Scanner scanner, String[] fields) {
        super.updateFields(scanner, fields);
        for(String field : fields){
            if (field.equalsIgnoreCase("ingredients")){
                System.out.println("New ingredients: ('/' between ingredients)");
                this.ingredients = scanner.nextLine().split("/");
                break;
            }
        }
    }

    public String[] getIngredients() { return ingredients;  }

    public void setIngredients(String[] ingredients) { this.ingredients = ingredients; }
}

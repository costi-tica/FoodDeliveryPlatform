package model.products;


import java.util.Arrays;

public class Dish extends Product {
    private String[] ingredients;

    public Dish(){
        super();
    }

    public static class Builder{
        private final Dish dish = new Dish();

        public Builder withId(int id){
            dish.setId(id);
            return this;
        }
        public Builder withName(String name){
            dish.setName(name);
            return this;
        }
        public Builder withPrice(double price){
            dish.setPrice(price);
            return this;
        }
        public Builder withQuantity(int quantity){
            dish.setQuantity(quantity);
            return this;
        }
        public Builder withIngredients(String[] ingredients){
            dish.setIngredients(ingredients);
            return this;
        }
        public Builder withGramsAsUnit(){
            dish.setUnit("g");
            return this;
        }
        public Dish build(){
            return this.dish;
        }
    }

    @Override
    public String toString() {
        return super.toString() + '\n' +
                "Ingredients: " + Arrays.toString(ingredients);
    }

    public String[] getIngredients() { return ingredients;  }

    public void setIngredients(String[] ingredients) { this.ingredients = ingredients; }
}

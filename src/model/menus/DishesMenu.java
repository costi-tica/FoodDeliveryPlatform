package model.menus;

import model.products.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishesMenu extends Menu{
    private List<Dish> dishes;

    public DishesMenu(){
        super();
        this.dishes = new ArrayList<Dish>();
    }
    public DishesMenu(List<String> categories, List<Dish> dishes) {
        super(categories);
        this.dishes = dishes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString(){
        String prods = "";

        for (Dish d : dishes){
            prods += d.toString() + "\n----------------\n";
        }

        return "Categories: " + super.toString() + '\n' +
                "Dishes: \n" +  prods;
    }
}

package model.menus;

import jdk.jfr.Percentage;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    protected List<String> categories;

    public Menu(){
        this.categories = new ArrayList<String>();
    }
    public Menu(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getCategories() {
        return categories;
    }

    @Override
    public String toString(){
        String result = "";
        for (String cat : categories){
            result += cat + "/";
        }
        return result;
    }
}

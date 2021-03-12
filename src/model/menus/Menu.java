package model.menus;

import jdk.jfr.Percentage;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    protected List<String> categories;
    private int nextProdId;

    public Menu(){
        this.categories = new ArrayList<String>();
        this.nextProdId = 0;
    }
    public Menu(List<String> categories) {
        this.categories = categories;
        this.nextProdId = 0;
    }

    public List<String> getCategories() {
        return categories;
    }

    public int getNextProdId() {
        return nextProdId++;
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

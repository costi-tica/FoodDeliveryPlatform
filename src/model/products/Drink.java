package model.products;

public final class Drink extends Product {
    public Drink(){}

    public static class Builder{
        private final Drink drink = new Drink();

        public Builder withId(int id){
            drink.setId(id);
            return this;
        }
        public Builder withName(String name){
            drink.setName(name);
            return this;
        }
        public Builder withPrice(double price){
            drink.setPrice(price);
            return this;
        }
        public Builder withQuantity(int quantity){
            drink.setQuantity(quantity);
            return this;
        }
        public Builder withMillilitersAsUnit(){
            drink.setUnit("ml");
            return this;
        }
        public Drink build(){
            return this.drink;
        }
    }

    @Override
    public String toString(){
        return super.toString();
    }
}

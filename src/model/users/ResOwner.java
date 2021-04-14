package model.users;

import model.Restaurant;

public final class ResOwner extends User{
    private Restaurant ownedRestaurant;

    public ResOwner(){}

    public static class Builder extends User.Builder<ResOwner, Builder>{
        public Builder(){
            super(new ResOwner());
        }

        @Override
        protected Builder getThis(){
            return this;
        }

        public Builder withRestaurant(Restaurant res){
            this.user.setOwnedRestaurant(res);
            return this;
        }
    }

    public Restaurant getOwnedRestaurant() {
        return ownedRestaurant;
    }

    public void setOwnedRestaurant(Restaurant ownedRestaurant) {
        this.ownedRestaurant = ownedRestaurant;
    }
}

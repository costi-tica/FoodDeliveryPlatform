package model.users;


public final class Courier extends User{
    private boolean busy = false;

    public Courier(){}

    public static class Builder extends User.Builder<Courier, Builder>{
        public Builder(){
            super(new Courier());
        }

        @Override
        protected Builder getThis(){
            return this;
        }
    }

    @Override
    public String toString(){
        return super.toString() + '\n' +
                "Is busy: " + this.busy;

    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}

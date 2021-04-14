package model.users;

public final class AppAdmin extends User{
    private Rank rank;

    public enum Rank {
        LIEUTENANT,
        CAPTAIN,
        MAJOR,
        COLONEL,
        GENERAL
    }

    public AppAdmin(){}

    public static class Builder extends User.Builder<AppAdmin, Builder>{
        public Builder(){
            super(new AppAdmin());
        }

        @Override
        protected Builder getThis(){
            return this;
        }

        public Builder withRank(Rank rank){
            this.user.setRank(rank);
            return this;
        }
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}

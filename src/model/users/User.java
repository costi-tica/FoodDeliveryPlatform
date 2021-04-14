package model.users;

import model.Address;

public abstract class User {
    protected int id;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected String password;
    protected Role role;
    protected boolean loggedIn = false;

    public enum Role {
        CLIENT,
        COURIER,
        RES_OWNER,
        APP_ADMIN
    }

    public User(){}

    abstract public static class Builder<T extends User, B extends Builder<T,B>> {
        protected T user;

        public Builder(T user){
            this.user = user;
        }

        abstract protected B getThis();

        public B withId(int id){
            user.setId(id);
            return getThis();
        }
        public B withName(String name){
            user.setName(name);
            return getThis();
        }
        public B withPhoneNumber(String phoneNumber){
            user.setPhoneNumber(phoneNumber);
            return getThis();
        }
        public B withEmail(String email){
            user.setEmail(email);
            return getThis();
        }
        public B withPassword(String password){
            user.setPassword(password);
            return getThis();
        }
        public B withRole(User.Role role){
            user.setRole(role);
            return getThis();
        }

        public T build(){
            return user;
        }
    }

    @Override
    public String toString(){
        return "Name: " + name + '\n' +
                "Phone number: " + phoneNumber + '\n' +
                "Email: " + email + '\n' +
                "Role: " + role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}

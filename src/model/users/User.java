package model.users;

public abstract class User {
    protected int id;
    protected String name;
    protected String phoneNumber;

    public User(){}

    @Override
    public String toString(){
        return "Name: " + name + '\n' +
                "Phone number: " + phoneNumber;
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
}

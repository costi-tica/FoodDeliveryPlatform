package main;


import main.application.Application;

public class Main {
    public static void main(String[] args) {
        Application app = Application.getInstance();
        app.start();
    }
}

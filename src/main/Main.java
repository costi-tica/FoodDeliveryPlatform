package main;

import main.app.Application;

public class Main {
    public static void main(String[] args) {
        Application app = Application.getInstance();
        app.start();
    }
}


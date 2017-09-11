package com.codecool.snake;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        System.setProperty("quantum.multithreaded", "false");
        Application.launch(Snake.class, args);
    }

}

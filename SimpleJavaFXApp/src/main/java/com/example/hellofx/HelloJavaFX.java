package com.example.hellofx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

    @Override
    public void start(Stage stage) {

        Label message = new Label("Welcome, Prince Nkandu");

        Button button = new Button("Start");

        button.setOnAction(event ->
                message.setText("Well done Prince - 202506829.")
        );

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event ->message.setText("Welcome, Prince Nkandu")
        );


        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(message, button, resetButton);

        Scene scene = new Scene(layout, 500, 300);

        stage.setTitle("My First JavaFX Application - SN 202506829");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
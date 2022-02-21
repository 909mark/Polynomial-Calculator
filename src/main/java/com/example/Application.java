package com.example;

import com.example.model.Monomial;
import com.example.model.Polynomial;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
        Polynomial p1 = new Polynomial();
        p1.addMonom(new Monomial(1, 1));
        p1.addMonom(new Monomial(2, 2));
        Polynomial p2 = new Polynomial();
        p2.addMonom(new Monomial(1, 4));
        p2.addMonom(new Monomial(3, 2));
        Polynomial res = p2.add(p1);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(res);
    }
}
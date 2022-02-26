package com.example;

import com.example.controller.PolMath;
import com.example.model.Monomial;
import com.example.model.Polynomial;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    private static void demo() {
        Polynomial p1 = new Polynomial();
        PolMath.addMonom(p1, new Monomial(1, 1));
        PolMath.addMonom(p1, new Monomial(0, 1));
        Polynomial p2 = new Polynomial();
        PolMath.addMonom(p2, new Monomial(1, 1));
        PolMath.addMonom(p2, new Monomial(0, 1));
        System.out.println(p1);
        System.out.println(p2);
        Polynomial res = PolMath.add(p1, p2);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(res);
        res = PolMath.subtract(p1, p2);
        System.out.println(res);
        res = PolMath.multiply(p1, p2);
        System.out.println(res);
        res = PolMath.integralOf(p1);
        System.out.println(res);
        res = PolMath.integralOf(p2);
        System.out.println(res);
        res = PolMath.derivativeOf(p1);
        System.out.println(res);
        res = PolMath.derivativeOf(p2);
        System.out.println(res);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
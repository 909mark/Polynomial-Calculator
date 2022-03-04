module com.example.pt2022_30221_vicsi_mark_otto_assignment_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.calculator to javafx.fxml;
    exports com.calculator;
    exports com.calculator.controller;
    exports com.calculator.model;
    opens com.calculator.controller to javafx.fxml;
}
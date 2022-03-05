package com.calculator.controller;

import com.calculator.constants.CONSTANTS;
import com.calculator.controller.builder.Builder;
import com.calculator.model.Polynomial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {

    public static final Background INPUT_FOCUSED = new Background(
            new BackgroundFill(Color.rgb(220, 220, 220), new CornerRadii(10), Insets.EMPTY)
    );
    public static final Background INPUT_UNFOCUSED = new Background(
            new BackgroundFill(Color.rgb(180, 180, 180), new CornerRadii(10), Insets.EMPTY)
    );

    private int inputCurrentLength = 0;
    private Label inputCurrent;
    @FXML
    private Label input1;
    @FXML
    private Label input2;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private Label result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox.getItems().addAll(
                "Add",
                "Subtract",
                "Multiply",
                "Divide",
                "Derive",
                "Integrate"
        );
        inputCurrent = input1;
    }

    @FXML
    protected void mathInput(ActionEvent event) {
        if (inputCurrent.getText().contains("polynomial")) {
            inputCurrentLength = 0;
            inputCurrent.setText("");
        }
        if (inputCurrentLength < CONSTANTS.MAX_INPUT_LENGTH) {
            String in = ((Button) event.getSource()).getText();
            if ("X".equals(in))
                in += "^";
            inputCurrent.setText(inputCurrent.getText() + in);
            inputCurrentLength++;
        }
    }

    @FXML
    public void delete() {
        inputCurrentLength = inputCurrent.getText().length();
        if (inputCurrentLength > 0) {
            inputCurrent.setText(inputCurrent.getText().substring(0, inputCurrentLength - 1));
            inputCurrentLength--;
        }
    }

    @FXML
    public void deleteAll() {
        inputCurrent.setText("");
        inputCurrentLength = 0;
    }

    @FXML
    public void changeOutput(MouseEvent event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            inputCurrent.setBackground(INPUT_FOCUSED);
            inputCurrent = (Label) event.getSource();
            inputCurrent.setBackground(INPUT_UNFOCUSED);
            inputCurrentLength = inputCurrent.getText().length();
        }
    }

    @FXML
    public void calculate() {
        if (checkComboBox(combobox) == -1)
            return;
        Polynomial pol1 = parseInput(input1.getText());
        Polynomial pol2 = parseInput(input2.getText());

        switch (combobox.getSelectionModel().getSelectedItem()) {
            case "Add" -> result.setText(PolMath.add(pol1, pol2).toString());
            case "Subtract" -> result.setText(PolMath.subtract(pol1, pol2).toString());
            case "Multiply" -> result.setText(PolMath.multiply(pol1, pol2).toString());
            case "Divide" -> {
                List<Polynomial> res = PolMath.divide(pol1, pol2);
                result.setText(res.get(0).toString() + ", r = " + res.get(1).toString());
            }
            case "Derive" -> result.setText(PolMath.derivativeOf(pol1).toString());
            case "Integrate" -> result.setText(PolMath.integralOf(pol1) + "+C");
            default -> result.setText("Whoops");
        }
    }

    @FXML
    public Polynomial parseInput(String input) {
        Pattern pattern = Pattern.compile(CONSTANTS.INPUT_PATTERN);
        Matcher matcher = pattern.matcher(input);
        if (input.isBlank() || input.contains("polynom"))
            return new Polynomial();
        if (!matcher.matches()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Incorrect input!");
            a.show();
            return new Polynomial();
        } else {
            return Builder.polynomial(input);
        }
    }

    public int checkComboBox(ComboBox<String> combobox) {
        if (combobox.getSelectionModel().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("You must choose an operation first!");
            a.show();
            return -1;
        }
        return 0;
    }

}
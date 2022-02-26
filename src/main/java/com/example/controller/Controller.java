package com.example.controller;

import com.example.constans.CONSTANTS;
import com.example.controller.builder.Builder;
import com.example.model.Polynomial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private int outputLength = 0;

    @FXML
    private Label output;

    @FXML
    protected void mathInput(ActionEvent event) {
        if (output.getText().matches("[Pp]olynom")) {
            outputLength = 0;
            output.setText("");
        }
        if (outputLength < CONSTANTS.MAX_INPUT_LENGTH) {
            String in = ((Button) event.getSource()).getText();
            if ("X".equals(in))
                in += "^";
            output.setText(output.getText() + in);
            outputLength++;
        }
    }

    @FXML
    public void delete() {
        outputLength = output.getText().length();
        if (outputLength > 0) {
            output.setText(output.getText().substring(0, outputLength - 1));
            outputLength--;
        }
    }

    @FXML
    public void deleteAll() {
        output.setText("");
        outputLength = 0;
    }

    @FXML
    public void changeOutput(MouseEvent event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            output.setBackground(new Background(
                    new BackgroundFill(Color.rgb(220, 220, 220), new CornerRadii(10), Insets.EMPTY)
            ));
            output = (Label) event.getSource();
            output.setBackground(new Background(
                    new BackgroundFill(Color.rgb(180, 180, 180), new CornerRadii(10), Insets.EMPTY)
            ));
            outputLength = output.getText().length();
        }
    }

    @FXML
    public void calculate(ActionEvent event) {
        Polynomial res = parseInput((output.getText()));
        if (res != null) {
            output.setText(res.toString());
        } else {
            output.setText("ERROR");
        }
    }

    @FXML
    public Polynomial parseInput(String input) {
        Pattern pattern = Pattern.compile(CONSTANTS.INPUT_PATTERN);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Incorrect input!");
            a.show();
        } else {
            return Builder.polynomial(input);
        }
        return null;
    }


}
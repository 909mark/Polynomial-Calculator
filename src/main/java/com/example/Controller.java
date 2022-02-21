package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.example.constans.CONSTANTS;
import javafx.scene.input.MouseEvent;

public class Controller {

    private int outputLength = 0;

    @FXML
    private Label output1;

    @FXML
    protected void mathInput(ActionEvent event) {
        if (outputLength < CONSTANTS.MAX_INPUT_LENGTH) {
            output1.setText(output1.getText() + ((Button) event.getSource()).getText());
            outputLength++;
        }
    }

    public void delete() {
        outputLength = output1.getText().length();
        if (outputLength > 0) {
            output1.setText(output1.getText().substring(0, outputLength - 1));
            outputLength--;
        }
    }

    public void changeOutput(MouseEvent event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            output1 = (Label) event.getSource();
        }
    }
}
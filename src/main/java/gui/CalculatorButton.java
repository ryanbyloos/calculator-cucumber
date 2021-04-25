package gui;

import javafx.scene.control.Button;

public abstract class CalculatorButton extends Button {
    String value;
    public CalculatorButton(String value) {
        this.value = value;
        this.setText(value);
    }
}

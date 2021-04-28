package gui;

import Converter.Unit;
import calculator.Calculator;
import calculator.RealNumber;
import calculator.exceptions.IllegalConstruction;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConverterController extends MainController implements Initializable {

    public Calculator calculator;


    @FXML
    public ComboBox<String> unitComboBox;
    public ComboBox<String> fromComboBox;
    public ComboBox<String> toComboBox;
    public VBox converterVBox;
    public Label answerScreen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainVBox = converterVBox;
        ArrayList<String> typeOfUnit = new ArrayList<>();
        for (Unit u : Unit.values()) {
            if (!typeOfUnit.contains(u.getType()))
                typeOfUnit.add(u.getType());
        }
        if (unitComboBox != null)
            unitComboBox.getItems().addAll(typeOfUnit);

        calculator = new Calculator(Calculator.Mode.REAL);
    }

    @Override
    public void getResult(Event e) throws IllegalConstruction {
        String screenValue = calculatorScreen.getText();
        calculatorScreen.setText(screenValue);

        String res = calculator.convert(new RealNumber(screenValue), Unit.valueOf(fromComboBox.getValue()), Unit.valueOf(toComboBox.getValue())).toString();
        answerScreen.setText(res);
    }

    @FXML
    public void unitChoice(Event e) {
        ArrayList<String> unitList = new ArrayList<>();
        String unit = ((ComboBox) e.getSource()).getValue().toString();
        for (Unit u : Unit.values()) {
            if (u.getType().equals(unit)) {
                unitList.add(u.getFullName());
            }
        }
        fromComboBox.getItems().clear();
        toComboBox.getItems().clear();
        fromComboBox.getItems().addAll(unitList);
        toComboBox.getItems().addAll(unitList);
    }

}

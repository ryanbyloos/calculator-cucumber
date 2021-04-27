package gui;

import Converter.Unit;
import calculator.Calculator;
import calculator.Expression;
import calculator.Parser;
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

public class ConverterController implements Initializable {

    public Calculator calculator;
    private Scene calculatorScene;

    @FXML
    public TextField calculatorScreen;
    public ComboBox<String> unitComboBox;
    public ComboBox<String> fromComboBox;
    public ComboBox<String> toComboBox;
    public VBox controllerVBox;
    public Label answerScreen;
    public CheckMenuItem calculatorMenuItem;
    public CheckMenuItem converterMenuItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> typeOfUnit = new ArrayList<>();
        for (Unit u : Unit.values()) {
            if (!typeOfUnit.contains(u.getType()))
                typeOfUnit.add(u.getType());
        }
        if (unitComboBox!=null)
            unitComboBox.getItems().addAll(typeOfUnit);

        calculator = new Calculator(Calculator.Mode.REAL);
    }

    @FXML
    public void defaultButtonClicked(Event e){
        String buttonValue = ((Button) e.getSource()).getText();
        calculatorScreen.setText(calculatorScreen.getText()+buttonValue);
    }

    @FXML
    public void getResult(Event e) throws IllegalConstruction {
        String screenValue = calculatorScreen.getText();
        calculatorScreen.setText(screenValue);

        String res = calculator.convert(new RealNumber(screenValue), Unit.valueOf(fromComboBox.getValue()), Unit.valueOf(toComboBox.getValue())).toString();
        answerScreen.setText(res);
    }

    @FXML
    public void exitApplication(Event e) {
        Platform.exit();
    }

    @FXML
    public void unitChoice(Event e) {
        ArrayList<String> unitList = new ArrayList<>();
        String unit = ((ComboBox) e.getSource()).getValue().toString();
        for (Unit u : Unit.values()){
            if (u.getType().equals(unit)){
                unitList.add(u.getFullName());
            }
        }
        fromComboBox.getItems().clear();
        toComboBox.getItems().clear();
        fromComboBox.getItems().addAll(unitList);
        toComboBox.getItems().addAll(unitList);
    }

    @FXML
    public void openCalculatorScene(Event e){
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) controllerVBox.getScene().getWindow();
        stage.setScene(calculatorScene);
    }

    public void setCalculatorScene(Scene scene){
        calculatorScene = scene;
    }
}

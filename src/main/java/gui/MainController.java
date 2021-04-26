package gui;

import Converter.Unit;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public TextField calculatorScreen;
    public Label answerScreen;
    public ComboBox<String> unitComboBox;
    public ComboBox<String> fromComboBox;
    public ComboBox<String> toComboBox;

    @FXML
    public void defaultButtonClicked(Event e){
        String buttonValue = ((Button) e.getSource()).getText();
        calculatorScreen.setText(calculatorScreen.getText()+buttonValue);
    }

    @FXML
    public void functionButtonClicked(Event e){
        String function = ((Button) e.getSource()).getText()+"(";
        String screenValue = calculatorScreen.getText();
        String res = (screenValue.equals("") || screenValue.endsWith("(")) ? screenValue+function : screenValue+"*"+function;
        calculatorScreen.setText(res);
    }

    @FXML
    public void clearScreen(Event e){
        calculatorScreen.setText("");
    }

    @FXML
    public void removeCharacter(Event e){
        //TODO Remove a character or a function when pressing delete
    }

    @FXML
    public void getResult(Event e){
        //TODO Show the result on the answerScreen
        String screenValue = calculatorScreen.getText();
        StringBuilder parenthesisToAdd = new StringBuilder();
        int leftP = (int) screenValue.chars().filter(c -> c == '(').count();
        int rightP = (int) screenValue.chars().filter(c -> c == ')').count();
        parenthesisToAdd.append(")".repeat(Math.max(0, leftP - rightP)));
        calculatorScreen.setText(screenValue+parenthesisToAdd);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] typeOfUnit = {"Length", "Volume", "Mass", "Area", "Speed", "Energy", "Pressure", "Power", "Time", "Currency"};
        if (unitComboBox!=null)
            unitComboBox.getItems().addAll(typeOfUnit);
    }
}

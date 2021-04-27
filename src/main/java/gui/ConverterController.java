package gui;

import Converter.Unit;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConverterController implements Initializable {

    public TextField calculatorScreen;
    private Scene calculatorScene;

    @FXML
    public ComboBox<String> unitComboBox;
    public ComboBox<String> fromComboBox;
    public ComboBox<String> toComboBox;
    public VBox controllerVBox;
    public Label answerScreen;
    public CheckMenuItem calculatorMenuItem;
    public CheckMenuItem converterMenuItem;

    @FXML
    public void exitApplication(Event e) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> typeOfUnit = new ArrayList<>();
        for (Unit u : Unit.values()) {
            if (!u.getType().contains(u.getType()))
                typeOfUnit.add(u.getType());
        }
        if (unitComboBox!=null)
            unitComboBox.getItems().addAll(typeOfUnit);
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

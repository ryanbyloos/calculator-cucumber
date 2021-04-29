package gui;

import Converter.Temperature;
import Converter.Unit;
import calculator.Calculator;
import calculator.RealNumber;
import calculator.exceptions.IllegalConstruction;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Converter controller.
 */
public class ConverterController extends MainController implements Initializable {

    public Calculator calculator;


    @FXML
    public ComboBox<String> unitComboBox;
    public ComboBox<String> fromComboBox;
    public ComboBox<String> toComboBox;
    public VBox converterVBox;
    public Label answerScreen;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainVBox = converterVBox;
        ArrayList<String> typeOfUnit = new ArrayList<>();
        for (Unit u : Unit.values()) {
            if (!typeOfUnit.contains(u.getType()))
                typeOfUnit.add(u.getType());
        }
        for (Temperature t : Temperature.values()) {
            if (!typeOfUnit.contains(t.getType()))
                typeOfUnit.add(t.getType());
        }
        if (unitComboBox != null)
            unitComboBox.getItems().addAll(typeOfUnit);
        calculator = new Calculator(Calculator.Mode.REAL);
    }

    /**
     * @param e
     * @throws IllegalConstruction
     */
    @Override
    public void getResult(Event e) throws IllegalConstruction {
        if (unitComboBox.getValue() == null || fromComboBox == null ||toComboBox == null )
        {
            showAlert("You need to select an unit type, a starting and end unit ");
            return;
        }
        try
        {
            String res;
            String screenValue = calculatorScreen.getText();
            if (unitComboBox.getValue().equals("Temperature"))
                res = calculator.convert(new RealNumber(screenValue), Temperature.valueOf(fromComboBox.getValue()), Temperature.valueOf(toComboBox.getValue())).toString();
            else
                res = calculator.convert(new RealNumber(screenValue), Unit.valueOf(fromComboBox.getValue()), Unit.valueOf(toComboBox.getValue())).toString();
            answerScreen.setText(res);
        } catch (Exception exception)
        {
            showAlert("Wrong Input");
            return;
        }
    }

    /**
     * Unit choice.
     *
     * @param e the e
     */
    @FXML
    public void unitChoice(Event e) {
        ArrayList<String> unitList = new ArrayList<>();
        String unit = ((ComboBox) e.getSource()).getValue().toString();
        for (Unit u : Unit.values()) {
            if (u.getType().equals(unit)) {
                unitList.add(u.getFullName());
            }
        }
        for (Temperature t : Temperature.values()) {
            if (t.getType().equals(unit)) {
                unitList.add(t.getFullName());
            }
        }
        fromComboBox.getItems().clear();
        toComboBox.getItems().clear();
        fromComboBox.getItems().addAll(unitList);
        toComboBox.getItems().addAll(unitList);
    }

}

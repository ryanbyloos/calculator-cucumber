package gui;

import calculator.exceptions.IllegalConstruction;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import time.MyDate;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class DateController extends MainController implements Initializable {

    @FXML
    public VBox dateVBox;
    public TextField firstScreen;
    public TextField secondScreen;
    public ComboBox<String> dateComboBox;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainVBox = dateVBox;
        dateComboBox.getItems().addAll("ADDITION", "SUBTRACTION");
    }

    @Override
    public void clearScreen(Event e) {
        firstScreen.setText("");
        secondScreen.setText("");
        answerScreen.setText("");
    }

    /**
     * @param e
     * @throws IllegalConstruction
     */
    @Override
    public void getResult(Event e) throws IllegalConstruction {
        MyDate date1 = new MyDate(firstScreen.getText());
        MyDate date2 = new MyDate(secondScreen.getText());
        if (dateComboBox.getValue()==null) {
            dateComboBox.setValue("ADDITION");
        }
        String res = dateComboBox.getValue().equals("SUBTRACTION") ? date1.minus(date2).toString() : date1.plus(date2.toString()).toString();
        answerScreen.setText(res);
    }
}

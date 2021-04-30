package gui;

import calculator.exceptions.IllegalConstruction;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import time.MyDate;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller used for the Date Scene
 */
public class DateController extends MainController implements Initializable {

    @FXML
    public VBox dateVBox;
    public DatePicker firstDatePicker;
    public DatePicker secondDatePicker;
    public TextField secondScreen;
    public ComboBox<String> dateComboBox;

    /**
     * Initialize the Date scene, by getting the possible operations.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainVBox = dateVBox;
        dateComboBox.getItems().addAll("ADDITION", "SUBTRACTION", "TIME BETWEEN");
    }

    /**
     * Clear the screens
     *
     * @param e The event when the "C" button is pressed
     */
    @Override
    public void clearScreen(Event e) {
        firstDatePicker.getEditor().clear();
        secondDatePicker.getEditor().clear();
        secondScreen.setText("");
        answerScreen.setText("");
    }

    /**
     * Changing the operation
     *
     * @param e The event when we choose an operation in the drop-down menu
     */
    @FXML
    public void changeOperation(Event e){
        if (dateComboBox.getValue().equals("TIME BETWEEN")) {
            secondDatePicker.setVisible(true);
            secondScreen.setVisible(false);
            clearScreen(e);
        }
        else {
            secondDatePicker.setVisible(false);
            secondScreen.setVisible(true);
            clearScreen(e);
        }
    }

    /**
     * Create MyDate objects that computes the result, and show it on screen.
     *
     * @param e The event when the "=" button is pressed
     */
    @Override
    public void getResult(Event e) throws IllegalConstruction {
        try {
            MyDate date1 = new MyDate(firstDatePicker.getValue());
            MyDate date2;
            if (dateComboBox.getValue()==null) {
                dateComboBox.setValue("ADDITION");
            }
            if (dateComboBox.getValue().equals("TIME BETWEEN"))
                date2 = new MyDate(secondDatePicker.getValue());
            else
                date2 = new MyDate(secondScreen.getText());

            String res = dateComboBox.getValue().equals("ADDITION") ? date1.plus(date2).toString() : date1.minus(date2).toString();
            answerScreen.setText(res);
        }catch (Exception exception)
        {
            showAlert("Wrong Input");
        }
    }
}

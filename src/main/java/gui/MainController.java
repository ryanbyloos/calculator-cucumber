package gui;

import calculator.exceptions.IllegalConstruction;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * It is the parent of all the controller of the project. It contains the methods that are used by all the controllers.
 */
public abstract class MainController {

    private Scene converterScene;
    private Scene calculatorScene;
    private Scene dateScene;

    @FXML
    public VBox mainVBox;
    public TextField calculatorScreen;
    public Label answerScreen;

    /**
     * Call something in the model that computes the result, and show it on screen.
     *
     * @param e The event when the "=" button is pressed
     */
    public abstract void getResult(Event e) throws IllegalConstruction;

    /**
     * Exit the application
     *
     * @param e The event when the "exit" menu item is pressed
     */
    @FXML
    public void exitApplication(Event e) {
        Platform.exit();
    }

    /**
     * Take what is written on the button and add it on screen.
     *
     * @param e The event when any non-special button is pressed
     */
    @FXML
    public void defaultButtonClicked(Event e) {
        String buttonValue = ((Button) e.getSource()).getText();
        calculatorScreen.setText(calculatorScreen.getText() + buttonValue);
    }

    /**
     * Delete the character or the function at the end of the screen
     *
     * @param e The event when the "DEL" button is pressed
     */
    @FXML
    public void deleteButtonClicked(Event e) {
        String s = calculatorScreen.getText();
        if (s.endsWith("sin(") || s.endsWith("cos(") || s.endsWith("tan(") || s.endsWith("exp(") || s.endsWith("log("))
            calculatorScreen.setText(s.substring(0, s.length()-4));
        else if (s.endsWith("asin(") || s.endsWith("acos(") || s.endsWith("atan(") || s.endsWith("sinh(") || s.endsWith("cosh(") || s.endsWith("tanh(") || s.endsWith("sqrt("))
            calculatorScreen.setText(s.substring(0, s.length()-5));
        else if (s.endsWith("asinh(") || s.endsWith("acosh(") || s.endsWith("atanh("))
            calculatorScreen.setText(s.substring(0, s.length()-6));
        else if(!calculatorScreen.getText().isEmpty())
            calculatorScreen.setText(s.substring(0, s.length() - 1));
    }

    /**
     * Clear the screens
     *
     * @param e The event when the "C" button is pressed
     */
    @FXML
    public void clearScreen(Event e) {
        calculatorScreen.setText("");
        answerScreen.setText("");
    }

    /**
     * Change the current scene to the converter scene.
     *
     * @param e The event when the "Converter" menu item is pressed
     */
    @FXML
    public void openConverterScene(Event e) {
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) mainVBox.getScene().getWindow();
        stage.setScene(converterScene);
        stage.setTitle("Converter");
    }

    /**
     * Change the current scene to the calculator scene.
     *
     * @param e The event when the "Calculator" menu item is pressed
     */
    @FXML
    public void openCalculatorScene(Event e) {
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) mainVBox.getScene().getWindow();
        stage.setScene(calculatorScene);
        stage.setTitle("Calculator");
    }

    /**
     * Change the current scene to the date scene.
     *
     * @param e The event when the "Date" menu item is pressed
     */
    @FXML
    public void openDateScene(Event e) {
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) mainVBox.getScene().getWindow();
        stage.setScene(dateScene);
        stage.setTitle("Date calculator");
    }

    public void setConverterScene(Scene scene) {
        converterScene = scene;
    }
    public void setCalculatorScene(Scene scene) {
        calculatorScene = scene;
    }
    public void setDateScene(Scene scene) {
        dateScene = scene;
    }
}

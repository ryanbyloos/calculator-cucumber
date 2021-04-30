package gui;

import calculator.exceptions.IllegalConstruction;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
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
     * @param e
     * @throws IllegalConstruction
     */
    public abstract void getResult(Event e) throws IllegalConstruction;

    /**
     * @param e
     */
    @FXML
    public void exitApplication(Event e) {
        Platform.exit();
    }

    /**
     * @param e
     */
    @FXML
    public void defaultButtonClicked(Event e) {
        String buttonValue = ((Button) e.getSource()).getText();
        calculatorScreen.setText(calculatorScreen.getText() + buttonValue);
    }

    /**
     * @param e
     */
    @FXML
    public void removeCharacter(Event e) {
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
     * @param e
     */
    @FXML
    public void clearScreen(Event e) {
        calculatorScreen.setText("");
        answerScreen.setText("");
    }

    /**
     * @param e
     */
    @FXML
    public void openConverterScene(Event e) {
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) mainVBox.getScene().getWindow();
        stage.setScene(converterScene);
        stage.setTitle("Converter");
        stage.setHeight(360);
        stage.setWidth(330);
    }
    /**
     * @param scene
     */
    public void setConverterScene(Scene scene) {
        converterScene = scene;
    }

    /**
     * @param e
     */
    @FXML
    public void openCalculatorScene(Event e) {
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) mainVBox.getScene().getWindow();
        stage.setScene(calculatorScene);
        stage.setTitle("Calculator");
        stage.setHeight(380);
        stage.setWidth(640);
    }

    /**
     * @param scene
     */
    public void setCalculatorScene(Scene scene) {
        calculatorScene = scene;
    }

    /**
     * @param e
     */
    @FXML
    public void openDateScene(Event e) {
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) mainVBox.getScene().getWindow();
        stage.setScene(dateScene);
        stage.setTitle("Date calculator");
        stage.setHeight(200);
        stage.setWidth(340);
    }
    public void showAlert(String text)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /**
     * @param scene
     */
    public void setDateScene(Scene scene) {
        dateScene = scene;
    }
}

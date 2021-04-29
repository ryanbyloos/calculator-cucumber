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
        //TODO Remove a character or a function when pressing delete
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
    }

    /**
     * @param scene
     */
    public void setDateScene(Scene scene) {
        dateScene = scene;
    }
}

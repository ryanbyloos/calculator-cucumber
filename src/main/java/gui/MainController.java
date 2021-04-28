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

public abstract class MainController {

    private Scene converterScene;
    private Scene calculatorScene;
    private Scene dateScene;

    @FXML
    public VBox mainVBox;
    public TextField calculatorScreen;
    public Label answerScreen;

    public abstract void getResult(Event e) throws IllegalConstruction;

    @FXML
    public void exitApplication(Event e) {
        Platform.exit();
    }

    @FXML
    public void defaultButtonClicked(Event e) {
        String buttonValue = ((Button) e.getSource()).getText();
        calculatorScreen.setText(calculatorScreen.getText() + buttonValue);
    }

    @FXML
    public void removeCharacter(Event e) {
        //TODO Remove a character or a function when pressing delete
    }


    @FXML
    public void clearScreen(Event e) {
        calculatorScreen.setText("");
        answerScreen.setText("");
    }

    @FXML
    public void openConverterScene(Event e) {
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) mainVBox.getScene().getWindow();
        stage.setScene(converterScene);
        stage.setTitle("Converter");
    }

    public void setConverterScene(Scene scene) {
        converterScene = scene;
    }

    @FXML
    public void openCalculatorScene(Event e) {
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) mainVBox.getScene().getWindow();
        stage.setScene(calculatorScene);
        stage.setTitle("Calculator");
    }

    public void setCalculatorScene(Scene scene) {
        calculatorScene = scene;
    }

    @FXML
    public void openDateScene(Event e) {
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) mainVBox.getScene().getWindow();
        stage.setScene(dateScene);
        stage.setTitle("Date calculator");
    }

    public void setDateScene(Scene scene) {
        dateScene = scene;
    }
}

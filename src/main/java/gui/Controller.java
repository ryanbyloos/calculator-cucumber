package gui;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    public TextField calculatorScreen;
    public Label answerScreen;

    @FXML
    public void defaultButtonClicked(Event e){
        String buttonValue = ((Button) e.getSource()).getText();
        calculatorScreen.setText(calculatorScreen.getText()+buttonValue);
    }

    @FXML
    public void functionButtonClicked(Event e){
        String function = ((Button) e.getSource()).getText()+"(";
        String screenValue = calculatorScreen.getText();
        String res = (screenValue.equals("")) ? function : screenValue+"*"+function;
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
    }

    @FXML
    public void exitApplication(Event e) {
        Platform.exit();
    }
}

package gui;

import calculator.Calculator;
import calculator.Expression;
import calculator.Parser;
import calculator.exceptions.IllegalConstruction;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    private Scene converterScene;

    public Calculator calculator;

    @FXML
    public VBox calculatorVBox;
    public Label answerScreen;
    public CheckMenuItem calculatorMenuItem;
    public CheckMenuItem converterMenuItem;
    public TextField calculatorScreen;

    @FXML
    public void exitApplication(Event e) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calculator = new Calculator(Calculator.Mode.REAL);
    }

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
    public void removeCharacter(Event e){
        //TODO Remove a character or a function when pressing delete
    }

    @FXML
    public void getResult(Event e) throws IllegalConstruction {
        String screenValue = calculatorScreen.getText();
        StringBuilder parenthesisToAdd = new StringBuilder();
        int leftP = (int) screenValue.chars().filter(c -> c == '(').count();
        int rightP = (int) screenValue.chars().filter(c -> c == ')').count();
        parenthesisToAdd.append(")".repeat(Math.max(0, leftP - rightP)));
        String res = screenValue+parenthesisToAdd;
        calculatorScreen.setText(res);

        Parser parser = new Parser(res);
        Expression expression = parser.getExpression(calculator);
        answerScreen.setText(calculator.eval(expression));
    }

    @FXML
    public void clearScreen(Event e){
        calculatorScreen.setText("");
    }

    @FXML
    public void openConverterScene(Event e){
        ((CheckMenuItem) e.getSource()).setSelected(false);
        Stage stage = (Stage) calculatorVBox.getScene().getWindow();
        stage.setScene(converterScene);
    }

    public void setConverterScene(Scene scene){
        converterScene = scene;
    }
}

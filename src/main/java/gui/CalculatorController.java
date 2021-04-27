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
import javafx.scene.control.*;
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
    public ComboBox<Calculator.Mode> modeComboBox;
    public Button integerTrigger;

    @FXML
    public void exitApplication(Event e) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calculator = new Calculator(Calculator.Mode.REAL);
        modeComboBox.getItems().addAll(Calculator.Mode.values());
    }

    @FXML
    public void changeMode(Event e){
        calculator = new Calculator((Calculator.Mode) ((ComboBox) e.getSource()).getValue());
        integerTrigger.setDisable(!integerTrigger.isDisable());
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
        if(function.equals("√(")){
            function = "sqrt(";
        }
        String res = (screenValue.equals("") || screenValue.endsWith("(")) ? screenValue+function : screenValue+"*"+function;
        calculatorScreen.setText(res);
    }

    @FXML
    public void inverseFunctionButtonClicked(Event e){
        String screenValue = calculatorScreen.getText();
        String res = "1/("+screenValue;
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
    public void getAnswerValue(){
        calculatorScreen.setText(answerScreen.getText());
    }

    @FXML
    public void clearScreen(Event e){
        calculatorScreen.setText("");
        answerScreen.setText("");
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

package gui;

import calculator.Calculator;
import calculator.Expression;
import calculator.Parser;
import calculator.exceptions.IllegalConstruction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController extends MainController implements Initializable {


    public Calculator calculator;
    private Stage funStage;

    @FXML
    public VBox calculatorVBox;
    public ComboBox<Calculator.Mode> modeComboBox;
    public Button integerTrigger;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainVBox = calculatorVBox;
        calculator = new Calculator(Calculator.Mode.REAL);
        modeComboBox.getItems().addAll(Calculator.Mode.values());
    }

    @FXML
    public void changeMode(Event e) {
        calculator = new Calculator((Calculator.Mode) ((ComboBox) e.getSource()).getValue());
        integerTrigger.setDisable(!integerTrigger.isDisable());
    }

    @FXML
    public void functionButtonClicked(Event e) {
        String function = ((Button) e.getSource()).getText() + "(";
        String screenValue = calculatorScreen.getText();
        if (function.equals("√(")) {
            function = "sqrt(";
        }
        String res = (screenValue.equals("") || screenValue.endsWith("(")) ? screenValue + function : screenValue + "*" + function;
        calculatorScreen.setText(res);
    }

    @FXML
    public void inverseFunctionButtonClicked(Event e) {
        String screenValue = calculatorScreen.getText();
        String res = "1/(" + screenValue;
        calculatorScreen.setText(res);
    }

    @FXML
    public void getResult(Event e) throws IllegalConstruction {
        String screenValue = calculatorScreen.getText();
        StringBuilder parenthesisToAdd = new StringBuilder();
        int leftP = (int) screenValue.chars().filter(c -> c == '(').count();
        int rightP = (int) screenValue.chars().filter(c -> c == ')').count();
        parenthesisToAdd.append(")".repeat(Math.max(0, leftP - rightP)));
        String res = screenValue + parenthesisToAdd;
        calculatorScreen.setText(res);

        Parser parser = new Parser(res);
        Expression expression = parser.getExpression(calculator);
        if (expression != null)
            answerScreen.setText(calculator.eval(expression));
    }


    @FXML
    public void showFunction(Event e) {
        if (funStage != null) funStage.close();


        StackPane secondaryLayout = new StackPane();

        Scene secondScene = new Scene(secondaryLayout, 230, 100);

        // New window (Stage)
        funStage = new Stage();
        funStage.setTitle("Function");
        funStage.setScene(secondScene);

        funStage.show();

        try {
            FXMLLoader functionShow = new FXMLLoader(getClass().getResource("/layout-functionShow.fxml"));
            Parent calculatorParent = functionShow.load();
            Scene calculatorScene = new Scene(calculatorParent, 640, 400);
            funStage.setScene(calculatorScene);

            FunctionController functionController = functionShow.getController();
            functionController.setCalculator(calculator);
        } catch (IOException ignored) {

        }
    }

    public void defFun(ActionEvent actionEvent) {
        calculatorScreen.setText("name -> x+2");
    }
}

package gui;

import calculator.Calculator;
import calculator.Expression;
import calculator.Parser;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.InvalidSyntax;
import function.Function;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller used for the Calculator Scene
 */
public class CalculatorController extends MainController implements Initializable {

    public Calculator calculator;
    @FXML
    public VBox calculatorVBox;
    public ComboBox<Calculator.Mode> modeComboBox;
    public Button integerTrigger;
    private Stage funStage;

    /**
     * Initialize the Calculator scene, by default on REAL mode.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainVBox = calculatorVBox;
        calculator = new Calculator(Calculator.Mode.REAL);
        modeComboBox.getItems().addAll(Calculator.Mode.values());
    }

    /**
     * Changing the mode from REAL to INTEGER or vice-versa
     *
     * @param e The event when we choose a mode in the drop-down menu
     */
    @FXML
    public void changeMode(Event e) {
        calculator = new Calculator((Calculator.Mode) ((ComboBox) e.getSource()).getValue());
        integerTrigger.setDisable(!integerTrigger.isDisable());
    }

    /**
     * Apply the function to what is already typed
     *
     * @param e The event when a function button is pressed
     */
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

    /**
     * Apply the inverse function to what is already typed
     *
     * @param e The event when the "1/x" button is pressed
     */
    @FXML
    public void inverseFunctionButtonClicked(Event e) {
        String screenValue = calculatorScreen.getText();
        String res = "1/(" + screenValue;
        calculatorScreen.setText(res);
    }

    /**
     * Create a Parser that computes the result, and show it on screen.
     *
     * @param e The event when the "=" button is pressed
     */
    @FXML
    public void getResult(Event e) throws IllegalConstruction {
        String screenValue = calculatorScreen.getText();
        StringBuilder parenthesisToAdd = new StringBuilder();
        int leftP = (int) screenValue.chars().filter(c -> c == '(').count();
        int rightP = (int) screenValue.chars().filter(c -> c == ')').count();
        parenthesisToAdd.append(")".repeat(Math.max(0, leftP - rightP)));
        String res = screenValue + parenthesisToAdd;
        calculatorScreen.setText(res);
        try {
            Parser parser = new Parser(res);
            Expression expression = parser.getExpression(calculator);
            if (expression instanceof Function && !((Function) expression).getVar().asValue()) {
                answerScreen.setText("Function Added");
            } else if (expression != null)
                answerScreen.setText(calculator.eval(expression));
        } catch (InvalidSyntax exception) {
            answerScreen.setText(exception.getMessage());
        }
    }

    /**
     * Create a window where it is possible to show defined functions
     *
     * @param e The event when the "Function" button is pressed
     */
    @FXML
    public void showFunction(Event e) {
        if (funStage != null) funStage.close();
        StackPane secondaryLayout = new StackPane();
        Scene secondScene = new Scene(secondaryLayout, 640, 400);

        funStage = new Stage();
        funStage.setTitle("Function");
        funStage.setScene(secondScene);
        funStage.setResizable(false);
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

    /**
     * Show an example of function definition on screen
     *
     * @param e The event when the "defFun" button is pressed
     */
    public void defFun(ActionEvent e) {
        calculatorScreen.setText("name -> x+2");
    }
}

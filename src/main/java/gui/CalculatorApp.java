package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader calculatorLoader = new FXMLLoader(getClass().getResource("/layout-calculator.fxml"));
        Parent calculatorParent = calculatorLoader.load();
        Scene calculatorScene = new Scene(calculatorParent, 640, 380);

        FXMLLoader converterLoader = new FXMLLoader(getClass().getResource("/layout-converter.fxml"));
        Parent converterParent = converterLoader.load();
        Scene converterScene = new Scene(converterParent, 330, 360);

        CalculatorController calculatorController = calculatorLoader.getController();
        calculatorController.setConverterScene(converterScene);

        ConverterController converterController = converterLoader.getController();
        converterController.setCalculatorScene(calculatorScene);

        primaryStage.setScene(calculatorScene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

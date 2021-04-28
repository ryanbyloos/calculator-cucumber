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

        FXMLLoader dateLoader = new FXMLLoader(getClass().getResource("/layout-date.fxml"));
        Parent dateParent = dateLoader.load();
        Scene dateScene = new Scene(dateParent, 330, 360);

        CalculatorController calculatorController = calculatorLoader.getController();
        calculatorController.setConverterScene(converterScene);
        calculatorController.setDateScene(dateScene);

        ConverterController converterController = converterLoader.getController();
        converterController.setCalculatorScene(calculatorScene);
        converterController.setDateScene(dateScene);

        DateController dateController = dateLoader.getController();
        dateController.setCalculatorScene(calculatorScene);
        dateController.setConverterScene(converterScene);

        primaryStage.setScene(dateScene);
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

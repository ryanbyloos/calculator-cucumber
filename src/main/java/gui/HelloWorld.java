package gui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloWorld extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Calculator!");
        setup(primaryStage);
        primaryStage.show();
    }
    public void setup(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/darklayout.fxml")));
        Scene scene = new Scene(root, 640, 400);
        primaryStage.setScene(scene);
    }

    @FXML
    public Label calculatorScreen;

    @FXML
    public void numberButtonClicked(Event e){
        String buttonValue = ((Button) e.getSource()).getText();
        calculatorScreen.setText(calculatorScreen.getText()+buttonValue);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

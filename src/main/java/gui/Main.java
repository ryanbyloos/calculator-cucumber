package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

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
    public static void main(String[] args) {
        launch(args);
    }
}

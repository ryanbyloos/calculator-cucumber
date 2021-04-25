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
    public void start(Stage primaryStage){
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/darklayout.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene mainScreen = new Scene(root, 640, 400);
        primaryStage.setScene(mainScreen);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }
    public void setup(Stage primaryStage) throws IOException {


    }
    public static void main(String[] args) {
        launch(args);
    }
}

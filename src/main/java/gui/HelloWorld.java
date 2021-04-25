package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
//        GridPane root = new GridPane();
//        for (int i = 1; i <= 3; i++) {
//            for (int j = 1; j <= 3 ; j++) {
//                int value = i*j;
//                NumberButton btn = new NumberButton(Integer.toString(value));
//                btn.setOnAction(event -> System.out.println(btn.value));
//                root.add(btn, i, j);
//            }
//        }
//
//        for (int i = 0; i < 5; i++) {
//            String[] operators = {"+", "-", "*", "/", "="};
//            OperatorButton op = new OperatorButton(operators[i]);
//            op.setOnAction(event -> System.out.println(op.value));
//            root.add(op, 4, i);
//        }
//        for (int i = 0; i < 2; i++) {
//            String[] mode = {"Decimal", "Integer","Convert"};
//            OperatorButton op = new OperatorButton(mode[i]);
//            op.setOnAction(event -> System.out.println(op.value));
//            root.add(op, 5, i);
//        }
//
//        for (Node node:root.getChildren()) {
//            GridPane.setMargin(node, new Insets(5, 5, 5, 5));
//        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/darklayout.fxml")));
        Scene scene = new Scene(root, 640, 400);
        primaryStage.setScene(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

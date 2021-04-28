package gui;

import calculator.Calculator;
import calculator.RealNumber;
import function.Function;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class FunctionController implements Initializable {

    @FXML private Pane pane;
    public TextField input;
    private Calculator calculator;

    private double center_x = 0;
    private double center_y = 0;
    private double size_pix = 0.05;

    public double pixToValueX(int pix){
        return center_x+(((double) pix)*size_pix);
    }
    public int valueToPixY(double v){
        return -(int) (v / size_pix);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    private void drawCanvas() {
        Path path = new Path();
        path.setStroke(Color.LIGHTGREEN);
        path.setStrokeWidth(2);

        path.setClip(
                new Rectangle(
                        0, 0,
                        pane.getPrefWidth(),
                        pane.getPrefHeight()
                )
        );
        // Clear pane
        pane.getChildren().clear();

        // get center of screen
        int center_pix_x = (int) (pane.getPrefWidth()/2.0);
        int center_pix_y = (int) (pane.getPrefHeight()/2.0);

        boolean first = true;

        String funName = input.getText();

        if (!calculator.getStoredFun().containsKey(funName)){
            return;
        }
        Function f = calculator.getStoredFun().get(funName);


        double py = 0;
        for(int x = 0 ; x < (int) (pane.getWidth()) ; x++){
            double x_value = pixToValueX(x) - (center_pix_x*size_pix);
            try {
                // compute function
                f.setValue(new RealNumber(Double.toString(x_value)));
                double y_value = Double.valueOf(calculator.evalReal(f).getValue().toPlainString());
                f.clearValue();

                int y = center_pix_y + valueToPixY(y_value + center_y);

                if (first) {
                    path.getElements().add(new MoveTo(x,y));
                    first = false;
                } else{
//                    if( (py > center_y+(size_pix*(double)center_pix_y)  &&  center_y-(size_pix*(double)center_pix_y) < y_value) ||
//                            (y_value > center_y+(size_pix*(double)center_pix_y)  && center_y-(size_pix*(double)center_pix_y) < py )){
//                        path.getElements().add(new MoveTo(x,y));
//                    }else{
//                        path.getElements().add(new LineTo(x,y));
//                    }
                    path.getElements().add(new LineTo(x,y));
                }
                py = y_value;

            }catch (Exception ignored){}
        }
        pane.getChildren().add(path);
    }

    @FXML
    private void ok(ActionEvent event){
        drawCanvas();
    }

    @FXML
    private void moveLeft(ActionEvent event){
        center_x+=5.0*size_pix;
        drawCanvas();
    }
    @FXML
    private void moveRight(){
        center_x-=5.0*size_pix;
        drawCanvas();
    }
    @FXML
    private void moveUp(ActionEvent event){
        center_y+=5.0*size_pix;
        drawCanvas();
    }
    @FXML
    private void moveDown(){
        center_y-=5.0*size_pix;
        drawCanvas();
    }

    @FXML
    private void zoomIn(){
        size_pix -= 0.005;
        if(size_pix <= 0.01) size_pix = 0.01;
        drawCanvas();
    }
    @FXML
    private void zoomOut(){
        size_pix += 0.005;
        drawCanvas();
    }

    public void setCalculator(Calculator c){
        calculator = c;
    }
}

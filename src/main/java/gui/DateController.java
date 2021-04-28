package gui;

import calculator.exceptions.IllegalConstruction;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class DateController extends MainController implements Initializable {

    @FXML
    public VBox dateVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainVBox = dateVBox;
    }

    @Override
    public void getResult(Event e) throws IllegalConstruction {

    }
}

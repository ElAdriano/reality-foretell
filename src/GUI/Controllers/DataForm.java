package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;

import java.io.IOException;

public class DataForm {

    private final String defaultInputValue = "";

    @FXML
    private CheckBox cameraCheckbox;

    @FXML
    private TextArea cameraPriceInput;
    @FXML
    private TextArea windowPriceInput;

    private void setEnableForPriceInput(TextArea inputField){
        inputField.setDisable(!inputField.isDisable());
        if(inputField.isDisable()){
            inputField.setText(defaultInputValue);
        }
    }

    @FXML
    public void onCameraCheckboxClick(ActionEvent actionEvent) {
        setEnableForPriceInput(cameraPriceInput);
    }

    @FXML
    public void onWindowCheckboxClick(ActionEvent actionEvent) {
        setEnableForPriceInput(windowPriceInput);
    }

}

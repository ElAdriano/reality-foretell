package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.IOException;

public class DataForm {

    @FXML
    private Circle backButtonCircle;

    @FXML
    private Polygon backButtonArrow;

    @FXML
    private void onBackButtonClick(MouseEvent mouseEvent) {
        Stage currentStage = (Stage) backButtonCircle.getScene().getWindow();

        Stage previousStage = null;
        try {
            previousStage = FXMLLoader.load(getClass().getResource("../Templates/StartMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        currentStage.setTitle(previousStage.getTitle());
        currentStage.setScene(previousStage.getScene());
    }

}

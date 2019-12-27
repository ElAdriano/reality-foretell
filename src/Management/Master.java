package Management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Master extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage stage = FXMLLoader.load(getClass().getResource("../GUI/Templates/StartMenu.fxml"));
        primaryStage.setScene(stage.getScene());
        primaryStage.setTitle(stage.getTitle());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

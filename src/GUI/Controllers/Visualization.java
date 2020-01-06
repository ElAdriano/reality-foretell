package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Visualization {

    private ArrayList<Image> generatedSchemes;
    private ArrayList<Background> generatedSchemesBackgroundImages;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;
    @FXML
    private int currentSchemeRenderedIndex;
    @FXML
    private Pane schemeView;

    public void initialize() {
        /*generatedSchemesBackgroundImages = new ArrayList<>();
        generatedSchemes = new ArrayList<>();

        for (Image image : generatedSchemes) {
            generatedSchemesBackgroundImages.add(new Background(new BackgroundImage(image, null, null, null, null)));
        }*/
    }

    @FXML
    public void onNextImageButtonClick(ActionEvent actionEvent) {
        /*currentSchemeRenderedIndex++;
        updateComponents();*/
    }

    @FXML
    public void onPreviousImageButton(ActionEvent actionEvent) {
        /*currentSchemeRenderedIndex--;
        updateComponents();*/
    }

    private void updateComponents(){
        progressBar.setProgress((currentSchemeRenderedIndex + 1) / generatedSchemes.size());
        progressLabel.setText(String.valueOf((currentSchemeRenderedIndex + 1) / generatedSchemes.size()));
        schemeView.setBackground(generatedSchemesBackgroundImages.get(currentSchemeRenderedIndex));
    }
}

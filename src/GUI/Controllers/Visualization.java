package GUI.Controllers;

import Management.ImageCreator;
import Management.SchemeGenerator;
import Management.PrisonScheme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class Visualization {

    private volatile ArrayList<Image> generatedSchemes;
    private volatile ArrayList<Background> generatedSchemesBackgroundImages;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;
    @FXML
    private int currentSchemeRenderedIndex;
    @FXML
    private Pane schemeView;

    public Visualization() {
        generatedSchemes = new ArrayList<>();
        generatedSchemesBackgroundImages = new ArrayList<>();

        SchemeGenerator generator = new SchemeGenerator();
        PrisonScheme prisonScheme = new PrisonScheme();
        Image image = ImageCreator.createImage(prisonScheme.getPrisonPlan(), prisonScheme.getPlanSquareSize(), prisonScheme.getPlanSquareSize());
        addNewGeneratedImage(image);
        /*addNewGeneratedImage(image);*/
        //generator.run();
        //addNewGeneratedImage(generator.getLastGeneratedImage());
    }

    public void initialize() {
        //showSavedConditions();
        currentSchemeRenderedIndex = 0;
        updateComponents();
    }

    public void addNewGeneratedImage(Image image) {
        generatedSchemes.add(image);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(
                BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
        generatedSchemesBackgroundImages.add(new Background(backgroundImage));
    }

    private void showSavedConditions() {
        System.out.println("maxBudget : " + SchemeGenerator.conditions.budget);
        System.out.println("amountOfPrisoners : " + SchemeGenerator.conditions.amountOfPrisoners);
        System.out.println("priceOfPrisonWard : " + SchemeGenerator.conditions.priceOfPrisonWard);
        System.out.println("maxAmountOfPrisonersInPrisonWard : " + SchemeGenerator.conditions.maxAmountOfPrisonersInPrisonWard);
        System.out.println("priceOfSanitaryNook : " + SchemeGenerator.conditions.priceOfSanitaryNook);
        System.out.println("cameraRange : " + SchemeGenerator.conditions.cameraRange);
        System.out.println("amountOfGenerations : " + SchemeGenerator.conditions.amountOfGenerations);
        System.out.println("aDimensionOfPrison : " + SchemeGenerator.conditions.aDimensionOfPrison);
        System.out.println("bDimensionOfPrison : " + SchemeGenerator.conditions.bDimensionOfPrison);
        System.out.println("cDimensionOfPrison : " + SchemeGenerator.conditions.cDimensionOfPrison);
        System.out.println("dDimensionOfPrison : " + SchemeGenerator.conditions.dDimensionOfPrison);
    }

    @FXML
    public void onNextImageButtonClick(ActionEvent actionEvent) {
        currentSchemeRenderedIndex++;
        if (currentSchemeRenderedIndex + 1 >= generatedSchemesBackgroundImages.size()) {
            disableButton(actionEvent);
        }
        if (currentSchemeRenderedIndex < generatedSchemesBackgroundImages.size()) {
            updateComponents();
        }
    }

    @FXML
    public void onPreviousImageButton(ActionEvent actionEvent) {
        currentSchemeRenderedIndex--;
        if (currentSchemeRenderedIndex - 1 < 0) {
            disableButton(actionEvent);
        }
        if (currentSchemeRenderedIndex >= 0) {
            updateComponents();
        }
    }

    private void updateComponents() {
        progressBar.setProgress((double)(currentSchemeRenderedIndex + 1) / generatedSchemes.size());
        progressLabel.setText(String.valueOf((currentSchemeRenderedIndex + 1) / generatedSchemes.size()));
        schemeView.setBackground(generatedSchemesBackgroundImages.get(currentSchemeRenderedIndex));
    }

    private void disableButton(ActionEvent actionEvent) {
        Button sourceButton = (Button) actionEvent.getSource();
        sourceButton.setDisable(true);
    }
}

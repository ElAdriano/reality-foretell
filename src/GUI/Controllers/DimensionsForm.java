package GUI.Controllers;

import Management.SchemeGenerator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class DimensionsForm {
    private final double DEFAULT_LAYOUT_Y = 83;
    private final double INITIAL_HEIGHT = 120;

    /* Sliders for DimensionsForm.fxml */
    @FXML
    private Slider aDimensionSlider;
    @FXML
    private Slider bDimensionSlider;
    @FXML
    private Slider cDimensionSlider;
    @FXML
    private Slider dDimensionSlider;

    /* Monitors for DimensionsForm.fxml */
    @FXML
    private Label aDimensionMonitor;
    @FXML
    private Label bDimensionMonitor;
    @FXML
    private Label cDimensionMonitor;
    @FXML
    private Label dDimensionMonitor;
    @FXML
    private Button startProcessButton;
    @FXML
    private Pane PrisonBuilding;
    @FXML
    private Pane OutSidePrisonBuilding;
    @FXML
    private Pane proTipWindow;
    @FXML
    private Pane backArrowViewIcon;
    private Background backArrowNotHover;
    private Background backArrowHover;

    private DecimalFormat sizeFormat;

    public void initialize() {
        sizeFormat = new DecimalFormat("0.00");
        getEnteredDataFromCache();

        proTipWindow.setVisible(false);
        backArrowNotHover = new Background(new BackgroundImage(new Image("GUI/Backgrounds/BackArrowCircle.png"), null, null, null, null));
        backArrowHover = new Background(new BackgroundImage(new Image("GUI/Backgrounds/BackArrowHoverCircle.png"), null, null, null, null));
        backArrowViewIcon.setBackground(backArrowNotHover);

        aDimensionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            aDimensionSlider.setValue((double) newValue);
            aDimensionMonitor.setText(sizeFormat.format(newValue));
            PrisonBuilding.setLayoutY(DEFAULT_LAYOUT_Y - ((double) newValue - INITIAL_HEIGHT) / 2);
            PrisonBuilding.setPrefHeight((double) newValue);
            OutSidePrisonBuilding.setMaxHeight((double) newValue - 5);

            if (aDimensionSlider.getValue() - cDimensionSlider.getValue() < 5) {
                cDimensionSlider.setValue((double) newValue - 5);
                cDimensionMonitor.setText(sizeFormat.format(cDimensionSlider.getValue()));
                OutSidePrisonBuilding.setPrefHeight((double) newValue - 5);
            }
        });

        bDimensionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            bDimensionSlider.setValueChanging(true);

            bDimensionSlider.setValue((double) newValue);
            bDimensionMonitor.setText(sizeFormat.format((double) newValue));

            PrisonBuilding.setPrefWidth((double) newValue);
            PrisonBuilding.setLayoutX(PrisonBuilding.getLayoutX() - ((double) newValue - (double) oldValue) / 2);

            OutSidePrisonBuilding.setPrefWidth((double) newValue - OutSidePrisonBuilding.getLayoutX());
            dDimensionSlider.setValue((double) newValue - OutSidePrisonBuilding.getLayoutX());
            dDimensionMonitor.setText(sizeFormat.format(dDimensionSlider.getValue()));

            // case when new width for PrisonBuilding is too wide ; calculated width for OutSidePrisonBuilding is greater than max
            if ((double) newValue - OutSidePrisonBuilding.getLayoutX() > dDimensionSlider.getMax()) {
                PrisonBuilding.setPrefWidth(OutSidePrisonBuilding.getLayoutX() + dDimensionSlider.getMax());
                bDimensionSlider.setValue(OutSidePrisonBuilding.getLayoutX() + dDimensionSlider.getMax());
                bDimensionMonitor.setText(sizeFormat.format(bDimensionSlider.getValue()));
            }

            // case when new width for PrisonBuilding is lesser than OutSidePrisonBuilding's layoutX => no dDimension seen
            if ((double) newValue - OutSidePrisonBuilding.getLayoutX() <= 5) {
                OutSidePrisonBuilding.setPrefWidth(5);
                OutSidePrisonBuilding.setLayoutX((double) newValue - 5);
                dDimensionSlider.setValue(5);
                dDimensionMonitor.setText(sizeFormat.format(dDimensionSlider.getValue()));
            }

            bDimensionSlider.setValueChanging(false);
        });

        cDimensionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!aDimensionSlider.isValueChanging()) {
                if ((double) newValue <= OutSidePrisonBuilding.getMaxHeight()) {
                    OutSidePrisonBuilding.setPrefHeight((double) newValue);
                } else {
                    cDimensionSlider.setValue(OutSidePrisonBuilding.getMaxHeight());
                }
                cDimensionMonitor.setText(String.valueOf(sizeFormat.format(cDimensionSlider.getValue())));
            }
        });

        dDimensionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!bDimensionSlider.isValueChanging()) {
                if (PrisonBuilding.getWidth() - (double) newValue >= 5) {
                    OutSidePrisonBuilding.setPrefWidth((double) newValue);
                    OutSidePrisonBuilding.setLayoutX(PrisonBuilding.getWidth() - (double) newValue);
                    dDimensionSlider.setValue((double) newValue);
                    dDimensionMonitor.setText(sizeFormat.format((double) newValue));
                } else {
                    OutSidePrisonBuilding.setPrefWidth(PrisonBuilding.getWidth() - 5);
                    OutSidePrisonBuilding.setLayoutX(5);
                    dDimensionSlider.setValue(PrisonBuilding.getWidth() - 5);
                    dDimensionMonitor.setText(sizeFormat.format(PrisonBuilding.getWidth() - 5));
                }
            }
        });
    }

    private void getEnteredDataFromCache() {
        loadValues(aDimensionSlider, aDimensionMonitor, SchemeGenerator.conditions.aDimensionOfPrison);
        loadValues(bDimensionSlider, bDimensionMonitor, SchemeGenerator.conditions.bDimensionOfPrison);
        loadValues(cDimensionSlider, cDimensionMonitor, SchemeGenerator.conditions.cDimensionOfPrison);
        loadValues(dDimensionSlider, dDimensionMonitor, SchemeGenerator.conditions.dDimensionOfPrison);

        PrisonBuilding.setLayoutY(PrisonBuilding.getLayoutY() - (aDimensionSlider.getValue() - PrisonBuilding.getPrefHeight()) / 2);
        PrisonBuilding.setPrefHeight(aDimensionSlider.getValue());

        PrisonBuilding.setLayoutX(PrisonBuilding.getLayoutX() - (bDimensionSlider.getValue() - PrisonBuilding.getPrefWidth()) / 2);
        PrisonBuilding.setPrefWidth(bDimensionSlider.getValue());

        OutSidePrisonBuilding.setPrefHeight(cDimensionSlider.getValue());

        OutSidePrisonBuilding.setPrefWidth(dDimensionSlider.getValue());
        OutSidePrisonBuilding.setLayoutX(PrisonBuilding.getPrefWidth() - dDimensionSlider.getValue());
    }

    private void loadValues(Slider slider, Label monitor, double value) {
        double valueToSetForSlider = value;
        if (valueToSetForSlider == -1) {
            valueToSetForSlider = (slider.getMin() + slider.getMax()) / 2;
        }
        slider.setValue(valueToSetForSlider);
        monitor.setText(sizeFormat.format(slider.getValue()));
    }

    @FXML
    private void onStartProcessButtonClick() {
        Stage nextStage = null;
        try {
            saveSlidersData();
            nextStage = FXMLLoader.load(getClass().getResource("/GUI/Templates/ShowResults.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Stage renderedStage = (Stage) startProcessButton.getScene().getWindow();
        renderedStage.setScene(nextStage.getScene());
    }

    @FXML
    private void closeProTipWindow() {
        proTipWindow.setVisible(false);
    }

    @FXML
    private void openProTipWindow() {
        proTipWindow.setVisible(true);
    }

    private void saveSlidersData() {
        SchemeGenerator.conditions.aDimensionOfPrison = Math.round(100.0 * aDimensionSlider.getValue()) / 100.0;
        SchemeGenerator.conditions.bDimensionOfPrison = Math.round(100.0 * bDimensionSlider.getValue()) / 100.0;
        SchemeGenerator.conditions.cDimensionOfPrison = Math.round(100.0 * cDimensionSlider.getValue()) / 100.0;
        SchemeGenerator.conditions.dDimensionOfPrison = Math.round(100.0 * dDimensionSlider.getValue()) / 100.0;
    }

    @FXML
    public void onBackArrowButtonMouseEntered() {
        changeBackground(backArrowHover);
    }

    @FXML
    public void onBackArrowButtonMouseExited() {
        changeBackground(backArrowNotHover);
    }

    private void changeBackground(Background background) {
        backArrowViewIcon.setBackground(background);
    }

    @FXML
    public void onBackArrowButtonMouseClicked() {
        saveSlidersData();
        Stage nextStage = null;
        try {
            nextStage = FXMLLoader.load(getClass().getResource("/GUI/Templates/DataForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Stage renderedStage = (Stage) startProcessButton.getScene().getWindow();
        renderedStage.setScene(nextStage.getScene());
    }
}

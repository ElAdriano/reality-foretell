package Management;

import Management.Fields;
import Management.ImageCreator;
import Management.SchemeGenerator;
import Models.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class PrisonScheme {
    private ArrayList<PrisonWard> prisonWards;
    private ArrayList<Door> doorsOnScheme;
    private ArrayList<LightBulb> lightBulbsOnScheme;
    private ArrayList<Camera> camerasOnScheme;
    private ArrayList<Window> windowsOnScheme;

    private MonitoringRoom monitoringRoom;
    private SanitaryNook sanitaryNook;

    private Image imageToShow;
    private Fields[][] prisonPlan;
    private int planSquareSize;

    private int aWall;
    private int bWall;
    private int cWall;
    private int dWall;


    private Random rand;

    private double rate;

    public PrisonScheme() {
        this.camerasOnScheme = new ArrayList<>();
        this.doorsOnScheme = new ArrayList<>();
        this.lightBulbsOnScheme = new ArrayList<>();
        this.camerasOnScheme = new ArrayList<>();
        this.windowsOnScheme = new ArrayList<>();

        this.planSquareSize = (int) Math.max(SchemeGenerator.conditions.bDimensionOfPrison, SchemeGenerator.conditions.aDimensionOfPrison);

        prisonPlan = new Fields[planSquareSize][planSquareSize];

        this.aWall = (int) SchemeGenerator.conditions.aDimensionOfPrison;
        this.bWall = (int) SchemeGenerator.conditions.bDimensionOfPrison;
        this.cWall = (int) SchemeGenerator.conditions.cDimensionOfPrison;
        this.dWall = (int) SchemeGenerator.conditions.dDimensionOfPrison;

        this.rand = new Random();

        fillAsOutsideArea();
        fillAsEmpty();

        //TODO create PrisonScheme instance

        addOutsideWalls();
        addCoridors();
    }

    private void fillAsOutsideArea() {
        for (int w = 0; w < planSquareSize; w++) {
            for (int h = 0; h < planSquareSize; h++) {
                prisonPlan[w][h] = Fields.OUTSIDE_FIELD;
            }
        }
    }

    private void fillAsEmpty() {
        for (int w = 0; w < planSquareSize; w++) {
            for (int h = 0; h < planSquareSize; h++) {
                if (!(w > bWall - dWall && h < cWall)) {
                    prisonPlan[w][h] = Fields.EMPTY;
                }
            }
        }
    }

    private void addOutsideWalls() {
        for (int w = 0; w < bWall; w++) {
            for (int h = 0; h < aWall; h++) {
                if (h == 0 || w == aWall ||
                        (w == 0 &&  h <= bWall- dWall) ||
                        (w <= cWall && h == bWall - dWall) ||
                        (w == cWall && h >= bWall - dWall) ||
                        (w >= cWall && h == bWall)) {
                    prisonPlan[w][h] = Fields.WALL;
                }
            }
        }
    }

    private void addCoridors() {
        int x = rand.nextInt(bWall - dWall - SchemeGenerator.conditions.xSizeOfMonitoringRoom) + SchemeGenerator.conditions.xSizeOfMonitoringRoom;
        int y = rand.nextInt(aWall - cWall - SchemeGenerator.conditions.ySizeOfMonitoringRoom) + SchemeGenerator.conditions.ySizeOfMonitoringRoom;

    }

    private void createImageToShow() {
        imageToShow = ImageCreator.createImage(prisonPlan, planSquareSize, planSquareSize);
    }

    public void arrangePrisonScheme() {
        //TODO initializing PrisonScheme components and whole prison
    }

    public void updateImage(Image image) {
        imageToShow = image;
    }

    public Image getImageToShow() {
        return imageToShow;
    }

    public int getPlanSquareSize() {
        return planSquareSize;
    }

    public double getRate() {
        return rate;
    }

    public Fields[][] getPrisonPlan() {
        return prisonPlan;
    }
}
